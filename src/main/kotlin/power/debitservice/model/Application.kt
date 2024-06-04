package power.debitservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.*

/**
 * Заявки от пользователя на выпуск карт *
 * @see Product
 * @see Card

 */
@Entity
@Table(name = "applications")
class Application(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @Column(name = "user_id")
    var userId: UUID,
    @ManyToOne
    var product: Product
)
