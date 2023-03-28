package data

import kotlinx.serialization.Serializable

@Serializable
enum class Difficulty {
    EASY, NORMAL, TERRIBLE
}