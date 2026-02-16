package big_project

class Director(
    val name: String, val age: Int
) {

    fun takeCoffee(assistant: Assistant, drinkName: String) {
        val drinkName = assistant.bringCoffee()
        println("Thank you, ${assistant.name}! The $drinkName is very tasty :)")
    }

    fun getConsultantToWork(consultant: Consultant) {
        val count = consultant.serveCustomers()
        println("Consultant ${consultant.name} served $count clients ")
    }
}