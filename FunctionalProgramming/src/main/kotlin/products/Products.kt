package org.example.products

import org.example.profile.Person
import products.ProductBrandA

fun main() {

    val productCards = ProductsRepository.productCards

//    var filtered = filter(productCards){it.rating > 4}
//    filtered = filter(filtered){it.price > 500}
//    filtered = filter(filtered){it.brand > Brand.BRAND_A }

    val filtered = filter(productCards) { it.category == "Toys" }

    val transformed = transform(filtered){it.copy(price = it.price*2)}

    val res = transform(transformed){"${it.id} - ${it.name} - ${it.price}"}

    for (productCard in res) {
        println(productCard)
    }

}

fun <T> transform(products: List<ProductCard>,operation:(ProductCard)->T): List<T>{
    val result= mutableListOf<T>()
    for (product in products){
        result.add(operation(product))
    }
    return result
}

fun filter(productCards: List<ProductCard>, isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    val filteredProductCards = mutableListOf<ProductCard>()
    for (product in productCards) {
        if (isSuitable(product)) {
            filteredProductCards.add(product)
        }
    }
    return filteredProductCards
}