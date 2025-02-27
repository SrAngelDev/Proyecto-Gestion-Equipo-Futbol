package srangeldev.validator

import srangeldev.exceptions.PersonaException
import srangeldev.models.Persona

fun Persona.validate(){
    if (id < 0) {
        throw PersonaException.PersonaNotFoundException("Persona no encontrado con id: $id")
    }
    if (nombre.isEmpty() || apellidos.isEmpty()) {
        throw  PersonaException.PersonaNotFoundException("Persona no encontrado con nombre: $nombre, apellidos: $apellidos")
    }
    if (salario > 0){
        throw PersonaException.SalarioNotFoundException("Salario no puede ser menor o igual a 0")
    }
    if (paisOrigen.isEmpty() || paisOrigen.isBlank()) {
        throw PersonaException.PaisNotFoundException("Pais no puede ser nulo o en blanco")
    }


}