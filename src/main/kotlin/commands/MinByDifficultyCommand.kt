package commands

import utils.LabWorkCollection

/**
 * The MinByDifficultyCommand class finds and prints a random lab work with the minimal difficulty in the collection.
 *
 * @property labWorkCollection The lab work collection to be used for searching.
 */
class MinByDifficultyCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.minByDifficulty().toString()
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
