package org.example.profile

import kotlinx.serialization.json.Json
import java.io.File

fun main() {


    val persons = ProfilesRepository.profiles

    var filtered = filterOlderThen25(persons)
    filtered = filterNameStartsWithA(filtered)
    filtered = filterMale(filtered)

    for (person in filtered){
        println(person)
    }

}

fun filterMale(persons: List<Person>): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (person.gender == Gender.MALE){
            newPersons.add(person)
        }
    }
    return newPersons
}

fun filterNameStartsWithA(persons: List<Person>): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (person.firstName.startsWith("A")){
            newPersons.add(person)
        }
    }
    return newPersons
}

fun filterOlderThen25(persons: List<Person>): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (person.age<25){
            newPersons.add(person)
        }
    }
    return newPersons
}

