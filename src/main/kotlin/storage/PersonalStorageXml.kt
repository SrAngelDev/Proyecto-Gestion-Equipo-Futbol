package srangeldev.storage

import nl.adaptivity.xmlutil.serialization.XML
import org.lighthousegames.logging.logging
import srangeldev.dto.PersonalDto
import srangeldev.dto.PersonalsDto
import srangeldev.exceptions.PersonalException
import srangeldev.mapper.toEntrenador
import srangeldev.mapper.toJugador
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File

/**
 * Clase que implementa la interfaz PersonalStorageFile para almacenar y recuperar objetos de tipo Personal en formato XML.
 */
class PersonalStorageXml: PersonalStorageFile {
    private val logger = logging()

    init {
        logger.debug { "Inicializando almacenamiento de personal en formato XML" }
    }

    /**
     * Metodo que lee un fichero XML y devuelve una lista de objetos de tipo Personal.
     * @param file Fichero XML a leer.
     * @return Lista de objetos de tipo Personal.
     */
    override fun readFromFile(file: File): List<Personal> {
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || file.name.endsWith(
                ".xml",
                true
            )
        ) {
            logger.error {
                "El fichero no existe o es un fichero que no se puede leer: $file"
                }
                throw PersonalException.PersonalStorageException("El fichero no existe o es un fichero que no se puede leer: $file")
        }
        val xml = XML {}
        val xmlString = file.readText()
        val personalDto: PersonalsDto = xml.decodeFromString(PersonalsDto.serializer(), xmlString)
        val personalListDto = personalDto.equipo
        return personalListDto.map {
            when (it) {
                is Entrenador -> it.toEntrenador()
                else -> it.toJugador()
            }
        }
    }

    override fun writeToFile(file: File, personalList: List<Personal>) {
        logger.debug { "Escribiendo personal en formato de fichero XML: $file" }
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".xml", true)) {
            logger.error {"El directorio padre del fichero no existe o no es un directorio o el fichero no tiene extensión XML: ${file.parentFile.absolutePath}"}
            throw PersonalException.PersonalStorageException("El directorio padre del fichero no existe o no es un directorio o el fichero no tiene extensión XML: ${file.parentFile.absolutePath}")
        }
        val xml = XML {}
        val personalListDto = personalList.map {
            when (it) {
                is Entrenador -> PersonalDto.toEntrenador(it)
                else -> PersonalDto.toJugador(it)
            }
        }
        val personalDto = PersonalsDto(personalListDto)
        file.writeText(xml.encodeToString<PersonalsDto>(personalDto))
    }
}