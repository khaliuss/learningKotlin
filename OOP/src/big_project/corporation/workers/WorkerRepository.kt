package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition
import java.io.File

object WorkerRepository {

    private val employeeFile = File("employees.txt")
    private val _workers = getAllEmployees()
    val workers
        get()=_workers.toList()



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
                WorkerPosition.DIRECTOR -> Director(id, name, age,salary)
                WorkerPosition.ACCOUNTANT -> Accountant(id, name, age,salary)
                WorkerPosition.ASSISTANT -> Assistant(id, name, age,salary)
                WorkerPosition.CONSULTANT -> Consultant(id, name, age,salary)
            }
            employees.add(worker)
        }

        return employees
    }

    fun changeSalary(id: Int, salary: Int) {
        for ((index,worker) in _workers.withIndex()) {
            if (worker.id == id) {
                if (salary < worker.salary) return
                val newWorker = worker.copy(salary = salary)
                _workers[index] = newWorker
            }
        }
    }

    fun changeAge(id: Int, age: Int) {
        for ((index,worker) in _workers.withIndex()) {
            if (worker.id == id) {
                if (age < worker.age) return
                val newWorker = worker.copy(age = age)
                _workers[index] = newWorker
            }
        }
    }

    fun registerNewEmployee(newWorker: Worker) {
        for (worker in _workers){
            if (newWorker == worker) return
        }
        _workers.add(newWorker)
    }

    fun saveChanges() {
        val workersString = StringBuilder()
        for (worker in _workers){
            workersString.append("${worker.id}%${worker.name}%${worker.age}%${worker.salary}%${worker.position}\n")
        }
        employeeFile.writeText(workersString.toString())
    }

    fun fireEmployee(id: Int) {
        for (worker in _workers) {
            if (worker.id == id) {
                _workers.remove(worker)
                break
            }
        }
    }

}