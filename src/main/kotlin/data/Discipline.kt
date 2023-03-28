package data

import kotlinx.serialization.Serializable

@Serializable
data class Discipline(
    val name: String,//Поле не может быть null, Строка не может быть пустой
    val selfStudyHours: Long
)