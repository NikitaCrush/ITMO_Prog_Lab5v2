package commands

import data.LabWorkCollection
import data.Messages

class SaveCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        labWorkCollection.saveToFile()
        return Messages.LAB_WORK_SUCCESS_SAVE
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}