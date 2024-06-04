package power.debitservice.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


const val APPLICATION_URI = "/card/v1/application"

/**
 * Контроллер для работы с заявками на создание карты.
 * @author П. Галомзик
 */
@RestController
@RequestMapping(APPLICATION_URI)
@Tag(name = "Application controller")
class ApplicationController {
    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var jwtConfig: JwtConfig

    @Autowired
    lateinit var imageService: ImageService

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createCard(@RequestHeader("Authorization") authorizationHeader: String,  @RequestParam productId: Int) {
        val userId = jwtConfig.getUserId(authorizationHeader.substringAfter(" "))
        cardService.saveCard(userId, productId)
        imageService.getImageById(productId)
    }
}