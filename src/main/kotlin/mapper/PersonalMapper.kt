package srangeldev.mapper

import srangeldev.dto.PersonalDto
import srangeldev.models.Jugador
import srangeldev.models.Personal

fun Personal.toDto(): PersonalDto{
    return PersonalDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        rol = if (PersonalDto is Jugador) "Jugador" else "Entrenador",
        especializacion =  this.especializacion,
        dorsal = this.dorsal,
    )
}