package org.example.test

import kotlinx.serialization.json.Json
import java.io.File

var age: Int? = 20

fun main() {

//                                   не изменяемая коллекция
//
//    val dictionary: Map<String, String> = mapOf(
//
//        "hello" to "bonjour",                       //  we use ether to or ether Pair
//        Pair("thank you", "merci")  //  like here
//
//    )

    val dictionary = mutableMapOf<String, String>()

//    dictionary.put("Hello","Bonjour")
    dictionary["Hello"] = "Bonjour"

//    for (entry in dictionary){
//        println(entry.value)
//    }

    val keys = dictionary.keys

    for (key in keys){
        println(key)
    }

    val values = dictionary.values

    for (value in values){
        println(value)
    }






}




