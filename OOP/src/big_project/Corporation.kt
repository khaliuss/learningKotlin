package big_project

fun main() {

    val director = Director(name = "Andrey", age = 25)
    val consultant = Consultant(name = "John")
    director.getConsultantToWork(consultant)

//    val consultant = Consultant("Max")
//    consultant.serveCustomers()


}