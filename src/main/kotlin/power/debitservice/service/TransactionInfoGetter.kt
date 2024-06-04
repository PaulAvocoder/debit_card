package power.debitservice.service

import power.debitservice.model.dummy.TransactionDummy


/**
 * Интерфейс для получения информации об операциях по номеру карты.

 */
interface TransactionInfoGetter {
    fun getTransactionsInfo(number:Long):Map<Long, List<TransactionDummy>>
}