package srangeldev.storage

import srangeldev.models.Personal
import java.io.File

interface PersonalStorage {
    fun readFromFile(file: File, fileFormat: FileFormat): List<Personal>
    fun writeToFile(file: File, fileFormat: FileFormat, personalList: List<Personal>)
}