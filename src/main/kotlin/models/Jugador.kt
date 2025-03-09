package srangeldev.models

import java.time.LocalDate

/**
 * Clase que representa a un jugador.
 */
class Jugador(
    id: Int,
    nombre: String,
    apellidos: String,
    fechaNacimiento: LocalDate,
    fechaIncorporacion: LocalDate,
    salario: Double,
    paisOrigen: String,
    val posicion: Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidosJugados: Int
): Personal(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, paisOrigen) {
    enum class Posicion {
        PORTERO,
        DEFENSA,
        CENTROCAMPISTA,
        DELANTERO
    }
}