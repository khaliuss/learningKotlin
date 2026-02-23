package org.example.products

import kotlinx.serialization.json.Json
import java.io.File

object ProductsRepository {

    private val file = File("product_card.json")

    private val _productCards = loadProductCard()

    val productCards
        get()= _productCards


    fun loadProductCard() : MutableList<ProductCard>{
        val content= file.readText().trim()
        return Json.decodeFromString(content)
    }

}