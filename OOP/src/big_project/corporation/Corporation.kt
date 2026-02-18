package big_project.corporation

import big_project.corporation.workers.Accountant
import big_project.corporation.workers.Assistant
import big_project.corporation.workers.Consultant
import big_project.corporation.workers.Director
import big_project.corporation.workers.Worker

fun main() {

    /*val director = Director(name = "Andrey", age = 25)

    val consultant = Consultant(name = "Max")

    val assistant = Assistant("Helen", 20)

    val accountant = Accountant("Christian", 30)

    val employees = listOf<Worker>(director, consultant, assistant, accountant)

    for (employee in employees) {
        employee.work()
    }*/

    val accountant = Accountant(0,"Helen", 20)
    accountant.work()



}