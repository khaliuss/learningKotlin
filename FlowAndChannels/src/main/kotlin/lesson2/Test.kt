package org.example.lesson2

import kotlin.random.Random

fun main() {

    var filterCount = 0
    var mapperCount = 0

   /* generateSequence(0){
        it + 1
    }*/

    /*generateSequence{
        Random.nextInt()
    }.*/

    sequence<Int>{
        println("Start generation")
        yield(0)
        println("Start random numbers")
        repeat(100){
            yield(Random.nextInt(1000))
        }
    }.filter {
        println("Filter: $it")
        filterCount++
        it % 2 == 0
    }.map {
        println("Mapper: $it")
        mapperCount++
        it * 10
    }.take(10).count()

    println("Filtered: $filterCount")
    println("Mapped: $mapperCount")
}