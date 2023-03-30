package commands

import utils.LabWorkCollection

class PrintUniqueMinimalPointCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.printUniqueMinimalPoint().joinToString("\n")
    }

    override fun readArguments(input: () -> String): List<String> {
        // No arguments required
        return emptyList()
    }
}
