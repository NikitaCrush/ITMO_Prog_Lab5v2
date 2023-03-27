package commands

import LabWorkReader
import data.LabWork
import data.LabWorkCollection
import data.Messages
import utils.JsonUtil
import utils.Validator

class AddIfMaxCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        val labWork = JsonUtil.fromJson<LabWork>(args[0].toString())
        if (!validator.validateLabWork(labWork)) {
            return Messages.LAB_WORK_INVALID_DATA
        }
        labWorkCollection.addIfMax(labWork)
        return Messages.LAB_WORK_SUCCESS_ADD
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        val labWorkReader = LabWorkReader(readLineFn)
        val labWork = labWorkReader.readLabWorkFromConsole()
        return listOf(JsonUtil.toJson(labWork))
    }
}
