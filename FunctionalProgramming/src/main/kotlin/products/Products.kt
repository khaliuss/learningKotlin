package org.example.products

import org.example.extentions.filter

fun main() {

    ProductsRepository.productCards
        .filter{it.category == "Toys"}
        .map {it.copy(price = it.price*2)  }
        .map { "${it.id} - ${it.name} - ${it.price}" }
        .forEach { println(it) }




}



