package utils

object IdGenerator {
    private val generatedIds = mutableSetOf<Long>()

    fun generateUniqueId(): Long {
        var id: Long
        do {
            id = (1L..Long.MAX_VALUE).random()
        } while (generatedIds.contains(id))
        generatedIds.add(id)
        return id
    }
}

