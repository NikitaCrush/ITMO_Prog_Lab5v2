import data.Messages
import exeptions.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import utils.*
import java.io.FileNotFoundException
import java.util.*

val koinModule = module {
    single { Stack<String>() }
    single { Validator() }
    single {
        val fileName = System.getenv("LAB_WORK_FILE") ?: "collection.json"
        LabWorkCollection.fromFile(fileName)
    }
}

/**
 * The main function of the program, responsible for setting up the environment and executing commands.
 * Reads the file name from the environment variable LAB_WORK_FILE, otherwise uses "collection.json" as the default file name.
 */
fun main() {
    startKoin {
        modules(koinModule)
    }
    // Read the file name from the environment variable
    //val fileName = System.getenv("LAB_WORK_FILE") ?: "collection.json"

    // Create and populate the LabWork collection
    //val labWorkCollection = LabWorkCollection.fromFile(fileName)

    val printer = ConsolePrinter()
    val commandExecutor = CommandExecutor(printer)
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
