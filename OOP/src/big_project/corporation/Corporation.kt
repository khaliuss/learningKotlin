package big_project.corporation

import big_project.corporation.workers.WorkerRepository

fun main() {

    val assistant  = WorkerRepository.findAssistant()
    val director  = WorkerRepository.findDirector()
    assistant?.printInfo()
    director?.printInfo()
    if (assistant != null){
        director?.takeCoffee(assistant,"Cappuccino")
    }

    val directorSalary = director?.salary ?: 0
    val assistantSalary = assistant?.salary ?: 0

    val sum = directorSalary + assistantSalary


}