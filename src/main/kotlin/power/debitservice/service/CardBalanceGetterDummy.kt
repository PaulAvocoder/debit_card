package power.debitservice.service

import org.springframework.stereotype.Component

/**
 * Реализация-заглушка [CardBalanceGetter] для получения баланса карты

 */
private const val BALANCE_VALUE = 1000.0
@Component
class CardBalanceGetterDummy : CardBalanceGetter {
    override fun getBalances(accounts: List<String>): Map<String, Double> =
        accounts.associateWith { BALANCE_VALUE }

    override fun getBalance(number: Long): Double = BALANCE_VALUE
}
