package power.debitservice.dto


import java.time.LocalDate

/**
 * DTO для отображения детальной информации о карте клиента
 * @author  П.Галомзик
 */
class DetailedCardReadDto(
    val expireDate:LocalDate,
    val balance: Double,
    val active: Boolean,
    val firstName: String,
    val lastName: String,
    val account: String,
    val currency: Currency
)