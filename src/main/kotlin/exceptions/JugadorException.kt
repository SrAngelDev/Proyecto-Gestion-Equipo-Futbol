package srangeldev.exceptions

 sealed class JugadorException(message: String) : Exception(message) {
     class JugadorPosicionException(message: String) : JugadorException("la posicion no debe ser nula")
     class JugadorDorsalException(message: String) : JugadorException("el dorsal debe ser mayor que 0")
     class JugadorAlturaException(message: String) : JugadorException("la altura debe ser mayor que 1.50m")
     class JugadorPesoException(message: String) : JugadorException("el peso debe ser un valor positivo y mas de 40kg")
     class JugadorGolesException(message: String) : JugadorException("los goles deben ser un valor positivo")
     class JugadorPartidosException(message: String) : JugadorException("los partidos jugados debe ser un valor positivo")

 }