package srangeldev.models

class Entrenador(
    id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    paisOrigen: String
): Persona(id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, paisOrigen)