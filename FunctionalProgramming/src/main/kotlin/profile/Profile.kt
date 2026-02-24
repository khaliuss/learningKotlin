package org.example.profile

import org.example.extentions.filter
import org.example.extentions.myForEach


fun main() {
    showEmail()
}


fun filterCollection() {
    ProfilesRepository.profiles
        .filter { it.age > 25 }
        .filter { it.firstName.startsWith("A") }
        .filter { it.gender == Gender.MALE }
        .filter { it.age < 30 }
        .map { it.copy(age = it.age + 1) }
        .sortedBy { it.firstName }
        .sortedByDescending { it.age }
        .forEach { println(it) }
}

fun showEmail() {
    print("Please put id: ")
    val id = readln().trim().toInt()
    ProfilesRepository.profiles.find {
        it.id == id
    }?.let {
        println(it.email)
    } ?: println("Not Found")

}





