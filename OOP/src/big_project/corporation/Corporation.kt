package big_project.corporation

fun main() {

    val director = Director(name = "Andrey", age = 25)
    val consultant = Consultant(name = "John")
    val assistant = Assistant ( "Helen",20)
    director.getConsultantToWork(consultant)
    director.takeCoffee(assistant)

//    val consultant = Consultant("Max")
//    consultant.serveCustomers()


}