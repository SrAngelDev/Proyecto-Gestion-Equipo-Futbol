package srangeldev.mapper

import srangeldev.dto.PersonalDto
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import java.time.LocalDate

//toDto

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

//toModel

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