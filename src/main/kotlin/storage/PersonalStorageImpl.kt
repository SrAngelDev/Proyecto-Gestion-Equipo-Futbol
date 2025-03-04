package srangeldev.storage

import org.lighthousegames.logging.logging
import srangeldev.models.Personal
import java.io.File

class PersonalStorageImpl(
    private val storageJson: PersonalStorageFile = PersonalStorageJson(),
    private val storageCsv: PersonalStorageFile = PersonalStorageCsv(),
    private val storageXml: PersonalStorageFile = PersonalStorageXml(),
    private val storageBin: PersonalStorageFile = PersonalStorageBin()
): PersonalStorage {

    private val logger = logging()

    override fun readFromFile(file: File, fileFormat: FileFormat): List<Personal> {
        logger.debug { "Leyendo personal de fichero: $file" }
        return when (fileFormat) {
            FileFormat.JSON -> storageJson.readFromFile(file)
            FileFormat.CSV -> storageCsv.readFromFile(file)
            FileFormat.XML -> storageXml.readFromFile(file)
            FileFormat.BIN -> storageBin.readFromFile(file)
            FileFormat.DEFAULT -> storageJson.readFromFile(file) // Por defecto se asume JSON
        }
    }

    override fun writeToFile(file: File, fileFormat: FileFormat, personalList: List<Personal>) {
        logger.debug { "Escribiendo personal en fichero: $file" }
        when (fileFormat) {
            FileFormat.JSON -> storageJson.writeToFile(file, personalList)
            FileFormat.CSV -> storageCsv.writeToFile(file, personalList)
            FileFormat.XML -> storageXml.writeToFile(file, personalList)
            FileFormat.BIN -> storageBin.writeToFile(file, personalList)
            FileFormat.DEFAULT -> storageJson.writeToFile(file, personalList) // Por defecto se asume JSON
        }
    }
}