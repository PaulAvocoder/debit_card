package power.debitservice.dto

/**
 * Данные для отображения операции по номеру карты.

 */
class CardTransactionDto(
    val number: Long,
    val transactions: List<TransactionDummy>
)
