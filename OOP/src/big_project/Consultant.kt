package big_project

class Consultant(
    val name: String,
    val age: Int = 0
) {

    fun sayHello(){
        print("Hello! My name is $name.")
        if (age > 0) {
            print(" I'm $age years old.\n")
        }
    }

}

