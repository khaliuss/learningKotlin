package practice

import practice.enums.OperationCode
import practice.enums.ProductType

class Accountant {

    val items = mutableListOf<ProductCard>()

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
            }

        }

    }

    private fun showAllItems(){
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
        print("Enter the product brand: ")
        val brand = readln()
        print("Enter the product price: ")
        val price = readln().toInt()

        var productCard = when (productType) {
            ProductType.FOOD -> {
                print("Enter the calories: ")
                val calories = readln().toInt()
                FoodCard(name, brand, price, calories)
            }

            ProductType.APPLIANCE -> {
                print("Enter the wattage: ")
                val wattage = readln().toInt()
                AppliancesCard(name, brand, price, wattage)
            }

            ProductType.SHOE -> {
                print("Enter the size: ")
                val size = readln().toFloat()
                ShoeCard(name, brand, price, size)
            }
        }
        items.add(productCard)
     }

}