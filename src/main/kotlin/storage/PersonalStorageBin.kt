package srangeldev.storage

import org.lighthousegames.logging.logging
import srangeldev.dto.PersonalBinDto
import srangeldev.exceptions.PersonalException
import srangeldev.mapper.toBinDto
import srangeldev.mapper.toEntrenador
import srangeldev.mapper.toJugador
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File
import java.io.RandomAccessFile

/**
 * Clase que implementa la interfaz PersonalStorageFile para el almacenamiento de datos de personal en formato Bin.
 */
class PersonalStorageBin : PersonalStorageFile {
    override fun readFromFile(file: File): List<Personal> {
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".bin", true)) {
            MENSAJE DE ERROR Y EXCEPCION
        }
        val personalList = mutableListOf<Personal>()
        RandomAccessFile(file, "r").use { raf ->
            while (raf.filePointer < raf.length()) {
                val dto = PersonalBinDto(
                    id = raf.readInt(),
                    nombre = raf.readUTF(),
                    salario = raf.readDouble(),
                )
                val personal = when (dto.rol) {
                    "Entrenador" -> dto.toEntrenador()
                }
                personalList.add(personal)
            }
        }
        return personalList
    }
    override fun writeToFile(file: File, personalList: List<Personal>) {
        val parentFile = file.parentFile
        if (parentFile == null || !parentFile.exists() || !parentFile.isDirectory || !file.name.endsWith(".bin", true)) {
            MENSAJE DE ERROR Y EXCEPCION
        }
        RandomAccessFile(file, "rw").use { raf ->
            raf.setLength(0) // Limpiar el archivo antes de escribir
            for (personal in personalList) {
                val dto = when (personal) {
                    is Entrenador -> personal.toBinDto()
                    is Jugador -> personal.toBinDto()
                    else -> throw IllegalArgumentException("Tipo de personal desconocido")
                }
                raf.writeInt(dto.id)
                raf.writeUTF(dto.nombre)
                raf.writeDouble(dto.altura ?: 0.0)
            }
        }
    }
}