package data

import kotlinx.serialization.Serializable

@Serializable
data class Discipline(
    val name: String,
    val selfStudyHours: Long
)