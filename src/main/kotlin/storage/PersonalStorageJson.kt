package srangeldev.storage

import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging
import srangeldev.dto.PersonalJsonDto
import srangeldev.exceptions.PersonalException
import srangeldev.mapper.toJsonDto
import srangeldev.mapper.toEntrenador
import srangeldev.mapper.toJugador
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File

class PersonalStorageJson: PersonalStorageFile {

    private val logger = logging()

    init {
        logger.debug { "Inicializando almacenamiento de productos en JSON" }
    }

    override fun readFromFile(file: File): List<Personal> {
        logger.debug { "Leyendo personal de fichero JSON: $file" }
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".json")) {
            logger.error { "El fichero no existe, o no es un fichero o no se puede leer: $file" }
            throw PersonalException.PersonalStorageException("El fichero no existe, o no es un fichero o no se puede leer: $file")
        }
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString<List<PersonalJsonDto>>(file.readText()).map {
            when (it.rol) {
                "Entrenador" -> it.toEntrenador()
                "Jugador" -> it.toJugador()
                else -> throw IllegalArgumentException("Tipo de personal desconocido: ${it.rol}")
            }
        }
    }

    override fun writeToFile(file: File, personal: List<Personal>) {
        logger.debug { "Escribiendo personal en fichero JSON: $file" }
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".json")) {
            logger.error { "El directorio padre del fichero no existe: ${file.parentFile.absolutePath}" }
            throw PersonalException.PersonalStorageException("El directorio padre del fichero no existe: ${file.parentFile.absolutePath}")
        }
        val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
        file.writeText(json.encodeToString<List<PersonalJsonDto>>(personal.map {
            when (it) {
                is Entrenador -> it.toJsonDto()
                is Jugador -> it.toJsonDto()
                else -> throw IllegalArgumentException("Tipo de personal desconocido")
            }
        }))
    }
}