package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

/**
 * Data class que representa un objeto de transferencia de datos (DTO) para el personal.
 *
 * @param id El identificador único del personal.
 * @param nombre El nombre del personal.
 * @param apellidos Los apellidos del personal.
 * @param fechaNacimiento La fecha de nacimiento del personal.
 * @param fechaIncorporacion La fecha de incorporación del personal.
 * @param salario El salario del personal.
 * @param paisOrigen El país de origen del personal.
 * @param rol El rol del personal.
 * @param especializacion La especialización del personal.
 * @param tipo El tipo de personal.
 * @param posicion La posición del personal (solo para jugadores).
 * @param dorsal El dorsal del personal (solo para jugadores).
 * @param altura La altura del personal (solo para jugadores).
 * @param peso El peso del personal (solo para jugadores).
 * @param goles Los goles del personal (solo para jugadores).
 * @param partidosJugados Los partidos jugados por el personal (solo para jugadores).
 */
@Serializable
@SerialName("personal")
data class PersonalDto(
    @SerialName("id")
    val id: Int = 0,
    @XmlElement
    @SerialName("nombre")
    val nombre: String = "",
    @XmlElement
    @SerialName("apellidos")
    val apellidos: String = "",
    @XmlElement
    @SerialName("fechaNacimiento")
    val fechaNacimiento: String = "",
    @XmlElement
    @SerialName("fechaIncorporacion")
    val fechaIncorporacion: String = "",
    @XmlElement
    @SerialName("salario")
    val salario: Double = 0.0,
    @XmlElement
    @SerialName("pais")
    val paisOrigen: String = "",
    @XmlElement
    @SerialName("rol")
    val rol: String = "",
    @XmlElement
    @SerialName("especialidad")
    val especializacion: String = "",
    @XmlElement
    @SerialName("tipo")
    val tipo: String = "",
    @XmlElement
    @SerialName("posicion")
    val posicion: String? = null,
    @XmlElement
    @SerialName("dorsal")
    val dorsal: Int? = null,
    @XmlElement
    @SerialName("altura")
    val altura: Double? = null,
    @XmlElement
    @SerialName("peso")
    val peso: Double? = null,
    @XmlElement
    @SerialName("goles")
    val goles: Int? = null,
    @XmlElement
    @SerialName("partidosJugados")
    val partidosJugados: Int? = null,
)