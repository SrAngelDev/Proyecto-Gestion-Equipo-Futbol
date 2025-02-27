package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

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
    @SerialName("fecha_nacimiento")
    val fechaNacimiento: String = "",
    @XmlElement
    @SerialName("fecha_incorporacion")
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
    @SerialName("posicion")
    val posicion: String = "",
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
    @SerialName("partidos_jugados")
    val partidosJugados: Int? = null,
)