package practice

import practice.enums.ProductType

class Accountant {


    fun work() {
        while (true) {
            print("Enter the operation code. 0 - exit, 1 - register new item: ")
            val continueChoice = readln().toInt()

            when (continueChoice) {
                0 -> {
                    break
                }

                1 -> {
                    registerNewItem()
                }
            }
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
        productCard.printInfo()
    }

}