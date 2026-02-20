package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

class Director(
    id: Int,
    name: String,
    age: Int,
    salary:Int
): Worker(id,name,age,salary,WorkerPosition.DIRECTOR),Supplier{

    override fun work() {
        println("I'm drinking coffee...")
    }

    override fun copy(salary: Int,age: Int): Director {
        return Director(id,name,age,salary)
    }

    fun takeCoffee(assistant: Assistant, drinkName: String="Cappuccino") {
        val drinkName = assistant.bringCoffee()
        println("Thank you, ${assistant.name}! The $drinkName is very tasty :)")
    }

    fun getConsultantToWork(consultant: Consultant) {
        val count = consultant.serveCustomers()
        println("Consultant ${consultant.name} served $count clients ")
    }

    override fun buy() {
        println("My position is ${position.title}.I'm buying things ")
    }
}