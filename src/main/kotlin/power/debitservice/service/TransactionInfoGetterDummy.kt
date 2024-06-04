package power.debitservice.service

import org.springframework.stereotype.Component
import power.debitservice.model.dummy.TransactionDummy

import java.time.LocalDateTime

private const val sum = 500.0
private const val place = "Some place"
private val date = LocalDateTime.of(2022, 1, 1, 0, 0, 0)

/**
 * Реализация-заглушка получения информации об операциях по номеру карты

 */
@Component
class TransactionInfoGetterDummy : TransactionInfoGetter {
    override fun getTransactionsInfo(number: Long): Map<Long, List<TransactionDummy>> {

        val transactions = listOf(
            TransactionDummy(sum, place, date)
        )

        return mapOf(number to transactions)
    }

}