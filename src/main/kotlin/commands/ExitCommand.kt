package commands

import data.Messages

class ExitCommand : Command {
    override fun execute(args: List<Any>): String {
        System.exit(0)
        return Messages.VOID
    }

    override fun readArguments(readLineFn: () -> String): List<String> {
        return emptyList()
    }
}
