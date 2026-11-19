package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MenuNetworkdata (
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
){
    fun toMenuItemRoom() = MenuItem(
        id = id.toString(),
        title = title,
        description = description,
        price = price, // <-- Si en tu Entity price es String, quita el .toDouble()
        image = image,
        category = category
    )
}
