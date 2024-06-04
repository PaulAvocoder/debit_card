package power.debitservice.dto

class ProductReadDto(
    val name:String,
    val type: Type,
    val currency: Currency,
    val paymentSystem: PaymentSystem,
    val description: String
)

