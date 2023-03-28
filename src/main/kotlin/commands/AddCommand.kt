package commands

import data.*
import utils.Validator
import java.time.LocalDateTime

class AddCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        val labWork = args[0] as LabWork
        if (!validator.validateLabWork(labWork)) {
            return Messages.LAB_WORK_INVALID_DATA
        }
        labWorkCollection.add(labWork)
        return Messages.LAB_WORK_SUCCESS_ADD
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        val labWork = validator.validateAndReadLabWork(readLineFn)
        return listOf(labWork)
    }
}
