package srangeldev.storage

import org.lighthousegames.logging.logging
import srangeldev.exceptions.PersonalException
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File
import java.time.LocalDate
import java.util.*

class PersonalStorageCsv : PersonalStorageFile {
    private val logger = logging()

    init {
        logger.debug { "Inicializando almacenamiento de personal en formato CSV" }
    }

    /**
     * Lee el personal de un fichero CSV
     * @param file Fichero CSV
     * @return Lista de personal
     * @throws PersonalException.PersonalStorageException Si el fichero no existe, no es un fichero o no se puede leer
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
                when (it[0]) {
                    "Entrenador" -> Entrenador(
                        id = it[1].toInt(),
                        nombre = it[2],
                        apellidos = it[3],
                        fechaNacimiento = LocalDate.parse(it[4]),
                        fechaIncorporacion = LocalDate.parse(it[5]),
                        salario = it[6].toDouble(),
                        paisOrigen = it[7],
                        especializacion = Entrenador.Especializacion.valueOf(it[8])
                    )
                    "Jugador" -> Jugador(
                        id = it[1].toInt(),
                        nombre = it[2],
                        apellidos = it[3],
                        fechaNacimiento = LocalDate.parse(it[4]),
                        fechaIncorporacion = LocalDate.parse(it[5]),
                        salario = it[6].toDouble(),
                        paisOrigen = it[7],
                        posicion = Jugador.Posicion.valueOf(it[8].trim().uppercase()),
                        dorsal = it[9].toInt(),
                        altura = it[10].toDouble(),
                        peso = it[11].toDouble(),
                        goles = it[12].toInt(),
                        partidosJugados = it[13].toInt()
                    )
                    else -> throw PersonalException.PersonalStorageException("Tipo de personal desconocido: ${it[0]}")
                }
            }
    }

    /**
     * Escribe el personal en un fichero CSV
     * @param file Fichero CSV
     * @param personalList Lista de personal
     * @throws PersonalException.PersonalStorageException Si el directorio padre del fichero no existe
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
            val data = when (personal) {
                is Entrenador -> {
                    "Entrenador," +
                            "${personal.id}," +
                            "${personal.nombre}," +
                            "${personal.apellidos}," +
                            "${personal.fechaNacimiento}," +
                            "${personal.fechaIncorporacion}," +
                            "${personal.salario}," +
                            "${personal.paisOrigen}," +
                            "${personal.especializacion},,,,," // Campos vacíos
                }
                is Jugador -> {
                    "Jugador," +
                            "${personal.id}," +
                            "${personal.nombre}," +
                            "${personal.apellidos}," +
                            "${personal.fechaNacimiento}," +
                            "${personal.fechaIncorporacion}," +
                            "${personal.salario}," +
                            "${personal.paisOrigen}," +
                            "," + // Campo vacío para especialización
                            "${personal.posicion}," +
                            "${personal.dorsal}," +
                            "${personal.altura}," +
                            "${personal.peso}," +
                            "${personal.goles}," +
                            "${personal.partidosJugados}"
                }
                else -> throw IllegalArgumentException("Tipo de personal desconocido")
            }

            file.appendText("$data\n")
        }
    }
}
