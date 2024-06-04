package power.debitservice.service

import org.springframework.stereotype.Component

private const val USER_NAME = "Иван"
private const val USER_SURNAME = "Иванов"

/**
 * Реализация-заглушка [UserNameAndSurnameGetter]
 * @author П.Галомзик
 */
@Component
class UserNameAndSurnameGetterDummy: UserNameAndSurnameGetter {

    override fun getUserName(cardNumber: Long): String {
        return USER_NAME
    }

    override fun getUserSurname(cardNumber: Long): String {
        return USER_SURNAME
    }
}