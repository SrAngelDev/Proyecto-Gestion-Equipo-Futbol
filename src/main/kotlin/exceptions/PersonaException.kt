package srangeldev.exceptions

/**
 * Excepciones de Equipo, jugadores y entrenador
 * @param message Mensaje que salta cuando hay una excepcion
 */
sealed class PersonaException(message: String) : Exception(message) {
    class PersonaNotFoundException(id: String) : PersonaException("Persona no encontrada con id: $id")
    class SalarioNotFoundException(message: String) : PersonaException("el salario no puede ser menor o igual a 0")
    class PaisNotFoundException(message: String) : PersonaException("el pais no puede ser nulo ni en blanco")
}
