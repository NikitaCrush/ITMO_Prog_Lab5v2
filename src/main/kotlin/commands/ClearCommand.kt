package commands

import utils.LabWorkCollection
import data.Messages

class ClearCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        labWorkCollection.clear()
        return Messages.LAB_WORK_SUCCESS_CLEAR
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
