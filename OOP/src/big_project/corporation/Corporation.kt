package big_project.corporation

fun main() {

    val director = Director(name = "Andrey", age = 25)
    val consultant = Consultant(name = "Max")
    val assistant = Assistant("Helen", 20)

    val employees = listOf<Worker>(director, consultant, assistant)
    for (employee in employees) {
        employee.work()
    }

}