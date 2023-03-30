import utils.LabWorkCollection
import data.Messages
import exeptions.*
import utils.CommandExecutor
import utils.CommandParser
import utils.ConsolePrinter
import java.io.FileNotFoundException


/**
 * The main function of the program, responsible for setting up the environment and executing commands.
 * Reads the file name from the environment variable LAB_WORK_FILE, otherwise uses "collection.json" as the default file name.
 */
fun main() {
    // Read the file name from the environment variable
    val fileName = System.getenv("LAB_WORK_FILE") ?: "collection.json"

    // Create and populate the LabWork collection
    val labWorkCollection = LabWorkCollection.fromFile(fileName)

    val printer = ConsolePrinter()
    val commandExecutor = CommandExecutor(labWorkCollection, printer)
    val commandParser = CommandParser(commandExecutor)

    printer.println(Messages.WELCOME)
    printer.println(Messages.ENTER_HELP)

    while (true) {
        printer.print("> ")
        val commandLine = readlnOrNull() ?: break

        try {
            val commandResult = commandParser.parseAndExecute(commandLine)
            commandResult?.let { printer.println(it) }
        } catch (e: CommandException) {
            e.message?.let { printer.println(it) }
        } catch (e: FileNotFoundException) {
            e.message?.let { printer.println(it) }
        } catch (e: IllegalArgumentException) {
            e.message?.let { printer.println(it) }
        }
    }
}
