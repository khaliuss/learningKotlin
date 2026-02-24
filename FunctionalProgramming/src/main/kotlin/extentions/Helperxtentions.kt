package org.example.extentions

import org.example.products.ProductCard

fun <T,R> Iterable<T>.transform(operation:(T)->R): List<R>{
    val result= mutableListOf<R>()
    for (product in this){
        result.add(operation(product))
    }
    return result
}

fun <T> Iterable<T>.filter(isSuitable: (T) -> Boolean): List<T> {
    val filteredProductCards = mutableListOf<T>()
    for (product in this) {
        if (isSuitable(product)) {
            filteredProductCards.add(product)
        }
    }
    return filteredProductCards
}