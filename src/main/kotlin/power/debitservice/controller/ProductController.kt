package power.debitservice.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


const val PRODUCT_URI = "/card/v1/products"

/**
 * Контроллер для работы с карточными продуктами

 */
@RestController
@RequestMapping(PRODUCT_URI)
@Tag(name = "Product controller")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var jwtConfig: JwtConfig

    /**
     * Метод для получения списка карточных продуктов

     */
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getProducts(@RequestHeader("Authorization") authorizationHeader:String ){
        val userId = jwtConfig.getUserId(authorizationHeader.substringAfter(" "))
        productService.getProducts()
    }
}