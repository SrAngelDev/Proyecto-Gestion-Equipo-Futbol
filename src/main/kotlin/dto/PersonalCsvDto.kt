package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalCsvDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nombre")
    val nombre: String,
    @SerialName("apellidos")
    val apellidos: String,
    @SerialName("fecha_nacimiento")
    val fechaNacimiento: String,
    @SerialName("fecha_incorporacion")
    val fechaIncorporacion: String,
    @SerialName("salario")
    val salario: Double? = 0.0,
    @SerialName("pais")
    val paisOrigen: String,
    @SerialName("rol")
    val rol: String? = null,
    @SerialName("especialidad")
    val especializacion: String? = null,
    @SerialName("posicion")
    val posicion: String? = null,
    @SerialName("dorsal")
    val dorsal: Int? = null,
    @SerialName("altura")
    val altura: Double? = null,
    @SerialName("peso")
    val peso: Double? = null,
    @SerialName("goles")
    val goles: Int? = null,
    @SerialName("partidos_jugados")
    val partidosJugados: Int? = null
)