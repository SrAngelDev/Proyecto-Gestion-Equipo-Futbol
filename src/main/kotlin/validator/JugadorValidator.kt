package srangeldev.validator

import srangeldev.exceptions.JugadorException
import srangeldev.models.Jugador

fun Jugador.validate(){
    if (posicion == null){
        throw JugadorException.JugadorPosicionException("La posicion no debe ser nula")
    }
    if (dorsal >= 0){
        throw JugadorException.JugadorDorsalException("El dorsal debe ser un numero positivo")
    }
    if (altura > 1.50){
        throw JugadorException.JugadorAlturaException("La altura debe ser un numero positivo y mayor que 1.50 metros")
    }
    if (peso >= 40){
        throw JugadorException.JugadorPesoException("El peso debe ser un numero positivo y mayor a 40kg")
    }
    if (goles >= 0){
        throw JugadorException.JugadorGolesException("Los goles deben ser un numero positivo")
    }
    if (partidosJugados >= 0){
        throw JugadorException.JugadorPartidosException("Los partidos deben ser un numero positivo")
    }
}