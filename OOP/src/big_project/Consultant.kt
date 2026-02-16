package big_project

import kotlin.random.Random

class Consultant(
    val name: String,
    val age: Int = 0
) {

    fun serveCustomers():Int {
        val count = Random.nextInt(  0,  100)
        repeat (count) {
            print("The customer is served....")
        }
        println()
        return count
    }

    fun sayHello(){
        print("Hello! My name is $name.")
        if (age > 0) {
            print(" I'm $age years old.\n")
        }
    }

}

