package power.debitservice.dto

import power.debitservice.model.dummy.TransactionDummy

/**
 * Данные для отображения операции по номеру карты.

 */
class CardTransactionDto(
    val number: Long,
    val transactions: List<TransactionDummy>
)
