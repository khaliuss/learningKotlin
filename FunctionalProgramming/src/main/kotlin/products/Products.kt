package org.example.products

import org.example.extentions.filter

fun main() {

    val productCards = ProductsRepository.productCards
        .filter{it.category == "Toys"}
        .map {it.copy(price = it.price*2)  }
        .map { "${it.id} - ${it.name} - ${it.price}" }


    for (productCard in productCards) {
        println(productCard)
    }

}



