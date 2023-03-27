package utils

import commands.Command
import data.LabWork
import exeptions.CommandException

class CommandParser(private val commandExecutor: CommandExecutor) {

    fun parseAndExecute(commandLine: String): String? {
        val commandParts = commandLine.split(" ", limit = 2)
        val commandName = commandParts[0]
        val commandArgs = commandParts.getOrNull(1)
        println(commandArgs + "AAAA")

        val command = commandExecutor.getCommand(commandName) ?: throw CommandException("Unknown command: $commandName")
        val args = readArguments(command, commandArgs)

        return command.execute(args)
    }

    private fun readArguments(command: Command, commandArgs: String?): List<Any> {
        if (command is commands.AddCommand) {
            val json = commandArgs ?: throw CommandException("Command add requires a JSON object as an argument")
            val labWork = JsonUtil.fromJson<LabWork>(json)
            if (labWork != null) {
                return listOf(labWork)
            } else {
                throw CommandException("Invalid LabWork JSON object")
            }
        } else {
            return command.readArguments { readLine() ?: "" }
        }
    }
}
