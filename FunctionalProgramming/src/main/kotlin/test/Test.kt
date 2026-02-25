package org.example.test

import kotlinx.serialization.json.Json
import java.io.File

var age: Int? = 20

fun main() {
    exampleWith()

}

fun exampleWith() {
    with(mutableListOf<Int>()) {
        while (true) {
            print("Enter number or 0 to exit: ")
            val number: Int = readln().toInt().takeIf { it != 0 } ?: break
            add(number)
        }
        println("Max: ${max()}")
        println("Min: ${min()}")
        this
    }.forEach {
        println(it)
    }
}

fun exampleApply() {
    mutableListOf<Int>().apply {
        while (true) {
            print("Enter number or 0 to exit: ")
            val number: Int = readln().toInt().takeIf { it != 0 } ?: break
            add(number)
        }
    }.forEach(::println)
}




