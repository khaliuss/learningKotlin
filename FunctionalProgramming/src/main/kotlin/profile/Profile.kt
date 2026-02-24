package org.example.profile

import org.example.test.isPositive

fun main() {


    val persons = ProfilesRepository.profiles
        .myFilter { it.age > 25 }
        .myFilter { it.firstName.startsWith("A") }
        .myFilter { it.gender == Gender.MALE }
        .myFilter { it.age < 30 }
        .transform { it.copy(age = it.age + 1) }


    for (person in persons) {
        println(person)
    }

}

fun <T> List<Person>.transform(operation: (Person) -> T): List<T> {
    val result = mutableListOf<T>()
    for (person in this) {
        result.add(operation(person))
    }
    return result
}

fun List<Person>.myFilter(isSuitable: (Person) -> Boolean): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in this) {
        if (isSuitable(person)) {
            newPersons.add(person)
        }
    }
    return newPersons
}


