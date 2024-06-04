package power.debitservice.service

import org.springframework.stereotype.Component
import power.debitservice.model.ConfidentialData


/**
 * Реализация-заглушка для создания конфиденциальной информации по карте

 */

private const val CARD_NUMBER = 1231_1231_1231_1231
private const val PIN = "1234"
private const val SECURITY_CODE ="1234"

@Component
class ConfidentialDataCreationDummy {
    fun createConfidentialData() : ConfidentialData = ConfidentialData(CARD_NUMBER, PIN, SECURITY_CODE)
}