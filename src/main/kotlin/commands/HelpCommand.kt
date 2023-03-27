package commands

import utils.CommandExecutor

class HelpCommand(private val commandExecutor: CommandExecutor) : Command {
    override fun execute(args: List<Any>): String {
        return commandExecutor.getAvailableCommands().keys.joinToString("\n")
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        // No arguments required
        return emptyList()
    }
}
