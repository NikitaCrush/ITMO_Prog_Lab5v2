package commands

import utils.LabWorkCollection

class InfoCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.getInfo()
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
