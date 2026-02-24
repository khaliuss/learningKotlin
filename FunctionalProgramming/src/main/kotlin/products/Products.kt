package org.example.products

import org.example.profile.Person
import products.ProductBrandA

fun main() {

    val productCards = ProductsRepository.productCards
        .filter{it.category == "Toys"}
        .transform {it.copy(price = it.price*2)  }
        .transform { "${it.id} - ${it.name} - ${it.price}" }


    for (productCard in productCards) {
        println(productCard)
    }

}

fun <T> List<ProductCard>.transform(operation:(ProductCard)->T): List<T>{
    val result= mutableListOf<T>()
    for (product in this){
        result.add(operation(product))
    }
    return result
}

fun List<ProductCard>.filter(isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    val filteredProductCards = mutableListOf<ProductCard>()
    for (product in this) {
        if (isSuitable(product)) {
            filteredProductCards.add(product)
        }
    }
    return filteredProductCards
}