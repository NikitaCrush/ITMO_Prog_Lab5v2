package utils

import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import exeptions.ValidationException
import java.time.LocalDateTime

class LabWorkReader(private val readLineFn: () -> String, private val validator: Validator) {

    fun readName(): String {
        while (true) {
            print("Enter name: ")
            val name = readLineFn()
            try {
                validator.validateName(name)
                return name
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }

    fun readCoordinates(): Coordinates {
        while (true) {
            try {
                print("Enter coordinates x (Long): ")
                val x = readLineFn().trim().toLong()
                print("Enter coordinates y (Double): ")
                val y = readLineFn().trim().toDouble()
                val coordinates = Coordinates(x, y)
                validator.validateCoordinates(coordinates)
                return coordinates
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter valid coordinates.")
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }


    fun readMinimalPoint(): Int {
        return readInt("Enter minimalPoint: ", readLineFn, validator::validateMinimalPoint)
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
        while (true) {
            try {
                print("Enter discipline (name selfStudyHours(Long)): ")
                val disciplineInput = readLineFn().split(" ").map { it.trim() }
                val disciplineName = disciplineInput[0]
                val selfStudyHours = readSelfStudyHours()
                val discipline = Discipline(disciplineName, selfStudyHours)
                validator.validateDiscipline(discipline)
                validator.validateSelfStudyHours(selfStudyHours)
                return discipline
            } catch (e: Exception) {
                println("Invalid input. Please enter a valid discipline (name selfStudyHours(Long)).")
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }


    fun readLabWork(
        id: Long = IdGenerator.generateUniqueId(),
        creationDate: LocalDateTime = LocalDateTime.now()
    ): LabWork {
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
        return readInt("Enter personalQualitiesMinimum: ", readLineFn, validator::validatePersonalQualitiesMinimum)
    }

    private fun readInt(prompt: String, readLineFn: () -> String, validation: (Int) -> Unit): Int {
        while (true) {
            try {
                print(prompt)
                val value = readLineFn().toInt()
                validation(value)
                return value
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid number.")
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }
    private fun readLong(prompt: String, readLineFn: () -> String, validation: (Long) -> Unit): Long {
        while (true) {
            try {
                print(prompt)
                val value = readLineFn().toLong()
                validation(value)
                return value
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid number.")
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }
    fun readSelfStudyHours(): Long {
        return readLong("Enter selfStudyHours: ", readLineFn, validator::validateSelfStudyHours)
    }


}