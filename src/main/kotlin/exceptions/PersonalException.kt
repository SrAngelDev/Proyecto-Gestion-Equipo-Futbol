package srangeldev.exceptions


/**
 * Excepciones de Equipo, jugadores y entrenador
 * @param message Mensaje que salta cuando hay una excepcion
 */
sealed class PersonalException(message: String) : Exception(message) {
    class PersonalNotFoundException(id: String) : PersonalException("Persona no encontrada con id: $id")
    class SalarioNotFoundException(message: String) : PersonalException("el salario no puede ser menor o igual a 0")
    class PaisNotFoundException(message: String) : PersonalException("el pais no puede ser nulo ni en blanco")
    class PersonalStorageException(message: String) : PersonalException("Error en el almacenamiento: $message")
}
