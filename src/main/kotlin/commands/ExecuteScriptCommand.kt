package commands

import utils.CommandParser
import utils.Printer
import java.io.File
import java.io.FileNotFoundException

class ExecuteScriptCommand(
    private val commandParser: CommandParser,
    private val printer: Printer
) : Command {
    override fun execute(args: List<Any>): String {
        val fileName = args[0] as String
        val file = File(fileName)

        if (!file.exists()) {
            throw FileNotFoundException("File not found: $fileName")
        }

        file.forEachLine { line ->
            if (line.isNotBlank()) {
                val commandResult = commandParser.parseAndExecute(line)
                if (commandResult != null) {
                    printer.println(commandResult)
                }
            }
        }

        return "Script executed successfully."
    }

    override fun readArguments(readLineFn: () -> String): List<Any> {
        print("Enter script file name: ")
        val fileName = readLineFn()
        return listOf(fileName)
    }
}
