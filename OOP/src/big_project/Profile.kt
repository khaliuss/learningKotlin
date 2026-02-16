package big_project

fun main() {
    print("Input 1st name: ")
    val firstName = readln()
    print("Input 2nd name: ")
    val secondName = readln()
    print("Input Ist age: ")
    val firstAge = readln().toInt()
    print("Input 2nd age: ")
    val secondAge = readln().toInt()
    print("Input Ist height: ")
    val firstHeight = readln().toInt()
    print("Input 2nd height: ")
    val secondHeight = readln().toInt()
    print("Input 1st weight: ")
    val firstWeight = readln().toInt()
    print("Input 2nd weight: ")
    val secondWeight = readln().toInt()

    val first = Person(name = firstName, age = firstAge, height = firstHeight, weight = firstWeight)
    val second = Person(name = secondName, age = secondAge, height = secondHeight, weight = secondWeight)


    first.printInfo()
    second.printInfo()

    first.sayHello()
    second.sayHello()

}