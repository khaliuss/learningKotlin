package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

abstract class Worker(
    open val id: Int,
    open val name: String,
    open val age: Int = 0,
    open val salary: Int = 15000,
    val position: WorkerPosition,
) {

    abstract fun copy(
        id: Int = this.id,
        name: String = this.name,
        age: Int = this.age,
        salary: Int = this.salary,
        position: WorkerPosition = this.position
    ): Worker


    abstract fun work()

    fun printInfo() {
        println(this)
    }

}