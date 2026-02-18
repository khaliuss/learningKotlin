package big_project.corporation.shop

import big_project.corporation.enums.ProductType

class ApplianceCard(
    name: String,
    brand: String,
    price: Int,
    val wattage: Int
) : ProductCard(name, brand, price, ProductType.APPLIANCE) {

    override fun printInfo() {
        print("Name: ${this.name} Brand: ${this.brand} Price: ${this.price} Product type: ${productType.title} Wattage: $wattage\n")
    }
}