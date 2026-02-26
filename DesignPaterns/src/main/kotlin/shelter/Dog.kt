package shelter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog (
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("breed") val breed: String,
    @SerialName("weight") val weight: Double,
)
