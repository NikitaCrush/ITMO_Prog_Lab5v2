package commands

import data.LabWork
import utils.LabWorkCollection
import data.Messages
import utils.LabWorkReader
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
        val labWorkReader = LabWorkReader(readLineFn, validator)
        val labWork = labWorkReader.readLabWork()
        return listOf(labWork)
    }
}