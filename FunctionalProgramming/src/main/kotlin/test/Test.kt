package org.example.test

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val a :Int = readln().toInt()
    println(isPositive(a))
}

fun isPositive(number: Int) = number > 0