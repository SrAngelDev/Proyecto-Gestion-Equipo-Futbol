package srangeldev.storage

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import srangeldev.exceptions.PersonalException
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.io.File
import java.time.LocalDate

class PersonalStorageCsvTest {

 private val storage = PersonalStorageCsv()

 @Test
 @DisplayName("Debe lanzar PersonalStorageException cuando el fichero no es válido para leer")
 fun testReadFromFileInvalidFile() {
  val invalidFile = File("invalid_file.csv")
  val exception = assertThrows<PersonalException.PersonalStorageException> {
   storage.readFromFile(invalidFile)
  }
  assertEquals("Error en el almacenamiento: El fichero no existe, o no es un fichero o no se puede leer: invalid_file.csv", exception.message)
 }

 @Test
 @DisplayName("Debe lanzar PersonalStorageException cuando el tipo de personal es desconocido")
 fun testReadFromFileUnknownPersonalType() {
  val file = File.createTempFile("personal", ".csv")
  file.writeText("tipo,id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,paisOrigen,especializacion,posicion,dorsal,altura,peso,goles,partidosJugados\n")
  file.appendText("Desconocido,1,Juan,Perez,2000-01-01,2022-01-01,50000,España,ENTRENADOR_PRINCIPAL,,,\n")

  val exception = assertThrows<PersonalException.PersonalStorageException> {
   storage.readFromFile(file)
  }
  assertEquals("Error en el almacenamiento: Tipo de personal desconocido: Desconocido", exception.message)
 }

 @Test
 @DisplayName("Debe leer correctamente un fichero CSV de entrenadores")
 fun testReadFromFileValidEntrenador() {
  val file = File.createTempFile("personal", ".csv")
  file.writeText("tipo,id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,paisOrigen,especializacion,posicion,dorsal,altura,peso,goles,partidosJugados\n")
  file.appendText("Entrenador,1,Juan,Perez,2000-01-01,2022-01-01,50000,España,ENTRENADOR_PRINCIPAL,,,,\n")

  val personalList = storage.readFromFile(file)
  assertEquals(1, personalList.size)
  val entrenador = personalList[0] as Entrenador
  assertEquals("Juan", entrenador.nombre)
  assertEquals("Perez", entrenador.apellidos)
  assertEquals(LocalDate.of(2000, 1, 1), entrenador.fechaNacimiento)
  assertEquals(LocalDate.of(2022, 1, 1), entrenador.fechaIncorporacion)
  assertEquals(50000.0, entrenador.salario)
  assertEquals("España", entrenador.paisOrigen)
  assertEquals(Entrenador.Especializacion.ENTRENADOR_PRINCIPAL, entrenador.especializacion)
 }

 @Test
 @DisplayName("Debe leer correctamente un fichero CSV de jugadores")
 fun testReadFromFileValidJugador() {
  val file = File.createTempFile("personal", ".csv")
  file.writeText("tipo,id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,paisOrigen,especializacion,posicion,dorsal,altura,peso,goles,partidosJugados\n")
  file.appendText("Jugador,2,Pablo,Gonzalez,1998-05-15,2021-06-01,60000,España,,DEFENSA,5,1.85,75.0,10,50\n")

  val personalList = storage.readFromFile(file)
  assertEquals(1, personalList.size)
  val jugador = personalList[0] as Jugador
  assertEquals("Pablo", jugador.nombre)
  assertEquals("Gonzalez", jugador.apellidos)
  assertEquals(LocalDate.of(1998, 5, 15), jugador.fechaNacimiento)
  assertEquals(LocalDate.of(2021, 6, 1), jugador.fechaIncorporacion)
  assertEquals(60000.0, jugador.salario)
  assertEquals("España", jugador.paisOrigen)
  assertEquals(Jugador.Posicion.DEFENSA, jugador.posicion)
  assertEquals(5, jugador.dorsal)
  assertEquals(1.85, jugador.altura)
  assertEquals(75.0, jugador.peso)
  assertEquals(10, jugador.goles)
  assertEquals(50, jugador.partidosJugados)
 }

 @Test
 @DisplayName("Debe lanzar PersonalStorageException cuando el directorio padre no existe")
 fun testWriteToFileInvalidDirectory() {
  val invalidFile = File("/invalid_directory/personal.csv")
  val personalList = listOf<Personal>()
  val exception = assertThrows<PersonalException.PersonalStorageException> {
   storage.writeToFile(invalidFile, personalList)
  }
  assertEquals("Error en el almacenamiento: El directorio padre del fichero no existe o no es un directorio o el fichero no tiene extensión CSV: C:\\invalid_directory", exception.message)
 }

 @Test
 @DisplayName("Debe escribir correctamente una lista de personal en un fichero CSV")
 fun testWriteToFileValidData() {
  val file = File.createTempFile("personal", ".csv")
  val personalList = listOf(
   Entrenador(1, "Juan", "Perez", LocalDate.of(2000, 1, 1), LocalDate.of(2022, 1, 1), 50000.0, "España", Entrenador.Especializacion.ENTRENADOR_PRINCIPAL),
   Jugador(2, "Pablo", "Gonzalez", LocalDate.of(1998, 5, 15), LocalDate.of(2021, 6, 1), 60000.0, "España", Jugador.Posicion.DEFENSA, 5, 1.85, 75.0, 10, 50)
  )

  storage.writeToFile(file, personalList)

  val lines = file.readLines()
  assertEquals(3, lines.size)
  assertEquals("tipo,id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,paisOrigen,especializacion,posicion,dorsal,altura,peso,goles,partidosJugados", lines[0])
  assertEquals("Entrenador,1,Juan,Perez,2000-01-01,2022-01-01,50000.0,España,ENTRENADOR_PRINCIPAL,,,,,", lines[1])
  assertEquals("Jugador,2,Pablo,Gonzalez,1998-05-15,2021-06-01,60000.0,España,,DEFENSA,5,1.85,75.0,10,50", lines[2])
 }
}
