package power.debitservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


/**
 * Карточные продукты предлагаемые банком
 *
 * @author А.Гоманюк, К.Балыков
 */
@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var name: String,
    @Enumerated(EnumType.STRING)
    var type: Type,
    @Enumerated(EnumType.STRING)
    var currency: Currency,
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_system")
    var paymentSystem: PaymentSystem,
    var description: String
)