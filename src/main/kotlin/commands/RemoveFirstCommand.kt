package commands

import data.LabWorkCollection

class RemoveFirstCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.removeFirst().toString()
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}
