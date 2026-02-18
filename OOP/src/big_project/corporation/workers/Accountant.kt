package big_project.corporation.workers

import big_project.corporation.shop.ApplianceCard
import big_project.corporation.shop.FoodCard
import big_project.corporation.shop.ProductCard
import big_project.corporation.shop.ShoeCard
import big_project.corporation.enums.OperationCode
import big_project.corporation.enums.ProductType
import big_project.corporation.enums.WorkerPosition
import java.io.File

class Accountant(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id,name, age, WorkerPosition.ACCOUNTANT) {

    private val productFile = File("product_card.txt")
    private val employeeFile = File("employees.txt")

    override fun work() {
        val operationCodes = OperationCode.entries
        while (true) {
            println("Enter the operation code. ")
            for ((index, code) in operationCodes.withIndex()) {
                println("$index - ${code.title}")
            }
            val operationIndex = readln().toInt()
            val operationCode = operationCodes[operationIndex]
            when (operationCode) {
                OperationCode.EXIT -> break
                OperationCode.REGISTER_NEW_ITEM -> registerNewItem()
                OperationCode.SHOW_ALL_ITEMS -> showAllItems()
                OperationCode.REMOVE_PRODUCT_CARD -> removeProductCard()
                OperationCode.REGISTER_NEW_EMPLOYEE -> registerNewEmployee()
                OperationCode.FIRE_AN_EMPLOYEE -> fireEmployee()
                OperationCode.SHOW_ALL_EMPLOYEES -> showAllEmployees()
            }
        }

    }

    private fun showAllEmployees(){
        val employees = getAllEmployees()
        for (employee in employees){
            employee.personInf()
        }
    }

    private fun fireEmployee(){
        val employees = getAllEmployees()
        print("Enter employee's id to fire: ")
        val id = readln().toInt()
        for (employee in employees){
            if (employee.id == id ){
                employees.remove(employee)
                break
            }
        }
        employeeFile.writeText("")

        for (employee in employees){
            saveEmployee(employee)
        }
    }

    private fun getAllEmployees(): MutableList<Worker>{
        val employees = mutableListOf<Worker>()

        if (!employeeFile.exists()) employeeFile.createNewFile()
        val allText = employeeFile.readText().trim()
        if (allText.isEmpty()) return employees

        val lines = allText.split("\n")
        for (line in lines){
            val properties = line.split("%")
            val id = properties[0].toInt()
            val name = properties[1]
            val age = properties[2].toInt()
            val type = properties.last()

            val position = WorkerPosition.valueOf(type)

            val worker = when(position){
                WorkerPosition.DIRECTOR -> Director(id,name,age)
                WorkerPosition.ACCOUNTANT -> Accountant(id,name,age)
                WorkerPosition.ASSISTANT -> Accountant(id,name,age)
                WorkerPosition.CONSULTANT -> Consultant(id,name,age)
            }
            employees.add(worker)
        }

        return employees
    }


    private fun registerNewEmployee(){
        val workerTypes = WorkerPosition.entries

        print("Choose position -")
        for ((index,type) in workerTypes.withIndex()){
            print("$index - ${type.title}")
            if (index < workerTypes.size-1){
                print(", ")
            }else{
                print(": ")
            }
        }

        val workerTypeIndex = readln().toInt()
        val workerType = workerTypes[workerTypeIndex]
        print("Enter id:")
        val id = readln().toInt()
        print("Enter name:")
        val name = readln()
        print("Enter age:")
        val age = readln().toInt()

        val employee = when(workerType){
            WorkerPosition.DIRECTOR -> Director(id,name,age)
            WorkerPosition.ACCOUNTANT -> Accountant(id,name,age)
            WorkerPosition.ASSISTANT -> Accountant(id,name,age)
            WorkerPosition.CONSULTANT -> Consultant(id,name,age)
        }
        saveEmployee(employee)
    }

    private fun saveEmployee(employee: Worker) {
        employeeFile.appendText("${employee.id}%${employee.name}%${employee.age}%${employee.position}\n")
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
        productFile.writeText("")

        for (card in cards) {
            saveProductCardToFile(card)
        }
    }

    private fun saveProductCardToFile(productCard: ProductCard) {
        productFile.appendText("${productCard.name}%${productCard.brand}%${productCard.price}%")
        when (productCard) {
            is FoodCard -> {
                productFile.appendText("${productCard.caloric}%")
            }

            is ApplianceCard -> {
                productFile.appendText("${productCard.wattage}%")
            }

            is ShoeCard -> {
                productFile.appendText("${productCard.size}%")
            }
        }
        productFile.appendText("${productCard.productType}\n")
    }

    private fun getAllProducts(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
        if (cards.isEmpty()){
            print("There no items found\n")
            work()
            return cards
        }
        if (!productFile.exists()) productFile.createNewFile()
        val allText = productFile.readText().trim()
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


    private fun showAllItems() {
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