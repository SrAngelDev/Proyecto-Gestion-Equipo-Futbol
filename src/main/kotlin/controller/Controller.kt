package srangeldev.controller

import org.lighthousegames.logging.logging

class Controller {
    private val logger = logging()

    fun cargarDatos() {
        logger.debug { "Cargando datos" }
    }

    fun crearMiembro() {
        logger.debug { "Creando miembro" }
    }

    fun actualizarMiembro() {
        logger.debug { "Actualizando miembro" }
    }

    fun eliminarMiembro() {
        logger.debug { "Eliminando miembro" }
    }

    fun copiarDatos() {
        logger.debug { "Copiando datos" }
    }

    fun realizarConsultas() {
        logger.debug { "Realizando consultas" }
    }
}