package power.debitservice.service

/**
 * Интерфейс для получения баланса карты пользователя из АБС банка

 */
interface CardBalanceGetter {
    fun getBalances(accounts: List<String>):Map<String, Double>

    fun getBalance(number:Long):Double
}