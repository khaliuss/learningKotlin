package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

abstract class Worker(
    val id:Int,
    val name: String,
    val age: Int = 0,
    val position : WorkerPosition
) {

    abstract fun work()

    fun personInf(){
        println("id=$id, name='$name', age=$age, position=$position")
    }
}