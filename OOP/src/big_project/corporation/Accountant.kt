package big_project.corporation

class Accountant(
    name: String,
    age: Int = 0
) : Worker(name, age) {

    override fun work() {
        while (true) {
            print("Enter the operation code. O - exit, 1 - register new item: ")
            val operation = readln().toInt()
            when (operation) {
                0 -> break
                1 -> registerNewItem()
            }
        }
    }

    private fun registerNewItem() {
        print("Enter the product type. 0 -IFood, 1 - Appliance, 2 - Shoe: ")
        val productType = readln().toInt()
        print("Enter the product name: ")
        val productName = readln()
        print("Enter the product brand: ")
        val productBrand = readln()
        print("Enter the product price: ")
        val productPrice = readln().toInt()

        val productCard = when (productType) {
            0 -> {
                print("Enter the caloric: ")
                val caloric = readln().toInt()
                FoodCard(
                    name = productName,
                    brand = productBrand,
                    price = productPrice,
                    caloric = caloric
                )
            }
            1 -> {
                print("Enter the wattage: ")
                val wattage = readln().toInt()
                ApplianceCard(
                    name = productName,
                    brand = productBrand,
                    price = productPrice,
                    wattage = wattage
                )
            }
            else -> {
                print("Enter the size: ")
                val size = readln().toFloat()
                ShoeCard(
                    name = productName,
                    brand = productBrand,
                    price = productPrice,
                    size = size
                )
            }
        }
        productCard.printInfo()


    }


}
