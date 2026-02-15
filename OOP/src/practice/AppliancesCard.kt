package practice

class AppliancesCard(
    name: String,
    brand: String,
    price: Int,
    val wattage: Int
) : ProductCard(name, brand, price) {

    override fun printInfo() {
        super.printInfo()
        println("Wattage: $wattage")

    }
}