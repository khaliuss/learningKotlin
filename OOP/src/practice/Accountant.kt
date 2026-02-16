package practice

import practice.enums.OperationCode
import practice.enums.ProductType
import java.io.File

class Accountant {

    val items = mutableListOf<ProductCard>()
    val file = File("product_card.txt")

    fun work() {
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
                OperationCode.REMOVE_ITEMS -> removeProduct();
            }

        }

    }

    private fun removeProduct(){
        val cards = loadAllCards()
        print("Enter name of card for removing: ")
        val name = readln()
        for (card in loadAllCards()){
            if (card.name == name){
                cards.remove(card)
                break
            }
        }
        file.writeText("")
        for (card in cards ){
            saveProductCardToFile(card)
        }
        work()
    }

    private fun saveProductCardToFile(productCard: ProductCard){
        file.appendText(  "${productCard.name}%")
        file.appendText(  "${productCard.brand}%")
        file.appendText(  "${productCard.price}%")

        when (productCard) {
            is FoodCard -> {
                val calories = productCard.calories
                file.appendText("$calories%${ProductType.FOOD}")
            }

            is AppliancesCard -> {
                val wattage = productCard.wattage
                file.appendText("$wattage%${ProductType.APPLIANCE}")
            }

            is ShoeCard -> {
                val size = readln().toFloat()
                file.appendText("$size%${ProductType.SHOE}")
            }
        }
    }

    private fun loadAllCards(): MutableList<ProductCard>{
        val cards = mutableListOf<ProductCard>()
        val lines = file.readText().trim()
//        if (lines.isEmpty()){
//            return
//        }
        val products = lines.split("\n")
        for (product in products){
            val properties = product.split("%")
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)

            val product = when(productType){
                ProductType.FOOD -> {
                    val caloric = properties[3].toInt()
                    FoodCard(name,brand,price,caloric)
                }
                ProductType.APPLIANCE -> {
                    val wattage = properties[3].toInt()
                    AppliancesCard(name,brand,price,wattage)
                }
                ProductType.SHOE -> {
                    val size = properties[3].toFloat()
                    ShoeCard(name,brand,price,size)
                }
            }

            cards.add(product)
        }
        return cards
    }

    private fun showAllItems() {
        val lines = file.readText().trim()
        if (lines.isEmpty()) return
        val products = lines.split("\n")
        for (product in products){
            val properties = product.split("%")
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)

            val product = when(productType){
                ProductType.FOOD -> {
                    val caloric = properties[3].toInt()
                    FoodCard(name,brand,price,caloric)
                }
                ProductType.APPLIANCE -> {
                    val wattage = properties[3].toInt()
                    AppliancesCard(name,brand,price,wattage)
                }
                ProductType.SHOE -> {
                    val size = properties[3].toFloat()
                    ShoeCard(name,brand,price,size)
                }
            }

            items.add(product)

        }

        for (item in items){
            item.printInfo()
        }
    }

    private fun registerNewItem() {
        val productTypes = ProductType.entries
        print("Enter the product type. ")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index < productTypes.size - 1) {
                print(", ")
            } else {
                print(": ")
            }
        }

        val productTypeIndex = readln().toInt()
        val productType = productTypes[productTypeIndex]
        print("Enter the product name: ")
        val name = readln()
        file.appendText("$name%")
        print("Enter the product brand: ")
        val brand = readln()
        file.appendText("$brand%")
        print("Enter the product price: ")
        val price = readln().toInt()
        file.appendText("$price%")

        when (productType) {
            ProductType.FOOD -> {
                print("Enter the calories: ")
                val calories = readln().toInt()
                file.appendText("$calories%")
            }

            ProductType.APPLIANCE -> {
                print("Enter the wattage: ")
                val wattage = readln().toInt()
                file.appendText("$wattage%")
            }

            ProductType.SHOE -> {
                print("Enter the size: ")
                val size = readln().toFloat()
                file.appendText("$size%")
            }
        }
        file.appendText("$productType")
        file.appendText("\n")

    }

}