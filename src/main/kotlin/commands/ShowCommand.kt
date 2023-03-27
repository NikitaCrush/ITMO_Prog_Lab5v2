package commands

import data.LabWorkCollection

class ShowCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.show().joinToString(separator = "\n")
    }
    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}
