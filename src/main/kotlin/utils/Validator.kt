package utils

import data.LabWork
import utils.LabWorkReader

class Validator {

    fun validateLabWorkName(name: String?): Boolean {
        return name != null && name.isNotBlank()
    }

    fun validateCoordinatesX(x: Long?): Boolean {
        return x != null && x <= 608
    }

    fun validateCoordinatesY(y: Double?): Boolean {
        return y != null
    }

    fun validateMinimalPoint(minimalPoint: Int?): Boolean {
        return minimalPoint != null && minimalPoint > 0
    }

    fun validatePersonalQualitiesMinimum(personalQualitiesMinimum: Int?): Boolean {
        return personalQualitiesMinimum != null && personalQualitiesMinimum > 0
    }

    fun validateDisciplineName(name: String?): Boolean {
        return name != null && name.isNotBlank()
    }

    fun validateSelfStudyHours(selfStudyHours: Long?): Boolean {
        return selfStudyHours != null
    }

    fun validateLabWork(labWork: LabWork): Boolean {
        return validateLabWorkName(labWork.name) &&
                validateCoordinatesX(labWork.coordinates.x) &&
                validateCoordinatesY(labWork.coordinates.y) &&
                validateMinimalPoint(labWork.minimalPoint) &&
                validatePersonalQualitiesMinimum(labWork.personalQualitiesMinimum) &&
                validateDisciplineName(labWork.discipline.name) &&
                validateSelfStudyHours(labWork.discipline.selfStudyHours)
    }

    fun validateAndReadLabWork(readLineFn: () -> String): LabWork {
        val labWorkReader = LabWorkReader(readLineFn)
        val labWork = labWorkReader.readLabWork()
        if (!validateLabWork(labWork)) {
            throw IllegalArgumentException("Invalid lab work data")
        }
        return labWork
    }
}
