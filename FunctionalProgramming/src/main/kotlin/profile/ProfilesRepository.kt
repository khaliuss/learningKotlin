package org.example.profile

import kotlinx.serialization.json.Json
import java.io.File

object ProfilesRepository {

    private val file  = File("profiles.json")

    private val _profiles = loadProfiles()

    val profiles
        get () = _profiles.toList()


    private fun loadProfiles(): List<Person>{
        val content = file.readText().trim()
        val persons = Json.decodeFromString<List<Person>>(content)
        return persons
    }

//    private fun registerProfile(){
//        file.appendText()
//        val content = file.readText().trim()
//        val persons = Json.decodeFromString<List<Person>>(content)
//        return persons
//    }

}