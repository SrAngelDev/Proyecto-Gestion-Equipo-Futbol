package srangeldev.controller

import Consultas
import org.lighthousegames.logging.logging
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.service.PersonalServiceImpl
import srangeldev.storage.FileFormat
import java.time.LocalDate

/**
 * Controlador principal para gestionar las operaciones relacionadas con el personal.
 */
class Controller {
    private val logger = logging()
    private val service = PersonalServiceImpl()

    /**
     * Carga los datos desde un archivo.
     */
    fun cargarDatos() {
        logger.debug { "Cargando datos" }
        service.importFromFile("data/personal.json", FileFormat.JSON)
    }

    /**
     * Copia los datos a un archivo.
     */
    fun copiarDatos() {
        logger.debug { "Copiando datos" }
        service.exportToFile("data/personal_back.json", FileFormat.JSON)
    }

    /**
     * Crea un nuevo miembro del personal, ya sea entrenador o jugador.
     */
    fun crearMiembro() {
        logger.debug { "Creando miembro" }
        println("Quieres crear entrenador o jugador?")
        when (readln().uppercase()) {
            "ENTRENADOR" -> crearEntrenador()
            "JUGADOR" -> crearJugador()
            else -> println("Tipo no válido.")
        }
    }

    /**
     * Crea un nuevo entrenador.
     */
    private fun crearEntrenador() {
        val id = 0
        println("Introduce el nombre del entrenador")
        val nombre = readln()
        println("Introduce el apellido del entrenador")
        val apellido = readln()
        println("Introduce la fecha de nacimiento del entrenador")
        val fechaNacimiento = leerFecha()
        println("Introduce la fecha de incorporación al club del entrenador")
        val fechaIncorporacion = leerFecha()
        println("Introduce el salario del entrenador")
        val salario = readln().toDouble()
        println("Introduce el país de origen del entrenador")
        val paisOrigen = readln()
        println("Introduce la especialización del entrenador")
        val especializacion = leerEspecializacion()
        val entrenador = Entrenador(id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, paisOrigen, especializacion)
        service.save(entrenador)
    }

    /**
     * Crea un nuevo jugador.
     */
    private fun crearJugador() {
        val id = 0
        println("Introduce el nombre del jugador")
        val nombre = readln()
        println("Introduce el apellido del jugador")
        val apellido = readln()
        println("Introduce la fecha de nacimiento del jugador")
        val fechaNacimiento = leerFecha()
        println("Introduce la fecha de incorporación al club del jugador")
        val fechaIncorporacion = leerFecha()
        println("Introduce el salario del jugador")
        val salario = readln().toDouble()
        println("Introduce el país de origen del jugador")
        val paisOrigen = readln()
        println("Introduce la posición del jugador")
        val posicion = leerPosicion()
        println("Introduce el dorsal del jugador")
        val dorsal = readln().toInt()
        println("Introduce la altura del jugador")
        val altura = readln().toDouble()
        println("Introduce el peso del jugador")
        val peso = readln().toDouble()
        println("Introduce los goles del jugador")
        val goles = readln().toInt()
        println("Introduce los partidos jugados del jugador")
        val partidosJugados = readln().toInt()
        val jugador = Jugador(id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, paisOrigen, posicion, dorsal, altura, peso, goles, partidosJugados)
        service.save(jugador)
    }

    /**
     * Actualiza los datos de un miembro del personal.
     */
    fun actualizarMiembro() {
        logger.debug { "Actualizando miembro" }
        println("Introduce el id del miembro a actualizar")
        val id = readln().toInt()
        val miembro = service.getById(id)
        when (miembro) {
            is Entrenador -> actualizarEntrenador()
            is Jugador -> actualizarJugador()
            else -> throw kotlin.IllegalArgumentException("Miembro no encontrado.")
        }
    }

