package big_project.corporation

class FoodCard(
    name: String,
    brand: String,
    price: Int,
    val caloric: Int
) : ProductCard(name, brand, price) {

    override fun printInfo() {
        super.printInfo()
        print("Caloric: $caloric\n")
    }
}