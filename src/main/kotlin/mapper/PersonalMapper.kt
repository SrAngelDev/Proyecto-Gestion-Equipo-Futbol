package srangeldev.mapper

import srangeldev.dto.PersonalDto
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.time.LocalDate

/**
 * Función de mapeo de entrenador a dto.
 */
fun Entrenador.toDto(): PersonalDto {
    return PersonalDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        especializacion = this.especializacion.toString()
    )
}

/**
 * Función de mapeo de jugador a dto.
 */
fun Jugador.toDto(): PersonalDto {
    return PersonalDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        posicion = this.posicion.toString(),
        dorsal = this.dorsal.toString(),
        altura = this.altura.toString(),
        peso = this.peso.toString(),
        goles = this.goles.toString(),
        partidosJugados = this.partidosJugados.toString()
    )
}

/**
 * Función de mapeo de dto a entrenador.
 */
fun PersonalDto.toEntrenador(): Entrenador {
    val especializacion = if (this.especializacion.isNullOrEmpty()) {
        Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
    } else {
        try {
            Entrenador.Especializacion.valueOf(this.especializacion.toString().uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Especialización no válida: ${this.especializacion}")
        }
    }

    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        especializacion = especializacion
    )
}

/**
 * Función de mapeo de dto a jugador.
 */
fun PersonalDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        posicion = Jugador.Posicion.valueOf(this.posicion.toString()),
        dorsal = this.dorsal?.toInt() ?: 0,
        altura = this.altura?.toDouble() ?: 0.0,
        peso = this.peso?.toDouble() ?: 0.0,
        goles = this.goles?.toInt() ?: 0,
        partidosJugados = this.partidosJugados?.toInt() ?: 0
    )
}