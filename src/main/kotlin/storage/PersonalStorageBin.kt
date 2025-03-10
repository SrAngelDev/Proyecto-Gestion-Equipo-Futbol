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
    private val logger = logging()

    init {
        logger.debug { "Inicializando almacenamiento de personal en Bin" }
    }

    /**
     * Lee una lista de objetos Personal desde un archivo Bin.
     *
     * @param file El archivo Bin desde el cual leer los datos.
     * @return Una lista de objetos Personal leídos desde el archivo Bin.
     * @throws PersonalException.PersonalStorageException Si el archivo no existe, no es un archivo, no se puede leer, está vacío o no tiene extensión Bin.
     */
    override fun readFromFile(file: File): List<Personal> {
        logger.debug { "Leyendo personal de fichero Bin: $file" }
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".bin", true)) {
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            throw PersonalException.PersonalStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file")
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
                    salario = raf.readDouble(),
                    pais = raf.readUTF(),
                    rol = raf.readUTF(),
                    especialidad = raf.readUTF(),
                    posicion = raf.readUTF(),
                    dorsal = raf.readInt(),
                    altura = raf.readDouble(),
                    peso = raf.readDouble(),
                    goles = raf.readInt(),
                    partidosJugados = raf.readInt()
                )
                val personal = when (dto.rol) {
                    "Entrenador" -> dto.toEntrenador()
                    "Jugador" -> dto.toJugador()
                    else -> throw PersonalException.PersonalStorageException("Tipo de personal desconocido: ${dto.rol}")
                }
                personalList.add(personal)
            }
        }
        return personalList
    }

    /**
     * Escribe una lista de objetos Personal en un archivo Bin.
     *
     * @param file El archivo Bin en el cual escribir los datos.
     * @param personalList La lista de objetos Personal a escribir en el archivo Bin.
     * @throws PersonalException.PersonalStorageException Si el directorio padre del archivo no existe, no es un directorio o el archivo no tiene extensión Bin.
     */
    override fun writeToFile(file: File, personalList: List<Personal>) {
        logger.debug { "Escribiendo personal en fichero Bin: $file" }

        val parentFile = file.parentFile
        if (parentFile == null || !parentFile.exists() || !parentFile.isDirectory || !file.name.endsWith(".bin", true)) {
            logger.error { "El directorio padre del fichero no existe: ${parentFile?.absolutePath}" }
            throw PersonalException.PersonalStorageException("El directorio padre del fichero no existe: ${parentFile?.absolutePath}")
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
                raf.writeDouble(dto.salario)
                raf.writeUTF(dto.pais)
                raf.writeUTF(dto.rol)
                raf.writeUTF(dto.especialidad ?: "")
                raf.writeUTF(dto.posicion ?: "")
                raf.writeInt(dto.dorsal ?: 0)
                raf.writeDouble(dto.altura ?: 0.0)
                raf.writeDouble(dto.peso ?: 0.0)
                raf.writeInt(dto.goles ?: 0)
                raf.writeInt(dto.partidosJugados ?: 0)
            }
        }
    }
}