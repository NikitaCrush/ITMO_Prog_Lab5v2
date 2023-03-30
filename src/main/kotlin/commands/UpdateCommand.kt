package commands

import data.LabWork
import exeptions.ValidationException
import utils.CommandParser
import utils.LabWorkCollection
import utils.LabWorkReader
import utils.Validator

class UpdateCommand(
    private val labWorkCollection: LabWorkCollection,
    private val validator: Validator
) : Command {

    override fun execute(args: List<Any>): String {
        if (args.isEmpty() || args[0] !is String) {
            return "ID is not provided or has an incorrect format."
        }

        val id: Long
        try {
            id = args[0].toString().toLong()
        } catch (e: NumberFormatException) {
            return "Invalid ID format. Please enter a valid number."
        }

        val labWorkToUpdate = labWorkCollection.show().find { it.id == id }

        return if (labWorkToUpdate != null) {
            try {
                val labWorkReader = LabWorkReader({ readLine() ?: "" }, validator)
                val updatedLabWork = labWorkReader.readLabWork(id, labWorkToUpdate.creationDate)
                labWorkCollection.update(id, updatedLabWork)
                "Lab work with ID: $id has been updated."
            } catch (e: ValidationException) {
                "Failed to update lab work due to invalid input: ${e.message}"
            }
        } else {
            "No lab work found with ID: $id."
        }
    }

    override fun readArguments(input: () -> String): List<Any> {
        val inputString = input()
        return listOf(inputString)
    }
}
