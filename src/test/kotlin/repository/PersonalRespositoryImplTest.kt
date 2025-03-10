package repository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import srangeldev.models.Jugador
import srangeldev.models.Entrenador
import srangeldev.repository.PersonalRespositoryImpl
import java.time.LocalDate

class PersonalRepositoryImplTest {

 private lateinit var personalRepository: PersonalRespositoryImpl

 @BeforeEach
 fun setUp() {
  personalRepository = PersonalRespositoryImpl()
  // Vaciar los datos de prueba que se agregan en la inicialización
  personalRepository.delete(1)
  personalRepository.delete(2)
 }

 @Test
 fun `test getAll returns empty list when no personal exist`() {
  val personal = personalRepository.getAll()
  assertTrue(personal.isEmpty(), "Debería devolver una lista vacía")
 }


 @Test
 fun `test getById returns null when ID does not exist`() {
  val personal = personalRepository.getById(99)
  assertNull(personal, "Debería devolver nulo cuando el ID no existe")
 }


 @Test
 fun testSaveJugador() {
  val jugador = Jugador(
   id = 0,
   nombre = "Cristiano Ronaldo",
   apellidos = "Ronaldo",
   fechaNacimiento = LocalDate.of(1985, 2, 5),
   fechaIncorporacion = LocalDate.of(2021, 8, 31),
   salario = 4500000.0,
   paisOrigen = "Portugal",
   posicion = Jugador.Posicion.DELANTERO,
   dorsal = 7,
   altura = 187.0,
   peso = 83.0,
   goles = 700,
   partidosJugados = 900
  )
  val jugadorGuardado = personalRepository.save(jugador)
  assertNotNull(jugadorGuardado, "Jugador no guardado")
  assertTrue(jugadorGuardado.id > 0, "ID del jugador guardado debe ser mayor que 0")

  val jugadorObtenido = personalRepository.getById(jugadorGuardado.id)
  assertNotNull(jugadorObtenido, "Jugador no encontrado")
  assertEquals(jugadorGuardado, jugadorObtenido, "Jugador no coincide")
 }


 @Test
 fun testSaveEntrenador() {
  val entrenador = Entrenador(
   id = 0,
   nombre = "Zinedine Zidane",
   apellidos = "Zidane",
   fechaNacimiento = LocalDate.of(1972, 6, 23),
   fechaIncorporacion = LocalDate.of(2020, 7, 10),
   salario = 12000000.0,
   paisOrigen = "Francia",
   especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
  )
  val entrenadorGuardado = personalRepository.save(entrenador)
  assertNotNull(entrenadorGuardado, "Entrenador no guardado")
  assertTrue(entrenadorGuardado.id > 0, "ID del entrenador guardado debe ser mayor que 0")

  val entrenadorObtenido = personalRepository.getById(entrenadorGuardado.id)
  assertNotNull(entrenadorObtenido, "Entrenador no encontrado")
  assertEquals(entrenadorGuardado, entrenadorObtenido, "Entrenador no coincide")
 }

 @Test
 fun testUpdateJugador() {
  val jugador = Jugador(
   id = 1,
   nombre = "Lionel Messi",
   apellidos = "Messi",
   fechaNacimiento = LocalDate.of(1987, 6, 24),
   fechaIncorporacion = LocalDate.of(2021, 8, 10),
   salario = 5000000.0,
   paisOrigen = "Argentina",
   posicion = Jugador.Posicion.DELANTERO,
   dorsal = 10,
   altura = 170.0,
   peso = 72.0,
   goles = 750,
   partidosJugados = 800
  )
  personalRepository.save(jugador)

  val jugadorActualizado = Jugador(
   id = jugador.id,
   nombre = "Lionel Messi Actualizado",
   apellidos = jugador.apellidos,
   fechaNacimiento = jugador.fechaNacimiento,
   fechaIncorporacion = jugador.fechaIncorporacion,
   salario = 6000000.0,
   paisOrigen = jugador.paisOrigen,
   posicion = jugador.posicion,
   dorsal = jugador.dorsal,
   altura = jugador.altura,
   peso = jugador.peso,
   goles = jugador.goles + 10,
   partidosJugados = jugador.partidosJugados + 5
  )

  val resultado = personalRepository.update(jugador.id, jugadorActualizado)
  assertNotNull(resultado, "Jugador no encontrado")
  assertEquals("Lionel Messi Actualizado", resultado?.nombre, "El nombre no coincide")
  assertEquals(6000000.0, resultado?.salario, "El salario no coincide")
 }

