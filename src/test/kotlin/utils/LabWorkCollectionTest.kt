package utils

import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import io.mockk.mockk
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.time.LocalDateTime
import java.util.PriorityQueue

internal class LabWorkCollectionTest {

    @Test
    fun saveToFile() {
//        val testDiscipline = mockk<Discipline>()
        val testDiscipline = Discipline("sport", 12L)
        val labWorkQueue = mutableListOf(
            LabWork(
                15L, "Test2 LabWork", Coordinates(564L, 34.6),
                LocalDateTime.now(), 11, 17, Difficulty.EASY, testDiscipline),
            LabWork(
                1L, "Test LabWork", Coordinates(1L, 2.0), LocalDateTime.now(),
                10, 5, Difficulty.NORMAL, testDiscipline)
        )
        val fileName = "src\\test\\kotlin\\utils\\test.json"

        // Execution
        JsonUtil.saveToFile(labWorkQueue.toList(), fileName, ListSerializer(LabWork.serializer()))

        // Verification
        val jsonString = File(fileName).readText()
        val json = Json { ignoreUnknownKeys = true }
        val savedLabWorkQueue = json.decodeFromString(ListSerializer(LabWork.serializer()), jsonString)
        assertEquals(labWorkQueue, savedLabWorkQueue)

        // Cleanup
//        File(fileName).delete()
    }

    @Test
    fun add() {
        val labWorkCollection = LabWorkCollection("testFilename.json")
        val testDiscipline = mockk<Discipline>()
        val labWork = LabWork(
            1L, "Test LabWork", Coordinates(1L, 2.0), LocalDateTime.now(),
            10, 5, Difficulty.NORMAL, testDiscipline)

        val initialSize = labWorkCollection.size()
        labWorkCollection.add(labWork)
        val newSize = labWorkCollection.size()

        assertEquals(initialSize + 1, newSize)
    }

    @Test
    fun update(){
        val testDiscipline = mockk<Discipline>()
        val labWork = LabWork(
            15L, "Test2 LabWork", Coordinates(564L, 34.6), LocalDateTime.now(),
            11, 17, Difficulty.EASY, testDiscipline)
        val updatedLabWork = LabWork(15L, "Lab 1.1", Coordinates(3L, 4.0), LocalDateTime.now(),
            8, 6, Difficulty.NORMAL, testDiscipline)

        val labWorkQueue: MutableList<LabWork> = mutableListOf(labWork)
        val labWorkIndex = labWorkQueue.indexOfFirst { it.id == 15L }
        labWorkQueue[labWorkIndex] = updatedLabWork

        assertEquals(1, labWorkQueue.size)
        assertEquals(labWorkQueue[0], updatedLabWork)
    }
}


