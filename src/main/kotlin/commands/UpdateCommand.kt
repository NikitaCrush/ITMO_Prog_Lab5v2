package commands

import data.LabWork
import utils.LabWorkCollection
import data.Messages
import utils.LabWorkReader
import utils.Validator

class UpdateCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        val id = args[0] as Long
        val labWork = args[1] as LabWork

        val updated = labWorkCollection.update(id, labWork)

        return if (updated) Messages.LAB_WORK_SUCCESS_UPDATE else Messages.LAB_WORK_NOT_FOUND
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        print("Enter the ID of the lab work to update: ")
        val id = readLineFn().trim().toLong()

        val labWorkReader = LabWorkReader(readLineFn, validator)
        val labWork = labWorkReader.readLabWork()

        return listOf(id, labWork)
    }
}
