package srangeldev.validator

import srangeldev.exceptions.EntrenadorException
import srangeldev.models.Entrenador

fun Entrenador.validate(){
    if (especializacion == null){
        throw EntrenadorException.EntrenadorEspecialidadException("Entrenador necesita una especialidad")
    }
}