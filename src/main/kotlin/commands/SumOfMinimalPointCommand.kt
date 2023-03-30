package commands

import utils.LabWorkCollection

/**
 * The SumOfMinimalPointCommand class calculates the sum of the minimalPoint values of all
 * lab works in the collection.
 *
 * @property labWorkCollection The lab work collection to be used for calculations.
 */
class SumOfMinimalPointCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        return labWorkCollection.sumOfMinimalPoint().toString()
    }
    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
