package commands

import data.LabWorkCollection

class RemoveByIdCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        val id = (args[0] as String).toInt()
        labWorkCollection.removeById(id)
        return "Lab work removed successfully."
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        print("Enter id: ")
        val id = readLineFn()
        return listOf(id)
    }
}
