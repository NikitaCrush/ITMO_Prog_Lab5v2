package commands

import utils.LabWorkCollection

/**
 * The RemoveHeadCommand class removes and returns the first element in the lab work collection.
 *
 * @property labWorkCollection The lab work collection to remove the first element from.
 */
class RemoveHeadCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.removeHead().toString()
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
