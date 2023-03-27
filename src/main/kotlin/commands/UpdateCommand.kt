package commands

import data.LabWork
import data.LabWorkCollection
import utils.JsonUtil
import utils.Validator

class UpdateCommand(private val labWorkCollection: LabWorkCollection, validator: Validator) : Command {
    override fun execute(args: List<Any>): String {
        val id = (args[0] as String).toInt()
        val labWork = JsonUtil.fromJson<LabWork>(args[1] as String)
        labWorkCollection.update(id, labWork)
        return "Lab work updated successfully."
    }
    override fun readArguments(readLineFn: () -> String): List<Any> {
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
