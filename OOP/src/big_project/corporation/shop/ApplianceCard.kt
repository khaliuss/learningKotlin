package big_project.corporation.shop

import big_project.corporation.enums.ProductType

class ApplianceCard(
    name: String,
    brand: String,
    price: Int,
    val wattage: Int
) : ProductCard(name, brand, price, ProductType.APPLIANCE) {

    override fun printInfo() {
        super.printInfo()
        print("Wattage: $wattage\n")
    }
}