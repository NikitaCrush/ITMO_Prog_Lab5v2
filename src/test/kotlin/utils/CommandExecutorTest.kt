package utils

import commands.AddCommand
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CommandExecutorTest {
//    private val printer = Printer
    private val printer = object : Printer {
        override fun print(message: String) {}
        override fun println(message: String) {}
    }
    private val executor = CommandExecutor(printer)

    @Test
    fun `getCommand returns correct command object for valid command name`() {
        val expectedCommand = AddCommand()

        val actualCommand = executor.getCommand("add")

        assertEquals(expectedCommand::class, actualCommand!!::class)
    }

    @Test
    fun `getCommand returns null for invalid command name`() {
        val actualCommand = executor.getCommand("invalid_command_name")

        assertNull(actualCommand)
    }
}