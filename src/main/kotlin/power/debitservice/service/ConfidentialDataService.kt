package power.debitservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import power.debitservice.repository.ConfidentialDataRepository


/**
 * Сервис для работы с конфиденциальной информацией по карте

 */
@Component
class ConfidentialDataService {
    @Autowired
    lateinit var confidentialDataRepository: ConfidentialDataRepository

    /**
     * Метод принимает номер карты
     * Получает CVV карты

     */
    fun getSecurityCode(cardNumber: Long): String {
        val confidentialData = confidentialDataRepository.findByCardNumber(cardNumber)
        return confidentialData.securityCode
    }
}