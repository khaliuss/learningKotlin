package org.example.products

import jdk.jfr.Description
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.IDN

@Serializable
data class ProductCard (
    @SerialName("id") val id: Int,
    @SerialName("product_name") val name: String,
    @SerialName("product_category") val category: String,
    @SerialName("product_price") val price: Double,
    @SerialName("product_description") val description: String,
    @SerialName("product_brand") val brand: Brand,
    @SerialName("product_stock") val stock: Int,
    @SerialName("product_rating") val rating: Double
){

}