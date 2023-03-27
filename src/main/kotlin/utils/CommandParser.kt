package utils

import commands.Command
import data.LabWork
import exeptions.CommandException

class CommandParser(private val commandExecutor: CommandExecutor) {

    fun parseAndExecute(commandLine: String): String? {
        val commandParts = commandLine.split(" ", limit = 2)
        val commandName = commandParts[0]

        val command = commandExecutor.getCommand(commandName) ?: throw CommandException("Unknown command: $commandName")
        val args = readArguments(command)

        return command.execute(args)
    }

    private fun readArguments(command: Command): List<Any> {
        return command.readArguments { readLine() ?: "" }
    }
}
