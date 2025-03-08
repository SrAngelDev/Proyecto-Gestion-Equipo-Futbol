package srangeldev.validator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import srangeldev.exceptions.PersonalException
import srangeldev.models.Personal
import java.time.LocalDate

class PersonalValidatorTest {

 class PersonalImpl(
  id: Int,
  nombre: String,
  apellidos: String,
  fechaNacimiento: LocalDate,
  fechaIncorporacion: LocalDate,
  salario: Double,
  paisOrigen: String
 ) : Personal(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, paisOrigen)

 @Test
 @DisplayName("Prueba para validar ID negativo")
 fun testValidateNegativeId() {
  val personal = PersonalImpl(
   id = -1,
   nombre = "angel",
   apellidos = "sanchez",
   fechaNacimiento = LocalDate.of(1990, 1, 1),
   fechaIncorporacion = LocalDate.of(2020, 1, 1),
   salario = 3000.0,
   paisOrigen = "España"
  )

  val exception = assertThrows<PersonalException.PersonalNotFoundException> {
   personal.validate()
  }

  assertEquals("Persona no encontrada con id: -1", exception.message)
 }

 @Test
 @DisplayName("Prueba para validar nombre vacío")
 fun testValidateEmptyNombre() {
  val personal = PersonalImpl(
   id = 1,
   nombre = "",
   apellidos = "sanchez",
   fechaNacimiento = LocalDate.of(1990, 1, 1),
   fechaIncorporacion = LocalDate.of(2020, 1, 1),
   salario = 3000.0,
   paisOrigen = "España"
  )

  val exception = assertThrows<PersonalException.PersonalStorageException> {
   personal.validate()
  }

  assertEquals("Error en el almacenamiento: Persona no encontrada con nombre: , apellidos: sanchez", exception.message)
 }

 @Test
 @DisplayName("Prueba para validar apellidos vacío")
 fun testValidateEmptyApellidos() {
  val personal = PersonalImpl(
   id = 1,
   nombre = "angel",
   apellidos = "",
   fechaNacimiento = LocalDate.of(1990, 1, 1),
   fechaIncorporacion = LocalDate.of(2020, 1, 1),
   salario = 3000.0,
   paisOrigen = "España"
  )

  val exception = assertThrows<PersonalException.PersonalStorageException> {
   personal.validate()
  }

  assertEquals("Error en el almacenamiento: Persona no encontrada con nombre: angel, apellidos: ", exception.message)
 }
}
