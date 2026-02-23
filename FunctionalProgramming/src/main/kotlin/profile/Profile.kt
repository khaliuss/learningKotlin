package org.example.profile

import kotlinx.serialization.json.Json
import java.io.File

fun main() {


    val persons = ProfilesRepository.profiles

    for (person in persons){
        println(person)
    }

}

