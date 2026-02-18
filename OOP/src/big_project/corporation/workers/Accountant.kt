package big_project.corporation.workers

import big_project.corporation.enums.OperationCode
import big_project.corporation.enums.ProductType
import big_project.corporation.enums.WorkerPosition
import big_project.corporation.shop.ApplianceCard
import big_project.corporation.shop.FoodCard
import big_project.corporation.shop.ProductCard
import big_project.corporation.shop.ShoeCard

class Accountant(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id, name, age, WorkerPosition.ACCOUNTANT), Cleaner, Supplier {

    private val repositoryWorker = WorkerRepository()
    private val repositoryProduct = ProductRepository()


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
                OperationCode.EXIT -> {
                    repositoryWorker.saveChanges()
                    repositoryProduct.saveChanges()
                    break
                }
                OperationCode.REGISTER_NEW_ITEM -> registerNewItem()
                OperationCode.SHOW_ALL_ITEMS -> showAllItems()
                OperationCode.REMOVE_PRODUCT_CARD -> removeProductCard()
                OperationCode.REGISTER_NEW_EMPLOYEE -> registerNewEmployee()
                OperationCode.FIRE_AN_EMPLOYEE -> fireEmployee()
                OperationCode.SHOW_ALL_EMPLOYEES -> showAllEmployees()
                OperationCode.CHANGE_SALARY -> changeSalary()
            }
        }

    }

    private fun changeSalary() {
        print("Enter employee's id to change salary: ")
        val id: Int = readln().toInt()
        print("Enter new salary: ")
        val salary: Int = readln().toInt()
        repositoryWorker.changeSalary(id, salary)
    }

    private fun showAllEmployees() {
        val employees = repositoryWorker.workers
        for (employee in employees) {
            employee.personInf()
        }
    }

    private fun fireEmployee() {
        print("Enter employee's id to fire: ")
        val id = readln().toInt()
        repositoryWorker.fireEmployee(id)
    }


    private fun registerNewEmployee() {
        val workerTypes = WorkerPosition.entries
        print("Choose position -")
        for ((index, type) in workerTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index < workerTypes.size - 1) {
                print(", ")
            } else {
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
        print("Enter salary:")
        val salary = readln().toInt()

        val employee = when (workerType) {
            WorkerPosition.DIRECTOR -> Director(id, name, age)
            WorkerPosition.ACCOUNTANT -> Accountant(id, name, age)
            WorkerPosition.ASSISTANT -> Assistant(id, name, age)
            WorkerPosition.CONSULTANT -> Consultant(id, name, age)
        }
        employee.salary = salary
        repositoryWorker.registerNewEmployee(employee)
    }


    private fun removeProductCard() {
        print("Enter name of card for removing: ")
        val name = readln().trim()
        repositoryProduct.removeProductCard(name)
    }

    private fun saveProduct(productCard: ProductCard) {
        repositoryProduct.saveProduct(productCard)
    }


    private fun showAllItems() {
        val productCards = repositoryProduct.products
        for (card in productCards) {
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
        saveProduct(productCard)
    }

    override fun clean() {
        println("My position is ${position.title}.I'm cleaning work space ")
    }

    override fun buy() {
        println("My position is ${position.title}.I'm buying things ")
    }


}