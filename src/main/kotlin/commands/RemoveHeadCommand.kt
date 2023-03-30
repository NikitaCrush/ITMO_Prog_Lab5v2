package commands

import utils.LabWorkCollection

class RemoveHeadCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.removeHead().toString()
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
