package srangeldev.exceptions

sealed class EntrenadorException(message: String) : Exception(message) {
    class EntrenadorEspecialidadException(message: String) : EntrenadorException("Entrenador necesita una especialidad")
}