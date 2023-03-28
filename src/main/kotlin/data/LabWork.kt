package data

import java.time.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import utils.LocalDateTimeSerializer


@Serializable
data class LabWork(
    val id: Long,
    val name: String,       //Поле не может быть null, Строка не может быть пустой
    val coordinates: Coordinates,//Поле не может быть null
    @Serializable(with = LocalDateTimeSerializer::class)
    val creationDate: LocalDateTime,//Значение поля должно быть больше 0
    val minimalPoint: Int,//Значение поля должно быть больше 0
    val personalQualitiesMinimum: Int,//Поле не может быть null, Значение поля должно быть больше 0
    val difficulty: Difficulty?,//Поле может быть null
    val discipline: Discipline //Поле не может быть null
) : Comparable<LabWork> {

    override fun compareTo(other: LabWork): Int {
        return id.compareTo(other.id)
    }
}




