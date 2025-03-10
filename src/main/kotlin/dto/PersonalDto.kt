package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlOtherAttributes

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

    @SerialName("nombre")
    @XmlElement(true)
    val nombre: String,

    @SerialName("apellidos")
    @XmlElement(true)
    val apellidos: String,

    @SerialName("fechaNacimiento")
    @XmlElement(true)
    val fechaNacimiento: String,

    @SerialName("fechaIncorporacion")
    @XmlElement(true)
    val fechaIncorporacion: String,

    @SerialName("salario")
    @XmlElement(true)
    val salario: Double,

    @SerialName("pais")
    @XmlElement(true)
    val paisOrigen: String,

    @SerialName("rol")
    @XmlElement(true)
    val rol: String? = null,

    @SerialName("especialidad")
    @XmlElement(true)
    val especializacion: String? = null,

    @SerialName("tipo")
    @XmlElement(true)
    val tipo: String? = null,

    @SerialName("posicion")
    @XmlElement(true)
    val posicion: String? = null,

    @SerialName("dorsal")
    @XmlElement(true)
    val dorsal: String? = null,

    @SerialName("altura")
    @XmlElement(true)
    val altura: String? = null,

    @SerialName("peso")
    @XmlElement(true)
    val peso: String? = null,

    @SerialName("goles")
    @XmlElement(true)
    val goles: String? = null,

    @SerialName("partidosJugados")
    @XmlElement(true)
    val partidosJugados: String? = null,
)