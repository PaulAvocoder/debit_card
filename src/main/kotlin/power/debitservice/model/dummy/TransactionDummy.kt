package power.debitservice.model.dummy

import java.time.LocalDateTime

/**
 * Заглушка для возврата данных об опрерации
 * @author А.Гоманюк
 */
class TransactionDummy(
    val sum: Double,
    val place: String,
    val date: LocalDateTime
)

