package org.example.products

import org.example.profile.Person

interface Condition {

    fun isSuitable(productCard: ProductCard): Boolean

}