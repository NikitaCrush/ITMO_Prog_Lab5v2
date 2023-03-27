package utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object JsonUtil {
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }

    inline fun <reified T> fromJson(json: String): T {
        return gson.fromJson(json, T::class.java)
    }
}
