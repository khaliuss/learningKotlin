package org.example.products

fun main() {

    val productCards = ProductsRepository.productCards

    for (productCard in productCards){
        println(productCard)
    }

}