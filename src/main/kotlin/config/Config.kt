package srangeldev.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.pathString

object Config {

    private val log = logging()

    // Inicializa de manera perezosa la configuración
    val configProperties: ConfigProperties by lazy {
        loadConfig()
    }

    private fun loadConfig(): ConfigProperties {
        log.debug { "Cargando configuración" }
        val properties = Properties()

        // Carga el archivo de propiedades desde la carpeta resources
        val propertiesStream = this::class.java.getResourceAsStream("/config.properties")
            ?: throw RuntimeException("No se ha encontrado el fichero de configuración")

        properties.load(propertiesStream)

        val directorioActual = System.getProperty("user.dir") // Directorio actual de ejecución

        // Obtiene las propiedades, proporcionando valores por defecto si no se encuentran
        val dataDirProperty = properties.getProperty("data.directory") ?: "data"
        val backupDirProperty = properties.getProperty("backup.directory") ?: "backup"
        val inputFormatsProperty = properties.getProperty("input.formats") ?: "CSV,XML,JSON,BIN"
        val outputFormatsProperty = properties.getProperty("output.formats") ?: "CSV,XML,JSON,BIN"

        // Obtiene los directorios de datos y backup
        val dataDir = Path.of(directorioActual, dataDirProperty).pathString
        val backupDir = Path.of(directorioActual, backupDirProperty).pathString

        // Cremos las capretas si no existen
        makeProgramDirectories(dataDir, backupDir)

        return ConfigProperties(dataDir, backupDir, inputFormatsProperty, outputFormatsProperty)
    }

    /**
     * Crea los directorios necesarios para el programa
     * @param directories Lista de directorios a crear, parametros de tipo vararg, longitud variable
     */
    private fun makeProgramDirectories(vararg directories: String) {
        directories.forEach {
            val dir = java.io.File(it)
            log.debug { "Creando directorio: $it" }
            Files.createDirectories(dir.toPath()) // Crea el directorio si no existe
        }
    }

    // Clase para encapsular las propiedades de la configuración, y valores por defecto
    data class ConfigProperties(
        val dataDir: String = "data",
        val backupDir: String = "backup",
        val inputFormats: String = "CSV,XML,JSON,BIN",
        val outputFormats: String = "CSV,XML,JSON,BIN"
    )
}