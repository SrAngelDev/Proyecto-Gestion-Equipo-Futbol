package srangeldev.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("equipo")
data class PersonalsDto(
    val equipo: List<PersonalDto>
)