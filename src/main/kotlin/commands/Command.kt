package commands

interface Command {
    fun execute(args: List<Any>): String
    fun readArguments(input: () -> String): List<Any>
}
