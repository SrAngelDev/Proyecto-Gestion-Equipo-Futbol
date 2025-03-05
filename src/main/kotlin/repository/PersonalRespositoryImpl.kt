package srangeldev.repository

import org.lighthousegames.logging.logging
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal

class PersonalRespositoryImpl : PersonalRepository {
    private val logger = logging()
    private val personal = mutableMapOf<Int, Personal>()
    private var nextId = 1

    private fun generatedId(): Int {
        return nextId++
    }

    override fun getAll(): List<Personal> {
        logger.debug { "Obteniendo a todo el personal" }
        return personal.values.toList()
    }

    override fun getById(id: Int): Personal? {
        logger.debug { "Obteniendo personal por ID: $id" }
        return personal[id]
    }

    override fun save(entidad: Personal): Personal {
        logger.debug { "Guardando personal: $entidad" }
        val newId = generatedId()
        val newPersonal = when (entidad) {
            is Jugador -> Jugador(
                id = newId,
                nombre = entidad.nombre,
                apellidos = entidad.apellidos,
                fechaNacimiento = entidad.fechaNacimiento,
                fechaIncorporacion = entidad.fechaIncorporacion,
                salario = entidad.salario,
                paisOrigen = entidad.paisOrigen,
                posicion = entidad.posicion,
                dorsal = entidad.dorsal,
                altura = entidad.altura,
                peso = entidad.peso,
                goles = entidad.goles,
                partidosJugados = entidad.partidosJugados
            )
            is Entrenador -> Entrenador(
                id = newId,
                nombre = entidad.nombre,
                apellidos = entidad.apellidos,
                fechaNacimiento = entidad.fechaNacimiento,
                fechaIncorporacion = entidad.fechaIncorporacion,
                salario = entidad.salario,
                paisOrigen = entidad.paisOrigen,
                especializacion = entidad.especializacion
            )
            else -> throw IllegalArgumentException("Tipo desconocido de Personal")
        }

        personal[newId] = newPersonal
        return newPersonal
    }

    override fun update(id: Int, entidad: Personal): Personal? {
        logger.debug { "Actualizando personal con ID: $id" }

        if (!personal.containsKey(id)) return null
        val updated = when (entidad) {
            is Jugador -> Jugador(
                id = id,
                nombre = entidad.nombre,
                apellidos = entidad.apellidos,
                fechaNacimiento = entidad.fechaNacimiento,
                fechaIncorporacion = entidad.fechaIncorporacion,
                salario = entidad.salario,
                paisOrigen = entidad.paisOrigen,
                posicion = entidad.posicion,
                dorsal = entidad.dorsal,
                altura = entidad.altura,
                peso = entidad.peso,
                goles = entidad.goles,
                partidosJugados = entidad.partidosJugados
            )
            is Entrenador -> Entrenador(
                id = id,
                nombre = entidad.nombre,
                apellidos = entidad.apellidos,
                fechaNacimiento = entidad.fechaNacimiento,
                fechaIncorporacion = entidad.fechaIncorporacion,
                salario = entidad.salario,
                paisOrigen = entidad.paisOrigen,
                especializacion = entidad.especializacion
            )
            else -> throw IllegalArgumentException("Tipo desconocido de Personal")
        }
        personal[id] = updated
        return updated
    }

    override fun delete(id: Int): Personal? {
        logger.debug { "Eliminando personal con ID: $id" }
        return personal.remove(id)
    }
}