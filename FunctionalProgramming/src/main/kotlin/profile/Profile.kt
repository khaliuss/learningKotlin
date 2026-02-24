package org.example.profile

import org.example.extentions.filter


fun main() {


    val persons = ProfilesRepository.profiles
        .filter { it.age > 25 }
        .filter { it.firstName.startsWith("A") }
        .filter { it.gender == Gender.MALE }
        .filter { it.age < 30 }
        .map { it.copy(age = it.age + 1) }

    myFilter(persons, { person -> person.age==23 })

    for (person in persons) {
        println(person)
    }

}


fun myFilter(persons :List<Person>,isSuitable: (Person) -> Boolean): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (isSuitable(person)) {
            newPersons.add(person)
        }
    }
    return newPersons
}


