package srangeldev.validator

import srangeldev.exceptions.PersonalException
import srangeldev.models.Personal

fun Personal.validate(){
    if (id < 0) {
        throw PersonalException.PersonalNotFoundException("Persona no encontrado con id: $id")
    }
    if (nombre.isEmpty() || apellidos.isEmpty()) {
        throw  PersonalException.PersonalNotFoundException("Persona no encontrado con nombre: $nombre, apellidos: $apellidos")
    }
    if (salario > 0){
        throw PersonalException.SalarioNotFoundException("Salario no puede ser menor o igual a 0")
    }
    if (paisOrigen.isEmpty() || paisOrigen.isBlank()) {
        throw PersonalException.PaisNotFoundException("Pais no puede ser nulo o en blanco")
    }
}