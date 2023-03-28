package data

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val x: Long,
    val y: Double
)