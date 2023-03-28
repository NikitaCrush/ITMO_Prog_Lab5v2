package utils

import data.Coordinates
import data.Discipline
import data.Difficulty
import data.LabWork
import exeptions.ValidationException

class Validator {
    fun validateName(name: String?) {
        if (name == null || name.isBlank()) {
            throw ValidationException("Name cannot be empty.")
        }
    }

    fun validateCoordinates(coordinates: Coordinates?) {
        if (coordinates == null || coordinates.x > 608) {
            throw ValidationException("X coordinate value cannot be greater than 608.")
        }
    }

    fun validateMinimalPoint(minimalPoint: Int?) {
        if (minimalPoint == null || minimalPoint <= 0) {
            throw ValidationException("Minimal point value must be greater than 0.")
        }
    }

    fun validatePersonalQualitiesMinimum(personalQualitiesMinimum: Int?) {
        if (personalQualitiesMinimum == null || personalQualitiesMinimum <= 0) {
            throw ValidationException("Personal qualities minimum value must be greater than 0.")
        }
    }

    fun validateDifficulty(difficulty: Difficulty?) {
        // No validation required as the field can be null
    }

    fun validateDiscipline(discipline: Discipline?) {
        if (discipline == null || discipline.name.isBlank()) {
            throw ValidationException("Discipline name cannot be empty.")
        }
    }

    fun validateSelfStudyHours(selfStudyHours: Long?) {
        if (selfStudyHours == null || selfStudyHours < 1) {
            throw ValidationException("Self-study hours must be a non-negative value and not 0.")
        }
    }


    fun validateLabWork(labWork: LabWork): Boolean {
        try {
            validateName(labWork.name)
            validateCoordinates(labWork.coordinates)
            validateMinimalPoint(labWork.minimalPoint)
            validatePersonalQualitiesMinimum(labWork.personalQualitiesMinimum)
            validateDifficulty(labWork.difficulty)
            validateDiscipline(labWork.discipline)
            validateSelfStudyHours(labWork.discipline.selfStudyHours)
        } catch (e: ValidationException) {
            throw IllegalArgumentException("Invalid lab work data: ${e.message}")
        }
        return true
    }


}
