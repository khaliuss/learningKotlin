package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

open class Worker(
    val id:Int,
    val name: String,
    val age: Int = 0,
    val position : WorkerPosition
) {

    open fun work() {
        println("I'm working now...")
    }

    open fun personInf(){
        println("id=$id, name='$name', age=$age, position=$position")
    }
}