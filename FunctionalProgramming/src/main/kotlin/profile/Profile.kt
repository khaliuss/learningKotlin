package org.example.profile

import kotlinx.serialization.json.Json
import java.io.File

fun main() {


    val persons = ProfilesRepository.profiles

    var filtered = filter(persons) { it.age > 25 }
    filtered = filter(filtered) { it.firstName.startsWith("A") }
    filtered = filter(filtered) { it.gender == Gender.MALE }



    for (person in filtered) {
        println(person)
    }

}

fun filter(persons: List<Person>, isSuitable: (Person) -> Boolean): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (isSuitable(person)) {
            newPersons.add(person)
        }
    }
    return newPersons
}

//fun filter(persons: List<Person>,condition: Condition): List<Person> {
//    val newPersons = mutableListOf<Person>()
//    for (person in persons) {
//        if (condition.isSuitable(person)){
//            newPersons.add(person)
//        }
//    }
//    return newPersons
//}


