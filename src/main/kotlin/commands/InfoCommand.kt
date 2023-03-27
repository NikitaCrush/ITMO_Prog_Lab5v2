package commands

import data.LabWorkCollection

class InfoCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.getInfo()
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}
