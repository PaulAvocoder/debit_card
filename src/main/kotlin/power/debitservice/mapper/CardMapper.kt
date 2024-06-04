package power.debitservice.mapper

import org.springframework.stereotype.Component
import power.debitservice.dto.CardReadDto
import javax.smartcardio.Card


/**
 * Маппер для карты
 * @author А. Гоманюк
 */
@Component
class CardMapper {
    fun toDto(card: Card, balance: Double, productId: Int): CardReadDto {
        return CardReadDto(
            number = card.confidentialData.cardNumber,
            name = card.product.name,
            balance = balance,
            imageUrl = "$IMAGE_URI/$productId"
        )
    }


    fun toDto(card: Card, balance: Double, firstName: String, lastName: String): DetailedCardReadDto {
        return DetailedCardReadDto(
            expireDate = card.expireDate,
            balance = balance,
            active = card.active,
            firstName = firstName,
            lastName = lastName,
            account = card.account,
            currency = card.product.currency
        )
    }

    fun toCardTransactionDto(number: Long, transactions: List<TransactionDummy>): CardTransactionDto {
        return CardTransactionDto(
            number = number,
            transactions = transactions
        )
    }

}