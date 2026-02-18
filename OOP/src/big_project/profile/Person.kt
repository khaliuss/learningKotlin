package big_project.profile

import practice.files.file

class Person(
    private val name: String,
    private val height: Int,
    private val weight: Int
) {

    var age: Int = 0
        set(value) {
            if (value > field) {
                field = value
            } else {
                println("Age can't decrease")
            }
        }
        get() {
            println("It is impolite to ask about someone's age")
            return field
        }

    fun askAboutAge(): Int{
        return age
    }

    fun sayHello() {
        println("Hello! My name is $name!")
    }


    fun printInfo() {
        println("Name: $name Age:$age Sage Height: $height Weight: $weight")
    }

    fun run() {
        repeat(10) {
            print("Running... ")
        }
        println()
    }

}