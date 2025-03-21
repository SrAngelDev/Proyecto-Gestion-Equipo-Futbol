package srangeldev.storage

import org.lighthousegames.logging.logging
import srangeldev.dto.PersonalCsvDto
import srangeldev.exceptions.PersonalException
import srangeldev.mapper.toCsvDto
import srangeldev.mapper.toEntrenador
import srangeldev.mapper.toJugador
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File
import java.time.LocalDate

/**
 * Clase que implementa la interfaz PersonalStorageFile para el almacenamiento de datos de personal en formato CSV.
 */
class PersonalStorageCsv : PersonalStorageFile {
    private val logger = logging()

    init {
        logger.debug { "Inicializando almacenamiento de personal en formato CSV" }
    }

    /**
     * Lee una lista de objetos Personal desde un archivo CSV.
     *
     * @param file El archivo CSV desde el cual leer los datos.
     * @return Una lista de objetos Personal leídos desde el archivo CSV.
     * @throws PersonalException.PersonalStorageException Si el archivo no existe, no es un archivo, no se puede leer, está vacío o no tiene extensión CSV.
     */
    override fun readFromFile(file: File): List<Personal> {
        logger.debug { "Leyendo personal de fichero CSV: $file" }
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".csv", true)) {
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            throw PersonalException.PersonalStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file")
        }
        return file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { it.map { it.trim() } }
            .map {
                val dto = PersonalCsvDto(
                    id = it[0].toInt(),
                    nombre = it[1],
                    apellidos = it[2],
                    fechaNacimiento = it[3],
                    fechaIncorporacion = it[4],
                    salario = it[5].toDouble(),
                    paisOrigen = it[6],
                    rol = it[7],
                    especializacion = it.getOrNull(8),
                    posicion = it.getOrNull(9),
                    dorsal = it.getOrNull(10)?.toIntOrNull(),
                    altura = it.getOrNull(11)?.toDoubleOrNull(),
                    peso = it.getOrNull(12)?.toDoubleOrNull(),
                    goles = it.getOrNull(13)?.toIntOrNull(),
                    partidosJugados = it.getOrNull(14)?.toIntOrNull()
                )
                when (dto.rol) {
                    "Entrenador" -> dto.toEntrenador()
                    "Jugador" -> dto.toJugador()
                    else -> throw PersonalException.PersonalStorageException("Tipo de personal desconocido: ${dto.rol}")
                }
            }
    }

    /**
     * Escribe una lista de objetos Personal en un archivo CSV.
     *
     * @param file El archivo CSV en el cual escribir los datos.
     * @param personalList La lista de objetos Personal a escribir en el archivo CSV.
     * @throws PersonalException.PersonalStorageException Si el directorio padre del archivo no existe, no es un directorio o el archivo no tiene extensión CSV.
     */
    override fun writeToFile(file: File, personalList: List<Personal>) {
        logger.debug { "Escribiendo personal en fichero CSV: $file" }

        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".csv", true)) {
            logger.error { "El directorio padre del fichero no existe o no es un directorio o el fichero no tiene extensión CSV: ${file.parentFile.absolutePath}" }
            throw PersonalException.PersonalStorageException("El directorio padre del fichero no existe o no es un directorio o el fichero no tiene extensión CSV: ${file.parentFile.absolutePath}")
        }

        file.writeText(
            "tipo,id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,paisOrigen,especializacion,posicion,dorsal,altura,peso,goles,partidosJugados\n"
        )

        personalList.forEach { personal ->
            val dto = when (personal) {
                is Entrenador -> personal.toCsvDto()
                is Jugador -> personal.toCsvDto()
                else -> throw IllegalArgumentException("Tipo de personal desconocido")
            }

            val data = listOf(
                dto.rol,
                dto.id,
                dto.nombre,
                dto.apellidos,
                dto.fechaNacimiento,
                dto.fechaIncorporacion,
                dto.salario,
                dto.paisOrigen,
                dto.especializacion ?: "",
                dto.posicion ?: "",
                dto.dorsal?.toString() ?: "",
                dto.altura?.toString() ?: "",
                dto.peso?.toString() ?: "",
                dto.goles?.toString() ?: "",
                dto.partidosJugados?.toString() ?: ""
            ).joinToString(",")

            file.appendText("$data\n")
        }
    }
}