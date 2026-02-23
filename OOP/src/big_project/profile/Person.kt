package big_project.profile

import practice.files.file

data class Person(
    val name: String,
    var secondName: String,
    val height: Int,
    val age: Int,
    val weight: Int
) {


    val fullName: String
        get() = "$name $secondName"

    fun sayHello() {
        println("Hello! My name is $name $secondName!")
    }

//    var age: Int = 0
//    set(value : Int ) {
//        if (value > field) {
//            field = value
//        } else {
//            println("The new age must be bigger than the old one")
//        }
//    }
//    get () {
//        println("It is indecent to ask a person his age")
//        return field
//    }

    fun printInfo() {
        println("Name: $name Second name: $secondName  Age:$age Sage Height: $height Weight: $weight")
    }

    fun run() {
        repeat(10) {
            print("Running... ")
        }
        println()
    }

}