package mapper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import srangeldev.dto.PersonalCsvDto
import srangeldev.mapper.toEntrenador
import srangeldev.mapper.toJugador
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import java.time.LocalDate

class PersonalMapperKtTest {
    @Test
    @DisplayName("Test Personal toEntrenador")
    fun toEntrenador() {
        val personalCsvDto = PersonalCsvDto(
            id = 1,
            nombre = "Angel",
            apellidos = "Sanchez",
            fechaNacimiento = "1980-01-01",
            fechaIncorporacion = "2020-01-01",
            salario = 50000.0,
            paisOrigen = "España",
            especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL.toString()
        )

        val entrenador = personalCsvDto.toEntrenador()

        assertEquals(1, entrenador.id)
        assertEquals("Angel", entrenador.nombre)
        assertEquals("Sanchez", entrenador.apellidos)
        assertEquals(LocalDate.of(1980, 1, 1), entrenador.fechaNacimiento)
        assertEquals(LocalDate.of(2020, 1, 1), entrenador.fechaIncorporacion)
        assertEquals(50000.0, entrenador.salario)
        assertEquals("España", entrenador.paisOrigen)
        assertEquals(Entrenador.Especializacion.ENTRENADOR_PRINCIPAL, entrenador.especializacion)
    }
    @Test
    @DisplayName("Test Personal toJugador")
    fun personalToJugador() {
        val personalCsvDto = PersonalCsvDto(
            id = 2,
            nombre = "Angel",
            apellidos = "Sanchez",
            fechaNacimiento = "1990-02-02",
            fechaIncorporacion = "2021-02-02",
            salario = 60000.0,
            paisOrigen = "España",
            posicion = Jugador.Posicion.DELANTERO.toString(),
            dorsal = 10,
            altura = 1.75,
            peso = 65.0,
            goles = 30,
            partidosJugados = 100
        )

        val jugador = personalCsvDto.toJugador()

        assertEquals(2, jugador.id)
        assertEquals("Angel", jugador.nombre)
        assertEquals("Sanchez", jugador.apellidos)
        assertEquals(LocalDate.of(1990, 2, 2), jugador.fechaNacimiento)
        assertEquals(LocalDate.of(2021, 2, 2), jugador.fechaIncorporacion)
        assertEquals(60000.0, jugador.salario)
        assertEquals("España", jugador.paisOrigen)
        assertEquals(Jugador.Posicion.DELANTERO, jugador.posicion)
        assertEquals(10, jugador.dorsal)
        assertEquals(1.75, jugador.altura)
        assertEquals(65.0, jugador.peso)
        assertEquals(30, jugador.goles)
        assertEquals(100, jugador.partidosJugados)
    }
}

