package big_project.cats

fun main() {
    val cat = Cat("Sam")
    println(cat.legsCount)
    println(cat.name)
    val lion = Lion(countInPride = 25)
    println(lion.countInPride)
    println(lion.legsCount)
    cat.playWithMouse()
}