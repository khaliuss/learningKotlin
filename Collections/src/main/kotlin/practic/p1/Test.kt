package org.example.practic.p1

fun main() {
    val arr = PracticeHashSet<Int>()
    repeat(100){
        arr.add(it)
    }
    repeat(100){
        arr.add(it)
    }
    for (a in  arr){
        println(a)
    }




}