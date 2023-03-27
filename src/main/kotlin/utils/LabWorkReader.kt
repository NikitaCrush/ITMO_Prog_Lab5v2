import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import java.time.LocalDateTime

class LabWorkReader(private val readLineFn: () -> String) {

    fun readLabWorkFromConsole(): LabWork {
        print("Enter name: ")
        val name = readLineFn()

        print("Enter coordinates x (Long): ")
        val x = readLineFn().toLong()

        print("Enter coordinates y (Double): ")
        val y = readLineFn().toDouble()

        print("Enter minimalPoint (Long): ")
        val minimalPoint = readLineFn().toLong()

        print("Enter personalQualitiesMinimum (Int): ")
        val personalQualitiesMinimum = readLineFn().toInt()

        print("Enter difficulty (VERY_EASY, EASY, NORMAL, HARD, VERY_HARD, null): ")
        val difficulty = readLineFn().let {
            if (it.isBlank()) null else Difficulty.valueOf(it)
        }

        print("Enter discipline name: ")
        val disciplineName = readLineFn()

        print("Enter discipline hours (Long): ")
        val disciplineHours = readLineFn().toLong()

        val discipline = Discipline(disciplineName, disciplineHours)

        val id = 0L // Temporary id, it will be replaced by the LabWorkCollection when adding
        val creationDate = LocalDateTime.now() // Automatically generated

        return LabWork(id, name, Coordinates(x, y), creationDate,
            minimalPoint.toInt(), personalQualitiesMinimum, difficulty, discipline)
    }
}
