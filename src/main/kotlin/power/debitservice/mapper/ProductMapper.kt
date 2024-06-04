package power.debitservice.mapper

import org.mapstruct.Mapper
import power.debitservice.dto.ProductReadDto
import power.debitservice.model.Product


/**
 * Маппер для модели [Product] и DTO [ProductReadDto]

 */
@Mapper
interface ProductMapper {
    fun toDto(product: Product): ProductReadDto<Any?>
}