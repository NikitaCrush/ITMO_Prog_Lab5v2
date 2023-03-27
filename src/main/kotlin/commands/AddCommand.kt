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
        print("Enter id: ")
        val id = readLineFn().toLong()

        print("Enter name: ")
        val name = readLineFn()

        print("Enter coordinates (x(Long) y(Double)): ")
        val coordinates = readLineFn().split(" ").map { it.trim() }
        val x = coordinates[0].toLong()
        val y = coordinates[1].toDouble()

        print("Enter minimalPoint: ")
        val minimalPoint = readLineFn().toInt()

        print("Enter personalQualitiesMinimum: ")
        val personalQualitiesMinimum = readLineFn().toInt()

        print("Enter description (leave empty for null): ")
        val description = readLineFn().ifEmpty { null }

        print("Enter difficulty (EASY, NORMAL, TERRIBLE): ")
        val difficulty = readLineFn().let { if (it.isEmpty()) null else Difficulty.valueOf(it.toUpperCase()) }

        print("Enter discipline (name selfStudyHours(Long)): ")
        val disciplineInput = readLineFn().split(" ").map { it.trim() }
        val disciplineName = disciplineInput[0]
        val selfStudyHours = disciplineInput[1].toLong()
        val discipline = Discipline(disciplineName, selfStudyHours)

        val labWork = LabWork(id, name, Coordinates(x, y), LocalDateTime.now(), minimalPoint, personalQualitiesMinimum, difficulty, discipline)
        return listOf(labWork)
    }
}
