package org.example.test

import kotlinx.serialization.json.Json
import java.io.File

var age: Int? = 20

fun main() {

    val handbook = mutableMapOf<String, String>()

    handbook["Andrew"] = "0505812126"
    handbook["Jimmy"] = "050523435"
    handbook.put("Caren", "050232345")

    print("Please put another one name: ")
    val name = readln().trim()
    print("Please put another one phone number: ")
    val phone = readln().trim()

    handbook.put(name, phone)

    takePhoneNumber(handbook)

}

fun takePhoneNumber(handbook: Map<String, String>) {

    while (true) {
        print("To find a number please put name or 0 to exit: ")
        val name = readln()
        if (name == "0") break
        handbook[name]?.let {
            println("tel: $it")
            break
        } ?: print("not found")
    }
}