    /**
     * Actualiza un jugador.
     *
     * @return El jugador actualizado.
     */
    private fun actualizarJugador(): Jugador {
        logger.debug { "Actualizando jugador" }
        println("Introduce el nombre del jugador")
        val nombre = readln()
        println("Introduce el apellido del jugador")
        val apellido = readln()
        println("Introduce la fecha de nacimiento del jugador")
        val fechaNacimiento = leerFecha()
        println("Introduce la fecha de incorporación al club del jugador")
        val fechaIncorporacion = leerFecha()
        println("Introduce el salario del jugador")
        val salario = readln().toDouble()
        println("Introduce el país de origen del jugador")
        val paisOrigen = readln()
        println("Introduce la posición del jugador")
        val posicion = leerPosicion()
        println("Introduce el dorsal del jugador")
        val dorsal = readln().toInt()
        println("Introduce la altura del jugador")
        val altura = readln().toDouble()
        println("Introduce el peso del jugador")
        val peso = readln().toDouble()
        println("Introduce los goles del jugador")
        val goles = readln().toInt()
        println("Introduce los partidos jugados del jugador")
        val partidosJugados = readln().toInt()
        val jugador = Jugador(0, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, paisOrigen, posicion, dorsal, altura, peso, goles, partidosJugados)
        return service.update(jugador.id, jugador) as Jugador
    }

    /**
     * Actualiza un entrenador.
     *
     * @return El entrenador actualizado.
     */
    private fun actualizarEntrenador(): Entrenador {
        logger.debug { "Actualizando entrenador" }
        println("Introduce el nombre del entrenador")
        val nombre = readln()
        println("Introduce el apellido del entrenador")
        val apellido = readln()
        println("Introduce la fecha de nacimiento del entrenador")
        val fechaNacimiento = leerFecha()
        println("Introduce la fecha de incorporación al club del entrenador")
        val fechaIncorporacion = leerFecha()
        println("Introduce el salario del entrenador")
        val salario = readln().toDouble()
        println("Introduce el país de origen del entrenador")
        val paisOrigen = readln()
        println("Introduce la especialización del entrenador")
        val especializacion = leerEspecializacion()
        val entrenador = Entrenador(0, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, paisOrigen, especializacion)
        return service.update(entrenador.id, entrenador) as Entrenador
    }

    /**
     * Elimina un miembro del personal.
     */
    fun eliminarMiembro() {
        logger.debug { "Eliminando miembro" }
        println("Introduce el id del miembro a eliminar")
        val id = readln().toInt()
        val result = service.delete(id)
        if (result is Entrenador) {
            println("Entrenador eliminado: $result")
        } else if (result is Jugador) {
            println("Jugador eliminado: $result")
        } else {
            println("Miembro no encontrado.")
        }
    }



    /**
     * Realiza consultas sobre los datos del personal.
     */
    fun realizarConsultas() {
        logger.debug { "Realizando consultas" }
        println("Realizando consultas")
        val consultas = Consultas()
        consultas.realizarConsultas()
    }

    /**
     * Lee una fecha desde la entrada estándar.
     *
     * @return La fecha leída.
     */
    fun leerFecha(): LocalDate {
        println("Introduce el día")
        val dia = readln().toInt()
        println("Introduce el mes")
        val mes = readln().toInt()
        println("Introduce el año")
        val año = readln().toInt()
        return LocalDate.of(año, mes, dia)
    }

    /**
     * Lee la especialización de un entrenador desde la entrada estándar.
     *
     * @return La especialización leída.
     */
    fun leerEspecializacion(): Entrenador.Especializacion {
        println("Introduce la especialización (principal, asistente, porteros)")
        return when (readln().uppercase()) {
            "PRINCIPAL" -> Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
            "ASISTENTE" -> Entrenador.Especializacion.ENTRENADOR_ASISTENTE
            "PORTEROS" -> Entrenador.Especializacion.ENTRENADOR_PORTEROS
            else -> throw IllegalArgumentException("Especialización no válida.")
        }
    }

    /**
     * Lee la posición de un jugador desde la entrada estándar.
     *
     * @return La posición leída.
     */
    fun leerPosicion(): Jugador.Posicion {
        println("Introduce la posición (portero, defensa, centrocampista, delantero)")
        return when (readln().uppercase()) {
            "PORTERO" -> Jugador.Posicion.PORTERO
            "DEFENSA" -> Jugador.Posicion.DEFENSA
            "CENTROCAMPISTA" -> Jugador.Posicion.CENTROCAMPISTA
            "DELANTERO" -> Jugador.Posicion.DELANTERO
            else -> throw IllegalArgumentException("Posición no válida.")
        }
    }
}