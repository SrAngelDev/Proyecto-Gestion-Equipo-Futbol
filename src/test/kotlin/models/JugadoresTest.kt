package models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import kotlin.test.assertEquals
import java.time.LocalDate
import srangeldev.models.Jugadores


class JugadoresTest {

    @Test
    @DisplayName("Posicion del jugador válida")
    fun getPosicion() {
        val jugador = crearJugador()
        assertEquals(Jugadores.Posicion.DELANTERO, jugador.posicion)
    }

    @Test
    @DisplayName("Dorsal del jugador válido")
    fun getDorsal() {
        val jugador = crearJugador()
        assertEquals(9, jugador.dorsal)
    }

    @Test
    @DisplayName("Altura del jugador válida")
    fun getAltura() {
        val jugador = crearJugador()
        assertEquals(1.85, jugador.altura)
    }

    @Test
    @DisplayName("Peso del jugador válido")
    fun getPeso() {
        val jugador = crearJugador()
        assertEquals(75.0, jugador.peso)
    }

    @Test
    @DisplayName("Goles del jugador válidos")
    fun getGoles() {
        val jugador = crearJugador()
        assertEquals(20, jugador.goles)
    }

    @Test
    @DisplayName("Partidos jugados del jugador válidos")
    fun getPartidosJugados() {
        val jugador = crearJugador()
        assertEquals(30, jugador.partidosJugados)
    }

    private fun crearJugador(): Jugadores {
        val fechaNacimiento = LocalDate.of(1990, 6, 21)
        val fechaIncorporacion = LocalDate.of(2015, 8, 10)
        return Jugadores(
            id = 1,
            nombre = "Pedro",
            apellidos = "González",
            fechaNacimiento = fechaNacimiento,
            fechaIncorporacion = fechaIncorporacion,
            salario = 45000.0,
            paisOrigen = "España",
            posicion = Jugadores.Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.85,
            peso = 75.0,
            goles = 20,
            partidosJugados = 30
        )
    }
}
