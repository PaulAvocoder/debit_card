package power.debitservice.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import power.debitservice.dto.*
import power.debitservice.exception.IllegalCardNumberException
import power.debitservice.service.CardService
import power.debitservice.service.ConfidentialDataService


const val CARD_URI = "/card/v1/cards"



@RestController
@RequestMapping(CARD_URI)
@Tag(name = "Card controller")
class CardController {
    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var confidentialDataService: ConfidentialDataService

    @Autowired
    lateinit var jwtConfig: JwtConfig


    @GetMapping(
        path = ["/cvv"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getSecurityCode(@RequestHeader("Authorization") authorizationHeader: String,
                        @RequestBody cardNumberRequestDto: CardNumberRequestDto
    ): String {
        try {
            return confidentialDataService.getSecurityCode(cardNumberRequestDto.cardNumber)
        } catch (e: Exception) {
            throw IllegalCardNumberException("Карты с данным номером не существует")
        }
    }

    /**
     * Получение списка карт пользователя. ID пользователя достается из jwt токена
     * @author П.Галомзик
     */
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getCards(@RequestHeader("Authorization") authorizationHeader: String): List<CardReadDto> {
        val userId = jwtConfig.getUserId(authorizationHeader.substringAfter(" "))
        return cardService.getAll(userId)
    }

    @PatchMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    fun changeCardStatus(@RequestHeader("Authorization") authorizationHeader: String, @RequestBody request: CardActiveDto) {
        val userId = jwtConfig.getUserId(authorizationHeader.substringAfter(" "))
        var card = cardService.updateCardStatus(request.number, request.active)
    }

    @PostMapping("/code")
    @ResponseStatus(HttpStatus.OK)
    fun changeCode(@RequestHeader("Authorization") authorizationHeader: String, @RequestBody request: CardCodeDto): List<CardReadDto> {
        val userId = jwtConfig.getUserId(authorizationHeader.substringAfter(" "))
        return cardService.getAll(userId)
    }

    /**
     * Получение полной информации о карте пользователя по ее номеру.
     * @author П.Галомзик
     */
    @GetMapping(
        path = ["/{number}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun getCard(@PathVariable number: Long, @RequestHeader("Authorization") authorizationHeader: String): DetailedCardReadDto {
        return cardService.getByNumber(number)
    }
}