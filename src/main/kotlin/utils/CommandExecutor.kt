package utils

import commands.*
import data.LabWorkCollection


class CommandExecutor(private val labWorkCollection: LabWorkCollection, private val printer: Printer) {

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



    fun getCommand(name: String): Command? {
        return commandMap[name.toLowerCase()]
    }


    fun getAvailableCommands(): Map<String, Command> {
        return commandMap
    }
}
