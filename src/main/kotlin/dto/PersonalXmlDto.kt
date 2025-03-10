package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

@Serializable
data class PersonalXmlDto(
    @SerialName("id")
    @XmlElement(true)
    val id: Int,
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
    val pais: String,
    @SerialName("rol")
    @XmlElement(true)
    val rol: String? = null,
    @SerialName("especialidad")
    @XmlElement(true)
    val especialidad: String? = null,
    @SerialName("posicion")
    @XmlElement(true)
    val posicion: String? = null,
    @SerialName("dorsal")
    @XmlElement(true)
    val dorsal: String? = null,
    @SerialName("altura")
    @XmlElement(true)
    val altura: Double? = null,
    @SerialName("peso")
    @XmlElement(true)
    val peso: Double? = null,
    @SerialName("goles")
    @XmlElement(true)
    val goles: Int? = null,
    @SerialName("partidosJugados")
    @XmlElement(true)
    val partidosJugados: Int? = null
)