package srangeldev.storage

import srangeldev.models.Personal
import java.io.File

class PersonalStorageJson: PersonalStorageFile {
    override fun readFromFile(file: File): List<Personal> {
        TODO("Not yet implemented read storageJson")
    }

    override fun writeToFile(file: File, personalList: List<Personal>) {
        TODO("Not yet implemented write storageJson")
    }
}