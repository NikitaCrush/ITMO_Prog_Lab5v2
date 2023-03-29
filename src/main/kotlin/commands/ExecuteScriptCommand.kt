package commands

import data.Messages
import utils.CommandParser
import utils.Printer
import java.io.File
import java.io.FileNotFoundException

class ExecuteScriptCommand(
    private val commandParser: CommandParser,
    private val printer: Printer,
    private val nestedLevel: Int = 5 // Add the nestedLevel parameter
) : Command {
    private val maxNestedLevel = 3

    fun copy(nestedLevel: Int): ExecuteScriptCommand {
        return ExecuteScriptCommand(commandParser, printer, nestedLevel)
    }

    override fun execute(args: List<Any>): String {
        if (nestedLevel >= maxNestedLevel) {
            return "Error: Maximum script nesting level ($maxNestedLevel) exceeded. Aborting."
        }

        val fileName = args[0] as String
        val file = File(fileName)

        return try {
            if (!file.exists()) {
                throw FileNotFoundException(Messages.FILE_NOT_FOUND+"$fileName")
            }

            val lines = file.readLines().iterator()
            while (lines.hasNext()) {
                val line = lines.next().trim()
                if (line.isNotBlank()) {
                    val commandResult = commandParser.parseAndExecute(line, nestedLevel + 1, lines::next) // Pass the nested level and input function
                    if (commandResult != null) {
                        printer.println(commandResult)
                    }
                }
            }
            Messages.SCRIPT_EXECUTED_SUCCESS
        } catch (e: FileNotFoundException) {
            "Error: ${e.message}"
        }
    }


        override fun readArguments(input: () -> String): List<Any> { // Change the function signature
            printer.print(Messages.ENTER_SCRIPT_FILE_NAME)// Use printer.print instead of print
            val fileName = input() // Use input() instead of readLineFn()
            return listOf(fileName)
        }

    }