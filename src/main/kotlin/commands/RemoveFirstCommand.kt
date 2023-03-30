package commands

import utils.LabWorkCollection

class RemoveFirstCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        labWorkCollection.removeFirst()
        return "First element removed successfully."
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
