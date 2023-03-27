package commands

import data.LabWorkCollection

class PrintUniqueMinimalPointCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.printUniqueMinimalPoint().joinToString("\n")
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        // No arguments required
        return emptyList()
    }
}
