package commands

import LabWorkReader
import data.LabWork
import data.LabWorkCollection
import data.Messages

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
//        val labWorkReader = LabWorkReader(readLineFn)
//        val labWork = labWorkReader.readLabWorkFromConsole()
//        return listOf(labWork)
        print("Enter id: ")
        val id = readLineFn()

        print("Enter name: ")
        val name = readLineFn()

        print("Enter minimalPoint: ")
        val minimalPoint = readLineFn()

        print("Enter description (leave empty for null): ")
        val description = readLineFn().ifEmpty { null }

        print("Enter difficulty (EASY, NORMAL, TERRIBLE): ")
        val difficulty = readLineFn().ifEmpty { null }

        return listOf(id, name, minimalPoint, description ?: "", difficulty ?: "")
    }
    }


