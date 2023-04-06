package utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.reflect.typeOf

internal class IdGeneratorTest {

    @Test
    fun generateUniqueId() {
        val getUniqueIdType = IdGenerator.generateUniqueId().javaClass.kotlin
        val aL = 12L
        val expectedType = aL.javaClass.kotlin

        assertEquals(expectedType, getUniqueIdType)
    }
}