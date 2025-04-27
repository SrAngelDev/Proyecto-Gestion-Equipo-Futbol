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

class PersonalStorageBin : PersonalStorageFile {

    private val logger = logging()

    override fun readFromFile(file: File): List<Personal> {
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(
                ".bin",
                true
            )
        ) {
            logger.error { "El archivo no se puede leer, no es accesible o no es binario" }
            throw PersonalException.PersonalStorageException("El archivo no se puede leer, no es accesible o no es binario")
        }
        val personalList = mutableListOf<Personal>()
        RandomAccessFile(file, "r").use { raf ->
            while (raf.filePointer < raf.length()) {
                val dto = PersonalBinDto(
                    id = raf.readInt(),
                    nombre = raf.readUTF(),
                    apellidos = raf.readUTF(),
                    fechaNacimiento = raf.readUTF(),
                    fechaIncorporacion = raf.readUTF(),
                    pais = raf.readUTF(),
                    rol = raf.readUTF(),
                    salario = raf.readDouble(),
                )
                val personal = when (dto.rol) {
                    "Entrenador" -> dto.toEntrenador()
                    "Jugador" -> dto.toJugador()
                    else -> throw IllegalArgumentException("Rol desconocido: ${dto.rol}")
                }
                personalList.add(personal)
            }
        }
        return personalList
    }

    override fun writeToFile(personalList: List<Personal>) {
        val file = File("backup/personal_back.bin")
        val parentFile = file.parentFile
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs()
        }
        if (!file.name.endsWith(".bin", true)) {
            logger.error { "El archivo no es válido para escritura" }
            throw PersonalException.PersonalStorageException("El archivo no es válido para escritura")
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
                raf.writeUTF(dto.apellidos)
                raf.writeUTF(dto.fechaNacimiento)
                raf.writeUTF(dto.fechaIncorporacion)
                raf.writeUTF(dto.pais)
                raf.writeUTF(dto.rol)
                raf.writeDouble(dto.salario)
            }
        }
    }
}