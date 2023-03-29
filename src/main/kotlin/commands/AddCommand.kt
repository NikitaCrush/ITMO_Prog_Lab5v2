package commands

import data.*
import utils.LabWorkCollection
import utils.LabWorkReader
import utils.Validator

class AddCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        val labWork = args[0] as LabWork
        labWorkCollection.add(labWork)
        return Messages.LAB_WORK_SUCCESS_ADD
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        val labWorkReader = LabWorkReader(readLineFn, validator)
        val labWork = labWorkReader.readLabWork()
        return listOf(labWork)
    }
}