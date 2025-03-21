package srangeldev.repository

import org.lighthousegames.logging.logging
import srangeldev.dto.PersonalXmlDto
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal

/**
 * Implementación del repositorio de personal.
 */
class PersonalRespositoryImpl : PersonalRepository {
    private val logger = logging()
    private val personal = mutableMapOf<Int, Personal>()
    private var nextId = 1

    init {
        logger.debug { "Inicializando repositorio de personal" }
    }

    /**
     * Genera un ID único para cada objeto personal.
     */
    private fun generatedId(): Int {
        return nextId++
    }

    /**
     * Obtiene una lista de todos los objetos personal.
     *
     * @return Una lista de objetos personal.
     */
    override fun getAll(): List<Personal> {
        logger.debug { "Obteniendo a todo el personal" }
        return personal.values.toList()
    }

    /**
     * Obtiene un objeto personal por su ID.
     *
     * @param id El ID del objeto personal a obtener.
     * @return El objeto personal con el ID especificado, o null si no se encuentra.
     */
    override fun getById(id: Int): Personal? {
        logger.debug { "Obteniendo personal por ID: $id" }
        return personal[id]
    }

    /**
     * Guarda un objeto personal.
     *
     * @param entidad El objeto personal a guardar.
     * @return El objeto personal guardado.
     */
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

    /**
     * Actualiza un objeto personal por su ID.
     *
     * @param id El ID del objeto personal a actualizar.
     * @param entidad El objeto personal con los datos actualizados.
     * @return El objeto personal actualizado, o null si no se encuentra.
     */
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

    /**
     * Elimina un objeto personal por su ID.
     *
     * @param id El ID del objeto personal a eliminar.
     * @return El objeto personal eliminado.
     */
    override fun delete(id: Int): Personal? {
        logger.debug { "Eliminando personal con ID: $id" }
        return personal.remove(id)
    }
}