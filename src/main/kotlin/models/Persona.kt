package srangeldev.models

import java.time.LocalDate

abstract class Persona(
    val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: LocalDate,
    val fechaIncorporacion: LocalDate,
    val salario: Double,
    val paisOrigen: String
)