package big_project.profile

import practice.files.file

class Person(
    val name: String,
    var secondName: String,
    val height: Int,
    var age: Int = 0,
    val weight: Int
) {


    fun copy(name: String,secondName: String,height: Int,age: Int,weight: Int): Person{

        if (age < this.age) {
            println("Age can't be decrease")
            return Person(name,secondName,height,this.age,weight)
        }

        return Person(name,secondName,height,age,weight)
    }


    val fullName: String
        get() = "$name $secondName"

    fun sayHello() {
        println("Hello! My name is $name $secondName!")
    }


    fun printInfo() {
        println("Name: $name Second name: $secondName  Age:$age Sage Height: $height Weight: $weight")
    }

    fun run() {
        repeat(10) {
            print("Running... ")
        }
        println()
    }

    override fun equals(other: Any?): Boolean {

        if (other !is Person) return false

        return name == other.name && secondName == other.secondName && height == other.height && age == other. age && weight == other.weight
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + age
        result = 31 * result + weight
        result = 31 * result + name.hashCode()
        result = 31 * result + secondName.hashCode()
        return result
    }


}