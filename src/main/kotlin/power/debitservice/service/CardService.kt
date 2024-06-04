package power.debitservice.service


import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import power.debitservice.dto.CardReadDto
import power.debitservice.dto.CardTransactionDto
import power.debitservice.dto.DetailedCardReadDto
import power.debitservice.exception.IllegalPinCodeException
import power.debitservice.exception.IllegalStatusException
import power.debitservice.mapper.CardMapper
import power.debitservice.repository.CardRepository
import power.debitservice.repository.ConfidentialDataRepository
import power.debitservice.repository.ProductRepository

import java.time.LocalDate
import java.util.*


private val logger = KotlinLogging.logger { }

/**
 * Сервис для работы с картами

 */
@Service
class CardService {
    @Autowired
    lateinit var cardRepository: CardRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var confidentialDataRepository: ConfidentialDataRepository

    @Autowired
    lateinit var cardMapper: CardMapper

    @Autowired
    lateinit var confidentialDataCreationDummy: ConfidentialDataCreationDummy

    @Autowired
    lateinit var userNameAndSurnameGetter: UserNameAndSurnameGetter

    @Autowired
    lateinit var cardBalanceGetter: CardBalanceGetter

    @Autowired
    lateinit var transactionInfoGetter: TransactionInfoGetter

    /**
     * Метод получает ID пользователя и ID продукта
     * Сохраняет новую карту в базу данных

     */
    fun saveCard(userId: UUID, productId: Int): Unit {
        val product = productRepository.findById(productId).get()
        val expireDate = LocalDate.now().plusYears(4)
        val confidentialData = confidentialDataRepository.save(confidentialDataCreationDummy.createConfidentialData())

        val card = Card(1, userId, confidentialData,
            product, "first name", "last name", expireDate)

        cardRepository.save(card)
    }

    @Autowired
    lateinit var encoderDecoderUtil: EncoderDecoderUtil

    /**
     * Метод получает баланс карт с внешнего источника
     * Получает все доступные карты пользователя
     * Добавляет к ним информацию о текущем балансе и мапит их в дто

     */
    fun getAll(userId: UUID): List<CardReadDto> {
        val cards = cardRepository.findAllByUserId(userId)
        val cardBalances = cardBalanceGetter.getBalances(cards.map { it.account })
        return cards.map {
            cardMapper.toDto(it, cardBalances.getValue(it.account), it.product.id!!)
        }
    }

    /**
     * Метод получает ФИО пользователя и баланс счета карты с внешнего источника
     * Получает полную информацию о банковской карте по ее номеру
     * Добавляет к ней информацию о ФИО и мапит её в ДТО
     * @author П.Галомзик
     */
    fun getByNumber(number: Long): DetailedCardReadDto {
        val confidentialData = confidentialDataRepository.findByCardNumber(number)
        val card =
            cardRepository.findCardByConfidentialData(confidentialData) ?: throw EntityNotFoundException("Card with $number not found")

        val balance = cardBalanceGetter.getBalance(number)
        
        val name = userNameAndSurnameGetter.getUserName(number)
        val surname = userNameAndSurnameGetter.getUserSurname(number)
        
        return cardMapper.toDto(card, balance, name, surname)

    }

    /**
     * Метод получает информацию об операциях карты с внешнего источника
     * и маппит ее в ДТО

     */

    fun getTransactionsInfo(number: Long): CardTransactionDto {
        val confidentialData = confidentialDataRepository.findByCardNumber(number)
        cardRepository.findCardByConfidentialData(confidentialData) ?: throw EntityNotFoundException("Card with $number not found")
        val transactions  = transactionInfoGetter.getTransactionsInfo(number)
        return transactions[number]?.let { cardMapper.toCardTransactionDto(number, it) }!!

    }

    fun updateCardStatus(number: Long, active: Boolean) {
        val card = cardRepository.findCardByConfidentialData(confidentialDataRepository.findByCardNumber(number)) ?: throw EntityNotFoundException("Card with $number not found")
        if (card.active != active) {
            card.active = active
            cardRepository.save(card)
        } else throw IllegalStatusException("Card Status Error")
    }

    fun updateCodeCard(number: Long, pin: Int) {
        val card = cardRepository.findCardByConfidentialData(
            confidentialDataRepository.findByCardNumber(number)) ?: throw EntityNotFoundException("Card with $number not found")
        val encryptpin = encoderDecoderUtil.encryptData((pin.toString()))
        if (card.confidentialData.pin != encryptpin) {
            card.confidentialData.pin = encryptpin
            cardRepository.save(card)
        } else throw IllegalPinCodeException("Card PIN code Error")
    }
}