 @Test
 fun testUpdateEntrenador() {
  val entrenador = Entrenador(
   id = 2,
   nombre = "Pep Guardiola",
   apellidos = "Guardiola",
   fechaNacimiento = LocalDate.of(1971, 1, 18),
   fechaIncorporacion = LocalDate.of(2016, 7, 1),
   salario = 15000000.0,
   paisOrigen = "España",
   especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
  )
  personalRepository.save(entrenador)

  // Verificar que el entrenador se guardó correctamente
  val savedEntrenador = personalRepository.getById(entrenador.id)
  assertNotNull(savedEntrenador, "Entrenador no guardado correctamente")
  assertEquals(entrenador.id, savedEntrenador?.id, "El ID del entrenador guardado no coincide")

  val entrenadorActualizado = Entrenador(
   id = entrenador.id,
   nombre = "Pep Guardiola Actualizado",
   apellidos = entrenador.apellidos,
   fechaNacimiento = entrenador.fechaNacimiento,
   fechaIncorporacion = entrenador.fechaIncorporacion,
   salario = 18000000.0,
   paisOrigen = entrenador.paisOrigen,
   especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
  )

  val resultado = personalRepository.update(entrenador.id, entrenadorActualizado)
  assertNotNull(resultado, "Entrenador no encontrado")
  assertEquals("Pep Guardiola Actualizado", resultado?.nombre, "El nombre no coincide")
  assertEquals(18000000.0, resultado?.salario, "El salario no coincide")

  // Verificar que la actualización se guardó correctamente
  val updatedEntrenador = personalRepository.getById(entrenador.id)
  assertNotNull(updatedEntrenador, "Entrenador actualizado no encontrado")
  assertEquals("Pep Guardiola Actualizado", updatedEntrenador?.nombre, "El nombre actualizado no coincide")
  assertEquals(18000000.0, updatedEntrenador?.salario, "El salario actualizado no coincide")
 }



 @Test
 fun testDeleteJugador() {
  val jugador = Jugador(
   id = 1,
   nombre = "Lionel Messi",
   apellidos = "Messi",
   fechaNacimiento = LocalDate.of(1987, 6, 24),
   fechaIncorporacion = LocalDate.of(2021, 8, 10),
   salario = 5000000.0,
   paisOrigen = "Argentina",
   posicion = Jugador.Posicion.DELANTERO,
   dorsal = 10,
   altura = 170.0,
   peso = 72.0,
   goles = 750,
   partidosJugados = 800
  )
  personalRepository.save(jugador)
  val jugadorEliminado = personalRepository.delete(jugador.id)
  assertNotNull(jugadorEliminado, "Jugador no encontrado")
  val jugadorObtenido = personalRepository.getById(jugador.id)
  assertNull(jugadorObtenido, "Jugador no eliminado")
 }

 @Test
 fun testDeleteEntrenador() {
  val entrenador = Entrenador(
   id = 2,
   nombre = "Pep Guardiola",
   apellidos = "Guardiola",
   fechaNacimiento = LocalDate.of(1971, 1, 18),
   fechaIncorporacion = LocalDate.of(2016, 7, 1),
   salario = 15000000.0,
   paisOrigen = "España",
   especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
  )
  personalRepository.save(entrenador)
  val entrenadorEliminado = personalRepository.delete(entrenador.id)
  assertNotNull(entrenadorEliminado, "Entrenador no encontrado")
  val entrenadorObtenido = personalRepository.getById(entrenador.id)
  assertNull(entrenadorObtenido, "Entrenador no eliminado")
 }
}
