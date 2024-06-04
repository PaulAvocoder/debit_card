package power.debitservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import power.debitservice.model.ConfidentialData


/**
 * Репозиторий для хранения [ConfidentialData] в БД
 *

 */
interface ConfidentialDataRepository : JpaRepository<ConfidentialData, Long> {
    fun findByCardNumber(cardNumber: Long): ConfidentialData
}