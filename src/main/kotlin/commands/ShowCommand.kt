package commands

import utils.LabWorkCollection

class ShowCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.show().joinToString(separator = "\n")
    }
    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
