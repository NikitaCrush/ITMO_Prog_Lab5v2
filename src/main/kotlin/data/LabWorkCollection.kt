package data

import exeptions.CollectionLoadingException
import java.io.File
import java.time.LocalDate
import java.util.PriorityQueue
import utils.JsonUtil

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class LabWorkCollection private constructor(val fileName: String) {
    private val labWorkQueue = PriorityQueue<LabWork>()

    init {
        loadFromFile()
    }

    private fun loadFromFile() {
        val file = File(fileName)
        if (!file.exists()) {
            file.createNewFile()
        }

        val jsonString = file.readText()

        if (jsonString.isNotEmpty()) {
            try {
                val labWorkList: List<LabWork> = Json.decodeFromString(jsonString)
                labWorkQueue.addAll(labWorkList)
            } catch (e: Exception) {
                throw CollectionLoadingException(Messages.INVALID_JSON_FORMAT)
            }
        }
    }

    fun saveToFile() {
        val jsonString = Json.encodeToString(labWorkQueue)
        File(fileName).writeText(jsonString)
    }

    companion object {
        fun fromFile(fileName: String): LabWorkCollection {
            val file = File(fileName)
            return if (file.exists()) {
                val json = file.readText()
                val labWorks = JsonUtil.fromJson<List<LabWork>>(json)
                LabWorkCollection(fileName).apply {
                    labWorks?.let { labWorkQueue.addAll(it) } // Add null-safe let function here
                }
            } else {
                LabWorkCollection(fileName)
            }
        }
    }

    // The rest of the class remains unchanged...



    fun add(labWork: LabWork) {
        labWorkQueue.add(labWork)
    }

    fun update(id: Int, labWork: LabWork) {
        removeById(id)
        labWorkQueue.add(labWork)
    }

    fun removeById(id: Int) {
        labWorkQueue.removeIf { it.id.toInt() == id }
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

    fun getById(id: Int): LabWork? {
        return labWorkQueue.find { it.id.toInt() == id }
    }

    fun getFirst(): LabWork? {
        return labWorkQueue.peek()
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

    fun getAvailableCommands(): List<String> {
        return listOf(
            "help", "info", "show", "add {element}", "update id {element}",
            "remove_by_id id", "clear", "save", "execute_script file_name", "exit",
            "remove_first", "remove_head", "add_if_max {element}",
            "sum_of_minimal_point", "min_by_difficulty", "print_unique_minimal_point"
        )
    }

}




