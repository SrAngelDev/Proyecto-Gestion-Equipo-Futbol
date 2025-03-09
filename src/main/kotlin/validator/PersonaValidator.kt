package srangeldev.validator

import srangeldev.exceptions.PersonalException
import srangeldev.models.Personal

fun Personal.validate() {
    if (id < 0) {
        throw PersonalException.PersonalNotFoundException(id)
    }
    if (nombre.isEmpty() || apellidos.isEmpty()) {
        throw PersonalException.PersonalStorageException("Persona no encontrada con nombre: $nombre, apellidos: $apellidos")
    }
}
