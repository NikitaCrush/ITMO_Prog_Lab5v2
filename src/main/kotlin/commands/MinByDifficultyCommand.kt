package commands

import data.LabWorkCollection

class MinByDifficultyCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.minByDifficulty().toString()
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        // No arguments required
        return emptyList()
    }
}
