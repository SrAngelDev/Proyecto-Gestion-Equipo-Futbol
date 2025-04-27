package srangeldev.storage

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import srangeldev.exceptions.PersonalException
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import srangeldev.models.Jugador.Posicion
import srangeldev.models.Entrenador.Especializacion
import java.io.File
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonalStorageBinTest {
 private lateinit var storage: PersonalStorageBin
 private lateinit var testFile: File

 @BeforeAll
 fun setUp() {
  storage = PersonalStorageBin()
  testFile = File("test_personal.bin")
 }

 @AfterAll
 fun limpiar() {
  if (testFile.exists()) {
   testFile.delete()
  }
 }

 @Test
 @DisplayName("Test de escritura y lectura de Jugador")
 fun testWriteAndReadJugador() {
  val jugador = Jugador(
   id = 1,
   nombre = "Oliver",
   apellidos = "Atom",
   fechaNacimiento = LocalDate.of(1983, 4, 10),
   fechaIncorporacion = LocalDate.of(2001, 5, 15),
   salario = 35000.0,
   paisOrigen = "España",
   posicion = Posicion.DELANTERO,
   dorsal = 10,
   altura = 1.75,
   peso = 65.0,
   goles = 70,
   partidosJugados = 150
  )

  storage.writeToFile(testFile, listOf(jugador))
  val readPersonal = storage.readFromFile(testFile)

  assertEquals(1, readPersonal.size)
  assertTrue(readPersonal[0] is Jugador)
  val readJugador = readPersonal[0] as Jugador
  assertEquals(jugador.id, readJugador.id)
  assertEquals(jugador.nombre, readJugador.nombre)
  assertEquals(jugador.apellidos, readJugador.apellidos)
  assertEquals(jugador.fechaNacimiento, readJugador.fechaNacimiento)
  assertEquals(jugador.fechaIncorporacion, readJugador.fechaIncorporacion)
  assertEquals(jugador.salario, readJugador.salario)
  assertEquals(jugador.paisOrigen, readJugador.paisOrigen)
  assertEquals(jugador.posicion, readJugador.posicion)
  assertEquals(jugador.dorsal, readJugador.dorsal)
  assertEquals(jugador.altura, readJugador.altura)
  assertEquals(jugador.peso, readJugador.peso)
  assertEquals(jugador.goles, readJugador.goles)
  assertEquals(jugador.partidosJugados, readJugador.partidosJugados)
 }

 @Test
 @DisplayName("Test de escritura y lectura de Entrenador")
 fun testWriteAndReadEntrenador() {
  val entrenador = Entrenador(
   id = 2,
   nombre = "Roberto",
   apellidos = "Sánchez",
   fechaNacimiento = LocalDate.of(1975, 2, 10),
   fechaIncorporacion = LocalDate.of(2022, 5, 15),
   salario = 60000.0,
   paisOrigen = "Uruguay",
   especializacion = Especializacion.ENTRENADOR_PORTEROS
  )

  storage.writeToFile(testFile, listOf(entrenador))
  val readPersonal = storage.readFromFile(testFile)

  assertEquals(1, readPersonal.size)
  assertTrue(readPersonal[0] is Entrenador)
  val readEntrenador = readPersonal[0] as Entrenador
  assertEquals(entrenador.id, readEntrenador.id)
  assertEquals(entrenador.nombre, readEntrenador.nombre)
  assertEquals(entrenador.apellidos, readEntrenador.apellidos)
  assertEquals(entrenador.fechaNacimiento, readEntrenador.fechaNacimiento)
  assertEquals(entrenador.fechaIncorporacion, readEntrenador.fechaIncorporacion)
  assertEquals(entrenador.salario, readEntrenador.salario)
  assertEquals(entrenador.paisOrigen, readEntrenador.paisOrigen)
  assertEquals(entrenador.especializacion, readEntrenador.especializacion)
 }

 @Test
 @DisplayName("Test de lectura desde un archivo que no existe lanza excepción")
 fun testReadFromFileWithNonExistingFileThrowsException() {
  val nonExistingFile = File("non_existing_file.bin")
  val exception = assertThrows<PersonalException.PersonalStorageException> {
   storage.readFromFile(nonExistingFile)
  }
  assertEquals("El fichero no existe, o no es un fichero o no se puede leer: $nonExistingFile", exception.message)
 }

 @Test
 @DisplayName("Test de escritura en un directorio padre que no existe lanza excepción")
 fun testWriteToFileWithNonExistingParentDirectoryThrowsException() {
  val invalidFile = File("non_existing_directory/test_personal.bin")
  val jugador = Jugador(
   id = 1,
   nombre = "Oliver",
   apellidos = "Atom",
   fechaNacimiento = LocalDate.of(1983, 4, 10),
   fechaIncorporacion = LocalDate.of(2001, 5, 15),
   salario = 35000.0,
   paisOrigen = "España",
   posicion = Posicion.DELANTERO,
   dorsal = 10,
   altura = 1.75,
   peso = 65.0,
   goles = 70,
   partidosJugados = 150
  )

  val exception = assertThrows<PersonalException.PersonalStorageException> {
   storage.writeToFile(invalidFile, listOf(jugador))
  }
  assertEquals("Error en el almacenamiento: El directorio padre del fichero no existe: non_existing_directory", exception.message)
 }
}
