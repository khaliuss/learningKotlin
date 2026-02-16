package big_project

class Person {

    var name: String = ""
    var age: Int = 0
    var height: Int = 0
    var weight: Int = 0

    fun sayHello() {
        println("Hello! My name is $name!")
    }

    fun init(name: String, age: Int, height: Int, weight: Int) {
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