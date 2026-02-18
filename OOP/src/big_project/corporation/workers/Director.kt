package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

class Director(
    id: Int,
    name: String,
    age: Int
): Worker(id,name,age,WorkerPosition.DIRECTOR) {

    override fun work() {
        println("I'm drinking coffee...")
    }

    fun takeCoffee(assistant: Assistant, drinkName: String="Cappuccino") {
        val drinkName = assistant.bringCoffee()
        println("Thank you, ${assistant.name}! The $drinkName is very tasty :)")
    }

    fun getConsultantToWork(consultant: Consultant) {
        val count = consultant.serveCustomers()
        println("Consultant ${consultant.name} served $count clients ")
    }
}