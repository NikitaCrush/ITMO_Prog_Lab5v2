package commands

import utils.LabWorkCollection

/**
 * The ShowCommand class displays all lab works in the collection.
 *
 * @property labWorkCollection The lab work collection to be displayed.
 */
class ShowCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.show().joinToString(separator = "\n")
    }
    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
