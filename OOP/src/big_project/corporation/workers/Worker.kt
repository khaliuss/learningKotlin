package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

abstract class Worker(
    val id:Int,
    val name: String,
    val age: Int = 0,
    val position : WorkerPosition,
) {

    var salary: Int = 15000
        set (value) {
        if (value < field) {
            println("The new salary is too small...")
        } else {
            field = value
        }
    }

    abstract fun work()

    fun personInf(){
        print(this)
    }

    override fun toString(): String {
        return "id=$id, name='$name', age=$age, position=${position.title}, salary=$salary\n"
    }


}