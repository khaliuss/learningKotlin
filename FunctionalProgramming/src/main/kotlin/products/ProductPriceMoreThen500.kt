package org.example.products

class ProductPriceMoreThen500 : Condition {

    override fun isSuitable(productCard: ProductCard): Boolean {
        return productCard.price > 500
    }

}