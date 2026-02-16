package big_project.corporation

class Accountant(
    name: String,
    age: Int = 0
) : Worker(name, age) {

    override fun work() {
        val operationCodes = OperationCode.entries
        while (true) {
            print("Enter the operation code. ")
            for ((index, code) in operationCodes.withIndex()) {
                print("$index - ${code.title}")
                if (index < operationCodes.size - 1) {
                    print(", ")
                } else {
                    print(": ")
                }
            }
            val operationIndex = readln().toInt()
            val operationCode = operationCodes[operationIndex]
            when (operationCode) {
                OperationCode.EXIT -> break
                OperationCode.REGISTER_NEW_ITEM -> registerNewItem()
            }
        }
    }

    private fun registerNewItem() {
        val productTypes = ProductType.entries
        print("Enter the product type. ")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title},")

            if (index < productTypes.size) {
                print(",")
            } else {
                print(": ")
            }

        }

        val productTypeIndex = readln().toInt()
        val productType: ProductType = productTypes[productTypeIndex]
        print("Enter the product name: ")
        val productName = readln()
        print("Enter the product brand: ")
        val productBrand = readln()
        print("Enter the product price: ")
        val productPrice = readln().toInt()

        val productCard = when (productType) {
            ProductType.FOOD -> {
                print("Enter the caloric: ")
                val caloric = readln().toInt()
                FoodCard(
                    name = productName,
                    brand = productBrand,
                    price = productPrice,
                    caloric = caloric
                )
            }

            ProductType.APPLIANCE -> {
                print("Enter the wattage: ")
                val wattage = readln().toInt()
                ApplianceCard(
                    name = productName,
                    brand = productBrand,
                    price = productPrice,
                    wattage = wattage
                )
            }

            ProductType.SHOE -> {
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
