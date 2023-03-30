package utils

import commands.Command
import exeptions.CommandException

class CommandParser(private val commandExecutor: CommandExecutor) {

    fun parseAndExecute(commandLine: String, nestedLevel: Int = 0, input: (() -> String)? = null): String? {
        val commandParts = commandLine.split(" ", limit = 2)
        val commandName = commandParts[0]

        val command = commandExecutor.getCommand(commandName)
            ?: throw CommandException("Unknown command: $commandName")

        if (command is commands.ExecuteScriptCommand && commandParts.size == 1) {
            return "There is no file name"
        }

        val initialArg = commandParts.getOrElse(1) { "" }
        val args = readArguments(command, input, initialArg)
        return when {
            command is commands.ExecuteScriptCommand -> {
                command.copy(nestedLevel = nestedLevel).execute(args)
            }
            commandLine.isNotBlank() -> {
                command.execute(args)
            }
            else -> null // Return null if the input is empty (ignores empty lines)
        }
    }

    private fun readArguments(command: Command, input: (() -> String)?, initialArg: String): List<Any> {
        return when (command) {
            is commands.AddCommand, is commands.AddIfMaxCommand -> {
                command.readArguments(input ?: { readlnOrNull() ?: "" })
            }
            is commands.UpdateCommand -> {
                command.readArguments { initialArg }
            }
            else -> {
                command.readArguments { initialArg }
            }
        }
    }

}
