package org.example.products

import org.example.profile.Person
import products.ProductBrandA

fun main() {

    val productCards = ProductsRepository.productCards

    var filtered = filter(productCards){it.rating > 4}
    filtered = filter(filtered){it.price > 500}
    filtered = filter(filtered){it.brand > Brand.BRAND_A }


    for (productCard in filtered) {
        println(productCard)
    }

}

fun filter(productCards: List<ProductCard>, isSuitable : (ProductCard)-> Boolean): List<ProductCard> {
    val filteredProductCards = mutableListOf<ProductCard>()
    for (product in productCards) {
        if (isSuitable(product)) {
            filteredProductCards.add(product)
        }
    }
    return filteredProductCards
}