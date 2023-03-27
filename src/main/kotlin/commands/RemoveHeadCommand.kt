package commands

import data.LabWorkCollection

class RemoveHeadCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.removeHead().toString()
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}
