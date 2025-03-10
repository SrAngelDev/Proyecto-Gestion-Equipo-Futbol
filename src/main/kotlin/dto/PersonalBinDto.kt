package srangeldev.dto

data class PersonalBinDto(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String,
    val rol: String,
    val especialidad: String? = null,
    val posicion: String? = null,
    val dorsal: Int? = null,
    val altura: Double? = null,
    val peso: Double? = null,
    val goles: Int? = null,
    val partidosJugados: Int? = null
)