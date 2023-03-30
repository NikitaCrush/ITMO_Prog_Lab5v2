package commands

import utils.LabWorkCollection

class MinByDifficultyCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.minByDifficulty().toString()
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
