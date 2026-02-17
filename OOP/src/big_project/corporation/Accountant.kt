package big_project.corporation

import java.io.File

class Accountant(
    name: String,
    age: Int = 0
) : Worker(name, age) {

    val file = File("product_card.txt")

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
                OperationCode.SHOW_ALL_ITEMS -> showAllItems()
                OperationCode.REMOVE_PRODUCT_CARD -> removeProductCard()
            }
        }

    }

    private fun removeProductCard() {
        val cards = getAllProducts()
        print("Enter name of card for removing: ")
        val name = readln()
        for (card in cards) {
            if (card.name == name){
                cards.remove(card)
            }
            break
        }
        file.writeText("")

        for (card in cards) {
            saveProductCardToFile(card)
        }
    }

    fun saveProductCardToFile(productCard: ProductCard) {
        file.appendText("${productCard.name}%${productCard.brand}%${productCard.price}%")
        when (productCard) {
            is FoodCard -> {
                file.appendText("${productCard.caloric}%")
            }

            is ApplianceCard -> {
                file.appendText("${productCard.wattage}%")
            }

            is ShoeCard -> {
                file.appendText("${productCard.size}%")
            }
        }
        file.appendText("${productCard.productType}\n")
    }

    private fun getAllProducts(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
        if (cards.isEmpty()){
            print("There no items found\n")
            work()
            return cards
        }
        val allText = file.readText().trim()
        val lines = allText.split("\n")

        for (line in lines) {
            val properties = line.split("%")
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()

            val productType = ProductType.valueOf(properties.last())

            val product = when (productType) {
                ProductType.FOOD -> {
                    val caloric = properties[3].toInt()
                    FoodCard(name, brand, price, caloric)
                }

                ProductType.APPLIANCE -> {
                    val wattage = properties[3].toInt()
                    ApplianceCard(name, brand, price, wattage)
                }

                ProductType.SHOE -> {
                    val size = properties[3].toFloat()
                    ShoeCard(name, brand, price, size)
                }
            }
            cards.add(product)
        }

        return cards
    }


    fun showAllItems() {
        val productCards = getAllProducts()
        for (card in productCards){
            card.printInfo()
        }
    }


    private fun registerNewItem() {
        val productTypes = ProductType.entries
        print("Enter the product type. ")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index < productTypes.size - 1) {
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
        saveProductCardToFile(productCard)
    }


}
