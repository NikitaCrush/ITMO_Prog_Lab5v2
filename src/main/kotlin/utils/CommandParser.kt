package utils

import commands.Command
import exeptions.CommandException

class CommandParser(private val commandExecutor: CommandExecutor) {

    fun parseAndExecute(commandLine: String, nestedLevel: Int = 0): String? {
        val commandParts = commandLine.split(" ", limit = 2)
        val commandName = commandParts[0]

        val command = commandExecutor.getCommand(commandName)
            ?: throw CommandException("Unknown command: $commandName")
        val args = readArguments(command)

        // Pass the nestedLevel when executing the command
        return if (command is commands.ExecuteScriptCommand) {
            command.copy(nestedLevel = nestedLevel).execute(args)
        } else {
            command.execute(args)
        }
    }

    private fun readArguments(command: Command): List<Any> {
        return command.readArguments { readLine() ?: "" }
    }
}
