package utils

import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import java.time.LocalDateTime

class LabWorkReader(private val readLineFn: () -> String) {

    fun readName(): String {
        print("Enter name: ")
        return readLineFn()
    }

    fun readCoordinates(): Coordinates {
        while (true) {
            try {
                print("Enter coordinates x (Long): ")
                val x = readLineFn().trim().toLong()

                print("Enter coordinates y (Double): ")
                val y = readLineFn().trim().toDouble()

                return Coordinates(x, y)
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter valid coordinates.")
            }
        }
    }


    fun readMinimalPoint(): Int {
        return readInt("Enter minimalPoint: ", readLineFn)
    }

    fun readDescription(): String? {
        print("Enter description (leave empty for null): ")
        return readLineFn().ifEmpty { null }
    }

    fun readDifficulty(): Difficulty? {
        print("Enter difficulty (EASY, NORMAL, TERRIBLE) or leave it empty for null: ")
        while (true) {
            val input = readLineFn().trim()
            if (input.isEmpty()) {
                return null
            }

            return try {
                Difficulty.valueOf(input.toUpperCase())
            } catch (e: IllegalArgumentException) {
                println("Invalid input. Please enter a valid difficulty (EASY, NORMAL, TERRIBLE) or leave it empty.")
                continue
            }
        }
    }



    fun readDiscipline(): Discipline {
        print("Enter discipline (name selfStudyHours(Long)): ")
        while (true) {
            try {
                val disciplineInput = readLineFn().split(" ").map { it.trim() }
                val disciplineName = disciplineInput[0]
                val selfStudyHours = disciplineInput[1].toLong()
                return Discipline(disciplineName, selfStudyHours)
            } catch (e: Exception) {
                println("Invalid input. Please enter a valid discipline (name selfStudyHours(Long)).")
            }
        }
    }

    fun readLabWork(id: Long = IdGenerator.generateUniqueId(), creationDate: LocalDateTime = LocalDateTime.now()): LabWork {
        val name = readName()
        val coordinates = readCoordinates()
        val minimalPoint = readMinimalPoint()
        val personalQualitiesMinimum = readPersonalQualitiesMinimum()
        val difficulty = readDifficulty()
        val discipline = readDiscipline()
        return LabWork(
            id,
            name,
            coordinates,
            creationDate,
            minimalPoint,
            personalQualitiesMinimum,
            difficulty,
            discipline
        )
    }

    private fun readPersonalQualitiesMinimum(): Int {
        return readInt("Enter personalQualitiesMinimum: ", readLineFn)
    }

    private fun readInt(prompt: String, readLineFn: () -> String): Int {
        while (true) {
            try {
                print(prompt)
                val value = readLineFn().toInt()
                return value
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid number.")
            }
        }
    }
}
