package V9

class Assistant(val name:String) {

    fun bringCoffee(count: Int = 1, coffeeType: String = "Cappuccino") {
        repeat(count) {
            println("Get up")
            println("Go to the coffee machine")
            println("Press the \"$coffeeType\" button")
            println("Wait for the $coffeeType to be prepared")
            println("Take coffee")
            println("Bring coffee to the director")
            println("Put coffee on the table")
            println("Return to the workplace")
        }

    }

}