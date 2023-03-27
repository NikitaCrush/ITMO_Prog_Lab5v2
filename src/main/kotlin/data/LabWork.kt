package data

import java.time.LocalDateTime

data class LabWork(
    val id: Long,
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDateTime,
    val minimalPoint: Int,
    val personalQualitiesMinimum: Int,
    val difficulty: Difficulty?,
    val discipline: Discipline
) : Comparable<LabWork> {

    override fun compareTo(other: LabWork): Int {
        return id.compareTo(other.id)
    }
}




