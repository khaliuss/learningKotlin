package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition
import java.io.File

class WorkerRepository {

    private val employeeFile = File("employees.txt")
    val workers = getAllEmployees()


    fun getAllEmployees(): MutableList<Worker> {
        val employees = mutableListOf<Worker>()

        if (!employeeFile.exists()) employeeFile.createNewFile()
        val allText = employeeFile.readText().trim()
        if (allText.isEmpty()) return employees

        val lines = allText.split("\n")
        for (line in lines) {
            val properties = line.split("%")
            val id = properties[0].toInt()
            val name = properties[1]
            val age = properties[2].toInt()
            val salary = properties[3].toInt()
            val type = properties.last()

            val position = WorkerPosition.valueOf(type)

            val worker = when (position) {
                WorkerPosition.DIRECTOR -> Director(id, name, age)
                WorkerPosition.ACCOUNTANT -> Accountant(id, name, age)
                WorkerPosition.ASSISTANT -> Assistant(id, name, age)
                WorkerPosition.CONSULTANT -> Consultant(id, name, age)
            }
            worker.salary = salary
            employees.add(worker)
        }

        return employees
    }

    fun changeSalary(id: Int, salary: Int) {
        for (worker in workers) {
            if (worker.id == id) {
                worker.salary = salary
            }
        }
    }

    fun registerNewEmployee(employee: Worker) {
        workers.add(employee)
    }

    fun saveChanges() {
        val workersString = StringBuilder()
        for (worker in workers){
            workersString.append("${worker.id}%${worker.name}%${worker.age}%${worker.salary}%${worker.position}\n")
        }
        employeeFile.writeText(workersString.toString())
    }

    fun fireEmployee(id: Int) {
        for (worker in workers) {
            if (worker.id == id) {
                workers.remove(worker)
                break
            }
        }
    }

}