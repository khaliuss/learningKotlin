package big_project.corporation

fun main() {

    val director: Worker = Director(name = "Andrey", age = 25)
    val consultant: Worker = Consultant(name = "Max")
    val assistant: Worker = Assistant("Helen", 20)
    (assistant as Assistant).bringCoffee()
    (director as Director).getConsultantToWork(consultant as Consultant)
    director.takeCoffee(assistant)
    println(director.name)


}