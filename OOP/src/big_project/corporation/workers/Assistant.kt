package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

class Assistant(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id, name, age, WorkerPosition.ASSISTANT), Cleaner, Supplier {

    override fun work() {
        println("I'm answering the phone now....")
    }

    fun bringCoffee(drinkName: String = "Cappuccino", count: Int = 1): String {
        repeat(count) {
            println("Get up")
            println("Go to the coffee machine")
            println("Press the \"$drinkName\" button")
            println("Wait for the $drinkName to be prepared")
            println("Take coffee")
            println("Bring coffee to the director")
            println("Put coffee on the table")
            println("Return to the workplace")
        }
        return drinkName
    }

    override fun clean() {
        println("My position is ${position.title}.I'm cleaning work space ")
    }

    override fun buy() {
        println("My position is ${position.title}.I'm buying things ")
    }

}