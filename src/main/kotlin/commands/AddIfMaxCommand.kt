package commands

import data.LabWork
import data.LabWorkCollection
import data.Messages
import utils.Validator

class AddIfMaxCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        val labWork = args[0] as LabWork
        val added = labWorkCollection.addIfMax(labWork)
        return if (added) Messages.LAB_WORK_SUCCESS_ADD else Messages.LAB_WORK_NOT_MAX
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        val labWork = validator.validateAndReadLabWork(readLineFn)
        return listOf(labWork)
    }
}
