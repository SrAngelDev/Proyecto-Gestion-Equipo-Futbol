package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class que representa un equipo de personal.
 *
 * @param equipo La lista de objetos PersonalDto que componen el equipo.
 */
@Serializable
@SerialName("equipo")
data class PersonalsDto(
    val equipo: List<PersonalDto>
)