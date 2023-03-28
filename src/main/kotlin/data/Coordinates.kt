package data

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val x: Long,//Максимальное значение поля: 608, Поле не может быть null
    val y: Double//Поле не может быть null
)