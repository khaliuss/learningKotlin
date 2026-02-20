package big_project.profile

import practice.files.file

class Person(
    val name: String,
    var secondName: String,
    val height: Int,
    val age: Int,
    val weight: Int
) {


    fun copy(
        name: String = this.name,
        secondName: String = this.secondName,
        height: Int = this.height,
        age: Int = this.age,
        weight: Int = this.weight
    ): Person {

        if (age < this.age) {
            println("Age can't be decrease")
            return Person(name, secondName, height, this.age, weight)
        }

        return Person(name, secondName, height, age, weight)
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

        return name == other.name && secondName == other.secondName && height == other.height && age == other.age && weight == other.weight
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + age
        result = 31 * result + weight
        result = 31 * result + name.hashCode()
        result = 31 * result + secondName.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(name='$name', secondName='$secondName', height=$height, age=$age, weight=$weight, fullName='$fullName')"
    }


}