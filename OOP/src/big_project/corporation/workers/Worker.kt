package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

abstract class Worker(
    open val id:Int,
    open val name: String,
    open val age: Int = 0,
    open val salary: Int = 15000,
    val position : WorkerPosition,
) {

    abstract fun copy(salary: Int = this.salary,age: Int = this.age) : Worker


    abstract fun work()

    fun personInf(){
        println(this)
    }

}