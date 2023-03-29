package commands

import utils.LabWorkCollection

class RemoveByIdCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        val id = (args[0] as String).toLong()
        val removed = labWorkCollection.removeById(id)
        return if (removed) {
            "Lab work removed successfully."
        } else {
            "No lab work found with the provided id."
        }
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        print("Enter id: ")
        val id = readLineFn()
        return listOf(id)
    }
}
