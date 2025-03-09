package srangeldev.mapper

import srangeldev.dto.PersonalDto
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
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
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados
    )
}

/**
 * Función de mapeo de dto a entrenador.
 */
fun PersonalDto.toEntrenador(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        especializacion = Entrenador.Especializacion.valueOf(this.especializacion.toString())
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
        dorsal = this.dorsal ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0
    )
}