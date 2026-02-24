package org.example.profile

import kotlinx.serialization.json.Json
import java.io.File

fun main() {


    val persons = ProfilesRepository.profiles

    var filtered = filter(persons, object: Condition{
        override fun isSuitable(person: Person): Boolean {
            return person.age > 25
        }
    })

     filtered = filter(filtered, object: Condition{
         override fun isSuitable(person: Person): Boolean {
             return person.firstName.startsWith("A")
         }

     })
     filtered = filter(filtered, object : Condition{
         override fun isSuitable(person: Person): Boolean {
             return person.gender == Gender.MALE
         }
     })



    for (person in filtered){
        println(person)
    }

}

fun filter(persons: List<Person>,condition: Condition): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (condition.isSuitable(person)){
            newPersons.add(person)
        }
    }
    return newPersons
}


