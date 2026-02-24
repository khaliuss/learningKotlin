package org.example.profile

import org.example.extentions.filter


fun main() {


    val persons = ProfilesRepository.profiles
        .filter { it.age > 25 }
        .filter { it.firstName.startsWith("A") }
        .filter { it.gender == Gender.MALE }
        .filter { it.age < 30 }
        .map { it.copy(age = it.age + 1) }



    for (person in persons) {
        println(person)
    }

}



