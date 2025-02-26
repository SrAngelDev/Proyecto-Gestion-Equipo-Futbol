package srangeldev.models

abstract class Persona(
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val paisOrigen: String
)