package power.debitservice.service


/**
 * Интерфейс для получения Имени и Фамилии пользователя по номеру карты
 * @author П.Галомзик
 */
interface UserNameAndSurnameGetter {
    fun getUserName(cardNumber:Long):String
    fun getUserSurname(cardNumber:Long):String
}