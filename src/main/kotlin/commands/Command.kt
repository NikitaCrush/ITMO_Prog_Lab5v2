package commands

/**
 * The Command interface represents a command that can be executed.
 */
interface Command {
    fun execute(args: List<Any>): String
    fun readArguments(input: () -> String): List<Any>
}
