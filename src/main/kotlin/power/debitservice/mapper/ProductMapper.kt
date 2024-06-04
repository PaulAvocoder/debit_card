package ru.astondevs.astlink.cardservice.mapper

import org.mapstruct.Mapper
import ru.astondevs.astlink.cardservice.dto.ProductReadDto
import ru.astondevs.astlink.cardservice.model.Product

/**
 * Маппер для модели [Product] и DTO [ProductReadDto]
 * @author А.Гоманюк
 */
@Mapper
interface ProductMapper {
    fun toDto(product: Product):ProductReadDto
}