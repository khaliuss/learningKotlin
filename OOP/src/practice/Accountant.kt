package practice

class Accountant {


    fun work() {
        while (true) {
            print("Enter the operation code. 0 - exit, 1 - register new item: ")
            val continueChoice = readln().toInt()

            if (continueChoice == 0) break;

            print("Enter the product type. O - Food, 1 - Appliance, 2 - Shoe: ")
            val productChoice = readln().toInt()

            when (productChoice) {
                0 -> {
                    chosen(0)
                }

                1 -> {
                    chosen(1)
                }

                2 -> {
                    chosen(2)
                }
            }
        }
    }

    private fun chosen(product: Int) {

        print("Enter the product name: ")
        val name = readln()
        print("Enter the product brand: ")
        val brand = readln()
        print("Enter the product price: ")
        val price = readln().toInt()

        when (product) {
            0 -> {
                print("Enter the calories: ")
                val calories = readln().toInt()
                val food = FoodCard(name, brand, price, calories)
                food.printInfo()
            }

            1 -> {
                print("Enter the wattage: ")
                val wattage = readln().toInt()
                val appliance = AppliancesCard(name, brand, price, wattage)
                appliance.printInfo()
            }

            2 -> {
                print("Enter the size: ")
                val size = readln().toFloat()
                val shoe = ShoeCard(name, brand, price, size)
                shoe.printInfo()
            }
        }


    }

}