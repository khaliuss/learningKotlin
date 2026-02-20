package big_project.profile

fun main() {
    val person1 = Person ("John", "Smith",180,30,190)
    val person2 = Person ("John", "Smith",180,30,190)
    val person3 : Person = person1.copy(name = "Nick")
    val people : Set<Person> = setOf(person1, person2, person3)
    for (person : Person in people) {
        println(person)
    }



}