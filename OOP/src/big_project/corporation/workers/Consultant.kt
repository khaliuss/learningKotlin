package big_project.corporation.workers

import big_project.corporation.enums.WorkerPosition
import kotlin.random.Random

class Consultant(
    id: Int,
    name: String,
    age: Int=0,
    salary:Int
) : Worker(id,name,age,salary,WorkerPosition.CONSULTANT), Cleaner {

    override fun work() {
        serveCustomers()
    }

    override fun copy(salary: Int,age: Int): Consultant {
        return Consultant(id,name,age,salary)
    }

    fun serveCustomers(): Int {
        val count = Random.nextInt(0, 100)
        repeat(count) {
            print("The customer is served....")
        }
        println()
        return count
    }

    fun sayHello() {
        print("Hello! My name is $name.")
        if (age > 0) {
            print(" I'm $age years old.\n")
        }
    }

    override fun clean() {
        println("My position is ${position.title}.I'm cleaning work space ")
    }

}

