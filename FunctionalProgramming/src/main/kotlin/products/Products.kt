package org.example.products

import products.ProductBrandA

fun main() {

    val productCards = ProductsRepository.productCards

    var filtered = filter(productCards, object : Condition {
        override fun isSuitable(productCard: ProductCard): Boolean {
            return productCard.rating > 4
        }

    })
    filtered = filter(filtered, object : Condition {
        override fun isSuitable(productCard: ProductCard): Boolean {
            return productCard.price > 500
        }
    })
    filtered = filter(filtered, object : Condition {
        override fun isSuitable(productCard: ProductCard): Boolean {
            return productCard.brand == Brand.BRAND_A
        }
    })

    for (productCard in filtered) {
        println(productCard)
    }

}

fun filter(productCards: List<ProductCard>, condition: Condition): List<ProductCard> {

    val filteredProductCards = mutableListOf<ProductCard>()

    for (product in productCards) {
        if (condition.isSuitable(product)) {
            filteredProductCards.add(product)
        }
    }

    return filteredProductCards
}