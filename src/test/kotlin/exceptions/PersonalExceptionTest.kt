package srangeldev.exceptions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PersonalExceptionTest {

 @Test
 @DisplayName("Prueba de excepción PersonalNotFoundException")
 fun testPersonalNotFoundException() {
  val id = "1234"
  val exception = PersonalException.PersonalNotFoundException(id)

  assertNotNull(exception)
  assertEquals("Persona no encontrada con id: $id", exception.message)
 }

 @Test
 @DisplayName("Prueba de excepción PersonalStorageException")
 fun testPersonalStorageException() {
  val message = "Error al guardar los datos"
  val exception = PersonalException.PersonalStorageException(message)

  assertNotNull(exception)
  assertEquals("Error en el almacenamiento: $message", exception.message)
 }
}