package utils

import data.Messages
import exeptions.CommandException

class CommandProcessor(
    private val commandExecutor: CommandExecutor,
    private val printer: Printer
) {
    private val commandParser = CommandParser(commandExecutor)

    fun processCommand(commandLine: String): Boolean {
        if (commandLine.isBlank()) return true

        try {
            val commandResult = commandParser.parseAndExecute(commandLine)
            if (commandResult != null) {
                printer.println(commandResult)
                return true
            } else {
                printer.println(Messages.UNKNOWN_COMMAND)
                return false
            }
        } catch (e: CommandException) {
            printer.println(Messages.ERROR_EXECUTING_COMMAND.format(e.message))
            return false
        } catch (e: Exception) {
            printer.println(Messages.UNEXPECTED_ERROR.format(e.message))
            return false
        }
    }
}
