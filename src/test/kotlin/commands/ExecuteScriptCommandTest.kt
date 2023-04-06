package commands

import data.Messages
import koinModule
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.koin.core.context.startKoin
import utils.CommandExecutor
import utils.CommandParser
import utils.Printer
import java.io.File

internal class ExecuteScriptCommandTest {
    private val printer = object : Printer {
        var output = ""
        override fun print(message: String) { output += message }
        override fun println(message: String) { output += "$message\n" }
    }
    private val commandParser = CommandParser(CommandExecutor(printer))

    private val executeScriptCommand = ExecuteScriptCommand(commandParser, printer)

    @Test
    fun `test execute with non-existent file`() {
        startKoin {
            modules(koinModule)
        }

        val result = executeScriptCommand.execute(listOf("src\\main\\resources\\nonexistent.txt"))
        assertEquals("Error: ${Messages.FILE_NOT_FOUND}src\\main\\resources\\nonexistent.txt", result)
    }

    @Test
    fun `test execute with recursive file`() {

        startKoin {
            modules(koinModule)
        }
        val result = executeScriptCommand.execute(listOf("src\\test\\kotlin\\commands\\rec.txt"))
        assertEquals("Error: Recursion", result)
    }

    @Test
    fun `test readArguments with valid input`() {
        val input = {"src\\test\\kotlin\\utils\\script.txt"}
        val result = executeScriptCommand.readArguments(input)
        assertEquals(listOf("src\\test\\kotlin\\utils\\script.txt"), result)
    }
}