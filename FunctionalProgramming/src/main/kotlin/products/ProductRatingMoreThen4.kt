package org.example.products

class ProductRatingMoreThen4 : Condition {
    override fun isSuitable(productCard: ProductCard): Boolean {
        return productCard.rating > 4
    }
}