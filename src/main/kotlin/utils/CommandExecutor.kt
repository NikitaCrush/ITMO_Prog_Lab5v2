package utils

import commands.*
import java.util.*

/**
 * Class responsible for managing and executing commands.
 *
 * @property labWorkCollection The [LabWorkCollection] instance to be manipulated by the commands.
 * @property printer The [Printer] instance to output the command results.
 */
class CommandExecutor(labWorkCollection: LabWorkCollection, printer: Printer) {

    private val validator = Validator()
    private val commandMap: MutableMap<String, Command> = mutableMapOf()

    init {
        commandMap["help"] = HelpCommand(this)
        commandMap["info"] = InfoCommand(labWorkCollection)
        commandMap["show"] = ShowCommand(labWorkCollection)
        commandMap["add"] = AddCommand(labWorkCollection, validator)
        commandMap["update"] = UpdateCommand(labWorkCollection, validator)
        commandMap["remove_by_id"] = RemoveByIdCommand(labWorkCollection)
        commandMap["clear"] = ClearCommand(labWorkCollection)
        commandMap["save"] = SaveCommand(labWorkCollection)
        commandMap["execute_script"] = ExecuteScriptCommand(CommandParser(this), printer)
        commandMap["exit"] = ExitCommand()
        commandMap["remove_first"] = RemoveFirstCommand(labWorkCollection)
        commandMap["remove_head"] = RemoveHeadCommand(labWorkCollection)
        commandMap["add_if_max"] = AddIfMaxCommand(labWorkCollection, validator)
        commandMap["sum_of_minimal_point"] = SumOfMinimalPointCommand(labWorkCollection)
        commandMap["min_by_difficulty"] = MinByDifficultyCommand(labWorkCollection)
        commandMap["print_unique_minimal_point"] = PrintUniqueMinimalPointCommand(labWorkCollection)
    }


    /**
     * Retrieves a command instance by its name.
     *
     * @param name The name of the command to retrieve.
     * @return The command instance if found, null otherwise.
     */
    fun getCommand(name: String): Command? {
        return commandMap[name.lowercase(Locale.getDefault())]
    }

    /**
     * Retrieves the available commands.
     *
     * @return A map of command names to their corresponding [Command] instances.
     */
    fun getAvailableCommands(): Map<String, Command> {
        return commandMap
    }
}
