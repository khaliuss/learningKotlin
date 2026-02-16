package big_project.profile

class Person {

    val name: String
    val age: Int
    val height: Int
    val weight: Int

    fun sayHello() {
        println("Hello! My name is $name!")
    }

    constructor(name: String, age: Int, height: Int, weight: Int) {
        this.name = name
        this.age = age
        this.height = height
        this.weight = weight
    }
    fun printInfo() {
        println("Name: $name Age: Sage Height: $height Weight: $weight")
    }

    fun run() {
        repeat(10) {
            print("Running... ")
        }
        println()
    }

}