package users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("user_id") val id: Int,
    @SerialName("first_name") val firsName: String,
    @SerialName("last_name") val secondName: String,
    @SerialName("age") val age: Int
)
