package big_project.corporation

class Director(
    name: String,
    age: Int
): Worker(name,age) {

    fun takeCoffee(assistant: Assistant, drinkName: String="Cappuccino") {
        val drinkName = assistant.bringCoffee()
        println("Thank you, ${assistant.name}! The $drinkName is very tasty :)")
    }

    fun getConsultantToWork(consultant: Consultant) {
        val count = consultant.serveCustomers()
        println("Consultant ${consultant.name} served $count clients ")
    }
}