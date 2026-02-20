package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition

abstract class Worker(
    val id:Int,
    val name: String,
    val age: Int = 0,
    val salary: Int = 15000,
    val position : WorkerPosition,
) {

    abstract fun copy(salary: Int = this.salary,age: Int = this.age) : Worker


    abstract fun work()

    fun personInf(){
        print(this)
    }

    override fun toString(): String {
        return "id=$id, name='$name', age=$age, position=${position.title}, salary=$salary\n"
    }


}