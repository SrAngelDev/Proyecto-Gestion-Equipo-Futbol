package srangeldev.mapper

import srangeldev.dto.*
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import java.time.LocalDate
import java.time.LocalDateTime

fun Entrenador.toCsvDto(): PersonalCsvDto {
    return PersonalCsvDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        rol = "Entrenador",
        especializacion = this.especializacion.toString()
    )
}

fun Jugador.toCsvDto(): PersonalCsvDto {
    return PersonalCsvDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        rol = "Jugador",
        posicion = this.posicion.toString(),
        dorsal = this.dorsal.toString(),
        altura = this.altura.toString(),
        peso = this.peso.toString(),
        goles = this.goles.toString(),
        partidosJugados = this.partidosJugados.toString()
    )
}

fun Entrenador.toJsonDto(): PersonalJsonDto {
    return PersonalJsonDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        rol = "Entrenador",
        especializacion = this.especializacion.toString(),
    )
}

fun Jugador.toJsonDto(): PersonalJsonDto {
    return PersonalJsonDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        rol = "Jugador",
        posicion = this.posicion.toString(),
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados,
    )
}

fun Entrenador.toXmlDto(): PersonalXmlDto {
    return PersonalXmlDto(
        id = this.id,
        tipo = "Entrenador",
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        especialidad = this.especializacion.toString()
    )
}

fun Jugador.toXmlDto(): PersonalXmlDto {
    return PersonalXmlDto(
        id = this.id,
        tipo = "Jugador",
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        posicion = this.posicion.toString(),
        dorsal = this.dorsal.toString(),
        altura = this.altura.toString(),
        peso = this.peso.toString(),
        goles = this.goles.toString(),
        partidosJugados = this.partidosJugados.toString()
    )
}

fun PersonalCsvDto.toEntrenador(): Entrenador {
    val especializacion = if (this.especializacion.isNullOrEmpty()) {
        Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
    } else {
        try {
            Entrenador.Especializacion.valueOf(this.especializacion.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Especialización no válida: ${this.especializacion}")
        }
    }

    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        especializacion = especializacion
    )
}

fun PersonalCsvDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.paisOrigen,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal.toInt(),
        altura = this.altura.toDouble(),
        peso = this.peso.toDouble(),
        goles = this.goles.toInt(),
        partidosJugados = this.partidosJugados.toInt()
    )
}

fun PersonalJsonDto.toEntrenador(): Entrenador {
    val especializacion = if (this.especializacion.isNullOrEmpty()) {
        Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
    } else {
        try {
            Entrenador.Especializacion.valueOf(this.especializacion.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Especialización no válida: ${this.especializacion}")
        }
    }

    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.pais,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        especializacion = especializacion
    )
}

fun PersonalJsonDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.pais,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal?.toInt() ?: 0,
        altura = this.altura?.toDouble() ?: 0.0,
        peso = this.peso?.toDouble() ?: 0.0,
        goles = this.goles?.toInt() ?: 0,
        partidosJugados = this.partidosJugados?.toInt() ?: 0
    )
}

fun PersonalXmlDto.toEntrenador(): Entrenador {
    val especializacion = if (this.especialidad.isNullOrEmpty()) {
        Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
    } else {
        try {
            Entrenador.Especializacion.valueOf(this.especialidad.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Especialización no válida: ${this.especialidad}")
        }
    }

    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.pais,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        especializacion = especializacion
    )
}

fun PersonalXmlDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.pais,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal.toInt(),
        altura = this.altura.toDouble(),
        peso = this.peso.toDouble(),
        goles = this.goles.toInt(),
        partidosJugados = this.partidosJugados.toInt()
    )
}

fun Entrenador.toBinDto(): PersonalBinDto {
    return PersonalBinDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        rol = "Entrenador",
        especialidad = this.especializacion.toString()
    )
}

fun Jugador.toBinDto(): PersonalBinDto {
    return PersonalBinDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        rol = "Jugador",
        posicion = this.posicion.toString(),
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados
    )
}

fun PersonalBinDto.toEntrenador(): Entrenador {
    val especializacion = if (this.especialidad.isNullOrEmpty()) {
        Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
    } else {
        try {
            Entrenador.Especializacion.valueOf(this.especialidad.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Especialización no válida: ${this.especialidad}")
        }
    }

    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.pais,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        especializacion = especializacion
    )
}

fun PersonalBinDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        fechaIncorporacion = LocalDate.parse(this.fechaIncorporacion),
        salario = this.salario,
        paisOrigen = this.pais,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0
    )
}