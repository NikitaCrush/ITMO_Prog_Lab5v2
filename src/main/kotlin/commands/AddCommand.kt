package commands

import data.LabWork
import data.LabWorkCollection
import data.Messages
import data.Coordinates
import data.Difficulty
import data.Discipline
import java.time.LocalDateTime
import utils.Validator

class AddCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        val labWork = args[0] as LabWork
        if (!validator.validateLabWork(labWork)) {
            return Messages.LAB_WORK_INVALID_DATA
        }
        labWorkCollection.add(labWork)
        return Messages.LAB_WORK_SUCCESS_ADD
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        val id = generateUniqueId()
        val name = readNonEmptyString("Enter name: ", readLineFn)

        print("Enter coordinates (x(Long), y(Double)): ")
        val coordinates = readCoordinates(readLineFn)

        val minimalPoint = readInt("Enter minimalPoint: ", readLineFn)
        val personalQualitiesMinimum = readInt("Enter personalQualitiesMinimum: ", readLineFn)

        print("Enter description (leave empty for null): ")
        val description = readLineFn().ifEmpty { null }

        print("Enter difficulty (EASY, NORMAL, TERRIBLE): ")
        val difficulty = readDifficulty(readLineFn)

        print("Enter discipline (name selfStudyHours(Long)): ")
        val discipline = readDiscipline(readLineFn)

        val labWork = LabWork(id, name, coordinates, LocalDateTime.now(), minimalPoint, personalQualitiesMinimum, difficulty, discipline)
        return listOf(labWork)
    }

    private fun readLong(prompt: String, readLineFn: () -> String): Long {
        while (true) {
            try {
                print(prompt)
                val value = readLineFn().toLong()
                return value
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid number.")
            }
        }
    }
    private fun generateUniqueId(): Long {
        var id: Long
        do {
            id = (1..Long.MAX_VALUE).random()
        } while (labWorkCollection.containsId(id))
        return id
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

    private fun readNonEmptyString(prompt: String, readLineFn: () -> String): String {
        while (true) {
            print(prompt)
            val value = readLineFn()
            if (value.isNotEmpty()) {
                return value
            } else {
                println("Invalid input. Please enter a non-empty string.")
            }
        }
    }

    private fun readCoordinates(readLineFn: () -> String): Coordinates {
        while (true) {
            try {
                val coordinates = readLineFn().split(", ").map { it.trim() }
                val x = coordinates[0].toLong()
                val y = coordinates[1].toDouble()
                return Coordinates(x, y)
            } catch (e: Exception) {
                println("Invalid input. Please enter valid coordinates (x(Long), y(Double)).")
            }
        }
    }

    private fun readDifficulty(readLineFn: () -> String): Difficulty? {
        while (true) {
            val input = readLineFn()
            try {
                return if (input.isEmpty()) null else Difficulty.valueOf(input.toUpperCase())
            } catch (e: IllegalArgumentException) {
                println("Invalid input. Please enter a valid difficulty (EASY, NORMAL, TERRIBLE) or leave it empty.")
            }
        }
    }

    private fun readDiscipline(readLineFn: () -> String): Discipline {
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
}
