package commands

import utils.LabWorkCollection

class RemoveByIdCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        val id = args[0] as Long
        val removed = labWorkCollection.removeById(id)
        return if (removed) {
            "Lab work removed successfully."
        } else {
            "No lab work found with the provided id."
        }
    }

    override fun readArguments(input: () -> String): List<Any> {
        val idStr = input()
        val id = idStr.toLongOrNull() ?: throw IllegalArgumentException("Invalid ID")
        return listOf(id)
    }
}
