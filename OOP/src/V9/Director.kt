package V9

class Director(val name: String, val age: Int) {

    fun takeCoffee(assistant: Assistant,coffeeType:String) {
        assistant.bringCoffee(coffeeType = coffeeType)
        println("Thank you, ${assistant.name}! The $coffeeType is very tasty :)")
    }


}