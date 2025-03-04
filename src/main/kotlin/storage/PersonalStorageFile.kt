package srangeldev.storage

import srangeldev.models.Personal
import java.io.File

interface PersonalStorageFile {
    fun readFromFile(file: File): List<Personal>
    fun writeToFile(file: File, personalList: List<Personal>)
}