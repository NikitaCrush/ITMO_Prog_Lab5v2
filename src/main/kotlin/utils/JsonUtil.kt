package utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object JsonUtil {
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true // Add this line to enable pretty-printing
    }

    fun <T> fromJson(jsonString: String, serializer: KSerializer<T>): T? {
        return try {
            json.decodeFromString(serializer, jsonString)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> toJson(obj: T, serializer: KSerializer<T>): String {
        return json.encodeToString(serializer, obj)
    }

    fun <T> loadFromFile(fileName: String, serializer: KSerializer<T>): T? {
        return try {
            val jsonString = File(fileName).readText()
            fromJson(jsonString, serializer)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun <T> saveToFile(obj: T, fileName: String, serializer: KSerializer<T>) {
        try {
            val jsonString = json.encodeToString(serializer, obj)
            File(fileName).writeText(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
