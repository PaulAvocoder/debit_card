package power.debitservice.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import power.debitservice.model.Card
import power.debitservice.model.ConfidentialData

import java.util.*

/**
 * Репозиторий для хранения [Card] в БД
 *

 */
interface CardRepository : JpaRepository<Card, Long>{
    @EntityGraph("withProduct")
    fun findAllByUserId(userId: UUID): List<Card>
    @EntityGraph("withProduct")
    fun findCardByConfidentialData(confidentialData: ConfidentialData): Card?
}
