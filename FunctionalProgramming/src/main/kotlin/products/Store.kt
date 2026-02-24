package org.example.products

import org.example.extentions.filter
import org.example.extentions.myAlso

fun main() {

//    ProductsRepository.productCards
//        .filter{it.category == "Toys"}
//        .map {it.copy(price = it.price*2)  }
//        .map { "${it.id} - ${it.name} - ${it.price}" }
//        .forEach { println(it) }

    ProductsRepository.productCards.also {
        println("Filter by category CLOTHING")
    }.filter { it.category == "Toys" }.also {
        println("Increase price")
    }.map { it.copy(price = it.price * 2) }.also {
        println("Convert to strings")
    }.map { "${it.id} - ${it.name} - ${it.price}" }.also {
        println("Print info")
    }.forEach {
        println(it)
    }

}




