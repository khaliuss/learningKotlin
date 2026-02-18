package big_project.profile

fun main() {
    print("Input 1st name: ")
    val firstName = readln()
    print("Input 1st second name: ")
    val secondName = readln()
    print("Input Ist age: ")
    val firstAge = readln().toInt()
    print("Input Ist height: ")
    val firstHeight = readln().toInt()
    print("Input 1st weight: ")
    val firstWeight = readln().toInt()


    val first = Person(name = firstName, secondName , height = firstHeight, weight = firstWeight)

    first.age = 12

    first.printInfo()

    first.sayHello()

    first.age=7
    first.age

    first.secondName = "Unknown"

    print("fullName -------> ${first.fullName}")


}