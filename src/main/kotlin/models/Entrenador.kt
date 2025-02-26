package srangeldev.models

class Entrenador(
    id: Int,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double,
    paisOrigen: String,
    val especializacion: Especializacion
): Persona(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, paisOrigen) {
    enum class Especializacion {

    }
}