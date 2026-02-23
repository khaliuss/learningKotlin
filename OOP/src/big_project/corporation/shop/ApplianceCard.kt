package big_project.corporation.shop

import big_project.corporation.enums.ProductType

data class ApplianceCard(
    override val name: String,
    override val brand: String,
    override val price: Int,
    val wattage: Int
) : ProductCard(name, brand, price, ProductType.APPLIANCE) {


}