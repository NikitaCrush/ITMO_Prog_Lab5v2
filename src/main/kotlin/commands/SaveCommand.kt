package commands

import utils.LabWorkCollection
import data.Messages

class SaveCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        labWorkCollection.saveToFile()
        return Messages.LAB_WORK_SUCCESS_SAVE
    }

    override fun readArguments(input: () -> String): List<String> {
        return emptyList()
    }
}
