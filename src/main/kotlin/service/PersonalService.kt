package srangeldev.service

import srangeldev.models.Personal
import srangeldev.storage.FileFormat

interface PersonalService {

    fun importFromFile(filePath: String, format: FileFormat = FileFormat.JSON)
    fun exportToFile(filePath: String, format: FileFormat = FileFormat.JSON)

    fun getAll(): List<Personal>
    fun getById(id: Int): Personal?
    fun save(personal: Personal): Personal
    fun update(id: Int, personal: Personal): Personal?
    fun delete(id: Int): Personal
}