package org.example.profile

import org.example.extentions.filter
import org.example.extentions.myForEach


fun main() {

    print("Please put id: ")
    val id = readln().trim().toInt()

    ProfilesRepository.profiles
        .filter { it.age > 25 }
//        .filter { it.firstName.startsWith("A") }
        .filter { it.gender == Gender.MALE }
        .filter { it.age < 30 }
        .map { it.copy(age = it.age + 1) }
        .sortedBy { it.firstName }
        .sortedByDescending { it.age }
        .showEmail(id)
//        .myForEach { println(it) }

//        .forEach { println(it) }

}

inline fun List<Person>.showEmail(id:Int): Unit{
    var temp = ""
    for (item in this){
        if (item.id == id){
            temp = item.email
            break
        }else{
            temp = "The user is not found"
        }
    }
    println(temp)
}





