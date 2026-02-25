package org.example.dictionary

import kotlinx.serialization.json.Json
import java.io.File
import java.util.Dictionary

fun main() {
    val file = File("dictionary.json")
    val content = file.readText().trim()
    val dictionary = Json.decodeFromString<List<Entry>>(content)

    showDescription(dictionary)

}

fun showDescription(dictionary: List<Entry>){
    while (true){
        print("Please enter word: ")
        val word = readln().trim().lowercase()
        if (word == "0") break
        dictionary.find { it.value == word }?.let { println(it.description) } ?: println("Not found")

    }
}