package srangeldev.storage

import org.lighthousegames.logging.logging
import srangeldev.exceptions.PersonalException
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File
import java.io.RandomAccessFile
import java.time.LocalDate

class PersonalStorageBin : PersonalStorageFile {
    private val logger = logging()

    init {
        logger.debug { "Inicializando almacenamiento de personal en Bin" }
    }

    /**
     * Lee el personal de un fichero Bin
     * @param file Fichero Bin
     * @return Lista de personal
     * @throws PersonalException.PersonalStorageException, Si el fichero no existe, no es un fichero o no se puede leer
     */
    override fun readFromFile(file: File): List<Personal> {
        logger.debug { "Leyendo personal de fichero Bin: $file" }
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(
                ".bin",
                true
            )
        ) {
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            throw PersonalException.PersonalStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file")
        }
        val personalList = mutableListOf<Personal>()

        RandomAccessFile(file, "r").use { raf ->
            // Mientras no se haya llegado al final del fichero,
            // leer los datos de cada persona y añadirlos a la lista
            while (raf.filePointer < raf.length()) {
                val id = raf.readInt()
                val nombre = raf.readUTF()
                val apellidos = raf.readUTF()
                val fechaNacimiento = LocalDate.parse(raf.readUTF())
                val fechaIncorporacion = LocalDate.parse(raf.readUTF())
                val salario = raf.readDouble()
                val paisOrigen = raf.readUTF()
                val tipo = raf.readUTF()

                val personal = when (tipo) {
                    "Jugador" -> Jugador(
                        id,
                        nombre,
                        apellidos,
                        fechaNacimiento,
                        fechaIncorporacion,
                        salario,
                        paisOrigen,
                        Jugador.Posicion.valueOf(raf.readUTF()),
                        raf.readInt(),
                        raf.readDouble(),
                        raf.readDouble(),
                        raf.readInt(),
                        raf.readInt()
                    )
                    "Entrenador" -> Entrenador(
                        id,
                        nombre,
                        apellidos,
                        fechaNacimiento,
                        fechaIncorporacion,
                        salario,
                        paisOrigen,
                        Entrenador.Especializacion.valueOf(raf.readUTF())
                    )
                    else -> throw PersonalException.PersonalStorageException("Tipo desconocido: $tipo")
                }

                personalList.add(personal)
            }
        }
        return personalList
    }

    /**
     * Escribe el personal en un fichero Bin
     * @param file Fichero bin
     * @param personalList Lista de personal
     * @throws PersonalException.PersonalStorageException Si el directorio padre del fichero no existe
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
                raf.writeInt(personal.id)
                raf.writeUTF(personal.nombre)
                raf.writeUTF(personal.apellidos)
                raf.writeUTF(personal.fechaNacimiento.toString())
                raf.writeUTF(personal.fechaIncorporacion.toString())
                raf.writeDouble(personal.salario)
                raf.writeUTF(personal.paisOrigen)
                when (personal) {
                    is Jugador -> {
                        raf.writeUTF("Jugador")
                        raf.writeUTF(personal.posicion.name)
                        raf.writeInt(personal.dorsal)
                        raf.writeDouble(personal.altura)
                        raf.writeDouble(personal.peso)
                        raf.writeInt(personal.goles)
                        raf.writeInt(personal.partidosJugados)
                    }
                    is Entrenador -> {
                        raf.writeUTF("Entrenador")
                        raf.writeUTF(personal.especializacion.name)
                    }
                }
            }
        }
    }
}
