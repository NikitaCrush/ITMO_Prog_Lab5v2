package data

import data.Messages
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class MessagesTest {

    @Test
    fun `correctMessaging`() {
        assertEquals("File not found: ", Messages.FILE_NOT_FOUND)
    }
}