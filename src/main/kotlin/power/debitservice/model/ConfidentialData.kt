package power.debitservice.model

import jakarta.persistence.*

/**
 * Конфиденциальная информация по карте *

 */
@Entity
@Table(name = "confidential_card_data")
class ConfidentialData (
    @Id
    @Column(name = "card_number")
    var cardNumber: Long,
    var pin: String,
    @Column(name = "security_code")
    var securityCode: String,
    @OneToOne(mappedBy = "confidentialData")
    val card: Card? = null
)
