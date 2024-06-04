package power.debitservice.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


const val TRANSACTION_URI = "/card/v1/transactions"

/**
 * Контроллер для работы с операциями по карте

 */
@RestController
@RequestMapping(TRANSACTION_URI)
@Tag(name = "Transaction controller")
class TransactionController {

    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var jwtConfig: JwtConfig

    /**
     * Метод для получения информации об операциях по карте

     */
    @GetMapping(path = ["/{number}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getTransactionsInfo(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable number: Long
    ): CardTransactionDto {
        val userId = jwtConfig.getUserId(authorizationHeader.substringAfter(" "))
        return cardService.getTransactionsInfo(number)
    }

}