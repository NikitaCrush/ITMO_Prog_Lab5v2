package commands

import data.LabWorkCollection

class SumOfMinimalPointCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.sumOfMinimalPoint().toString()
    }
    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}