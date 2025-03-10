package srangeldev.mapper

import srangeldev.dto.*
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.time.LocalDate

fun Entrenador.toCsvDto(): PersonalCsvDto {
    return PersonalCsvDto(
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

fun Jugador.toCsvDto(): PersonalCsvDto {
    return PersonalCsvDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        rol = "Jugador",
        posicion = this.posicion.toString(),
        dorsal = this.dorsal.toString(),
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados
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
        especialidad = this.especializacion.toString(),
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

fun Jugador.toXmlDto(): PersonalXmlDto {
    return PersonalXmlDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento.toString(),
        fechaIncorporacion = this.fechaIncorporacion.toString(),
        salario = this.salario,
        pais = this.paisOrigen,
        rol = "Jugador",
        posicion = this.posicion.toString(),
        dorsal = this.dorsal.toString(),
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados
    )
}

fun PersonalCsvDto.toEntrenador(): Entrenador {
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
        paisOrigen = this.pais,
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal?.toInt() ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0
    )
}

fun PersonalJsonDto.toEntrenador(): Entrenador {
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
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal?.toInt() ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0
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
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal?.toInt() ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0
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
        posicion = Jugador.Posicion.valueOf(this.posicion ?: "DESCONOCIDO"),
        dorsal = this.dorsal ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0
    )
}