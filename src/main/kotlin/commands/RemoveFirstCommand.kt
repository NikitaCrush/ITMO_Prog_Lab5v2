package commands

import data.LabWorkCollection

class RemoveFirstCommand(private val labWorkCollection: LabWorkCollection) : Command {
    override fun execute(args: List<Any>): String {
        val removed = labWorkCollection.removeFirst()
        return if (removed != null) {
            "First element removed successfully."
        } else {
            "Collection is empty. Cannot remove first element."
        }
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}
