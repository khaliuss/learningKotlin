package V3

fun main() {
    val john = Person();

    print("Input your name: ")
    john.name = readln()

    print("Input your age: ")
    john.age = readln().toInt()

    print("Input your weight: ")
    john.weight = readln().toInt()

    print("Input your height: ")
    john.height = readln().toInt()

    print("Name: ${john.name} Age: ${john.age} Weight: ${john.weight} Height: ${john.height}")

    john.sayHello()
    println()
    john.run()

}