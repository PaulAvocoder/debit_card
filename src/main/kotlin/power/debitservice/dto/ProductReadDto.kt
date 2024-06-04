package power.debitservice.dto

import power.debitservice.model.enums.Currency
import power.debitservice.model.enums.Type
import power.debitservice.model.enums.PaymentSystem

class ProductReadDto(
    val name:String,
    val type: Type,
    val currency: Currency,
    val paymentSystem: PaymentSystem,
    val description: String
)

