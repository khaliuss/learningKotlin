package org.example.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Brand {

    @SerialName("Brand A")
    BRAND_A,

    @SerialName("Brand B")
    BRAND_B,

    @SerialName("Brand C")
    BRAND_C

}