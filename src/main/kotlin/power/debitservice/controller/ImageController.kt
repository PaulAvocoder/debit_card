package power.debitservice.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


const val IMAGE_URI = "/card/v1/images"

/**
 * Контроллер обращающийся к [ImageService] для получения изображений карточек.
 * @author А.Гоманюк
 * @exception [ImageNotFoundException]
 */
@RestController
@RequestMapping(IMAGE_URI)
@Tag(name = "Image controller")
class ImageController {
    @Autowired
    lateinit var imageService: ImageService

    /**
     * Получение изображения по идентификатору карточного продукта.
     * @author П. Галомзик
     */
    @GetMapping(
        path = ["/{imageId}"],
        produces = [MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun getImageById(@PathVariable imageId: Int): ByteArray {
        try {
            return imageService.getImageById(imageId)
        } catch (e: Exception) {
            throw ImageNotFoundException(e.message)
        }

    }
}