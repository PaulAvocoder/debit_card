package power.debitservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import power.debitservice.model.Product


/**
 * Репозиторий для работы с продуктами

 */
interface ProductRepository:JpaRepository<Product,Int> {
    override fun findAll():List<Product>

}