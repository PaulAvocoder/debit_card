package power.debitservice.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

/**
 * Выпущенная банковская карта клиента *
 * @see Product
 * @author А.Гоманюк, К.Балыков
 */
@Entity
@NamedEntityGraph(name = "withProduct", attributeNodes = [NamedAttributeNode("product")])
@Table(name = "cards")
class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    @Column(name = "user_id")
    var userId: UUID,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_number")
    var confidentialData: ConfidentialData,
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product,
    @Column(name = "first_name")
    var firstName: String?,
    @Column(name = "last_name")
    var lastName: String?,
    @Column(name = "expire_date")
    var expireDate: LocalDate,
    var account: String = "account",
    var active: Boolean = true,
    @Column(name = "limit_per_month")
    var limitPerMonth: Double? = 0.0,
    @Column(name = "limit_per_operation")
    var limitPerOperation: Double? = 0.0,
    @Column(name = "transaction_limit")
    var transactionLimit: Int? = 0,
    @Column(name = "credit_limit")
    var creditLimit: Double? = 0.0
)
