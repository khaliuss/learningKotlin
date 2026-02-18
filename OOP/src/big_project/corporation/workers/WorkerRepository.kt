package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition
import java.io.File

class WorkerRepository {

    private val employeeFile = File("employees.txt")

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

    fun changeSalary(id: Int,salary:Int) {
        val employees: MutableList<Worker> = getAllEmployees()
        employeeFile.writeText("")
        for (employee: Worker in employees) {
            if (employee.id == id) {
                employee.salary = salary
            }
            saveEmployee(employee)
        }
    }

    fun saveEmployee(employee: Worker) {
        saveEmployeesToFile(employee)
    }

    private fun saveEmployeesToFile(employee: Worker){
        employeeFile.appendText("${employee.id}%${employee.name}%${employee.age}%${employee.salary}%${employee.position}\n")
    }

    fun fireEmployee(id:Int) {
        val employees = getAllEmployees()
        for (employee in employees) {
            if (employee.id == id) {
                employees.remove(employee)
                break
            }
        }
        employeeFile.writeText("")

        for (employee in employees) {
            saveEmployee(employee)
        }
    }

}