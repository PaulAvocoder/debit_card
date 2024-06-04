package power.debitservice.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import power.debitservice.dto.ProductReadDto
import power.debitservice.repository.ProductRepository
import ru.astondevs.astlink.cardservice.mapper.ProductMapper


/**
 * Сервис для работы с карточными продуктами

 */
@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productMapper: ProductMapper

    /**
     *  Метод получает список карточных продуктов из БД

     *
     */
    fun getProducts(): List<ProductReadDto<Any?>> {
        return productRepository.findAll().map { productMapper.toDto(it) }
    }

}