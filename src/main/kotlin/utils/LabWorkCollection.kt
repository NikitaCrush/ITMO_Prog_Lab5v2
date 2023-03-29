package utils

import data.Difficulty
import data.LabWork
import java.time.LocalDate
import java.util.PriorityQueue
import kotlinx.serialization.builtins.ListSerializer

class LabWorkCollection private constructor(val fileName: String) {
    private val labWorkQueue = PriorityQueue<LabWork>()

    init {
        loadFromFile()
    }

    private fun loadFromFile() {
        try {
            val labWorkList = JsonUtil.loadFromFile<List<LabWork>>(fileName, ListSerializer(LabWork.serializer()))
            labWorkList?.let { labWorkQueue.addAll(it) }
        } catch (e: kotlinx.serialization.SerializationException) {
            println("Error: Failed to load data from file. The file might be empty or contain invalid content. Starting with an empty collection.")
        }
    }


    fun saveToFile() {
        JsonUtil.saveToFile(labWorkQueue.toList(), fileName, ListSerializer(LabWork.serializer()))
    }



    companion object {
        fun fromFile(fileName: String): LabWorkCollection {
            return LabWorkCollection(fileName)
        }
    }



    fun add(labWork: LabWork) {
        labWorkQueue.add(labWork)
    }


    fun removeById(id: Long): Boolean {
        val initialSize = labWorkQueue.size
        labWorkQueue.removeIf { it.id == id }
        return labWorkQueue.size < initialSize
    }



    fun clear() {
        labWorkQueue.clear()
    }

    fun show(): List<LabWork> {
        return labWorkQueue.toList()
    }

    fun size(): Int {
        return labWorkQueue.size
    }

    fun getCreationDate(): LocalDate {
        return LocalDate.now()
    }

    fun removeFirst() {
        labWorkQueue.poll()
    }

    fun sumOfMinimalPoint(): Int {
        return this.labWorkQueue.sumOf { it.minimalPoint }
    }

    fun minByDifficulty(): LabWork? {
        return labWorkQueue.minByOrNull { it.difficulty ?: Difficulty.EASY }
    }

    fun printUniqueMinimalPoint(): Set<Int> {
        return labWorkQueue.map { it.minimalPoint }.toSet()
    }

    fun addIfMax(labWork: LabWork): Boolean {
        val maxLabWork = labWorkQueue.maxWithOrNull(compareBy { it.id })
        if (maxLabWork == null || compareBy<LabWork> { it.id }.compare(maxLabWork, labWork) < 0) {
            labWorkQueue.add(labWork)
            return true
        }
        return false
    }

    fun removeHead(): LabWork? {
        return labWorkQueue.poll()
    }

    fun getInfo(): String {
        return "Collection type: ${labWorkQueue::class.simpleName}\n" +
                "Initialization date: ${getCreationDate()}\n" +
                "Number of elements: ${size()}"
    }


    fun update(id: Long, newLabWork: LabWork): Boolean {
        val labWork = labWorkQueue.find { it.id == id }
        return if (labWork != null) {
            val updatedLabWork = LabWork(
                id = labWork.id,
                name = newLabWork.name,
                coordinates = newLabWork.coordinates,
                creationDate = labWork.creationDate,
                minimalPoint = newLabWork.minimalPoint,
                personalQualitiesMinimum = newLabWork.personalQualitiesMinimum,
                difficulty = newLabWork.difficulty,
                discipline = newLabWork.discipline
            )

            labWorkQueue.remove(labWork)
            labWorkQueue.add(updatedLabWork)

            true
        } else {
            false
        }
    }
}
