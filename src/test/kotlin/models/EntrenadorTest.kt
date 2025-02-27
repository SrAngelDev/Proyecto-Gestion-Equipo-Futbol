package models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import srangeldev.models.Entrenador
import java.time.LocalDate

class EntrenadorTest {

    @Test
    @DisplayName("Instancia de entrenador con especialización correctamente")
    fun getEspecializacion() {
        val fechaNacimiento = LocalDate.of(1980, 5, 15)
        val fechaIncorporacion = LocalDate.of(2020, 1, 10)
        val entrenador = Entrenador(
            id = 1,
            nombre = "Juan",
            apellidos = "Pérez",
            fechaNacimiento = fechaNacimiento,
            fechaIncorporacion = fechaIncorporacion,
            salario = 50000.0,
            paisOrigen = "España",
            especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
        )

        assertEquals(Entrenador.Especializacion.ENTRENADOR_PRINCIPAL, entrenador.especializacion)
    }

    @Test
    @DisplayName("Instanciación de entrenador con propiedades correctamente")
    fun testEntrenadorProperties() {
        val fechaNacimiento = LocalDate.of(1980, 5, 15)
        val fechaIncorporacion = LocalDate.of(2020, 1, 10)
        val entrenador = Entrenador(
            id = 1,
            nombre = "Juan",
            apellidos = "Pérez",
            fechaNacimiento = fechaNacimiento,
            fechaIncorporacion = fechaIncorporacion,
            salario = 50000.0,
            paisOrigen = "España",
            especializacion = Entrenador.Especializacion.ENTRENADOR_ASISTENTE
        )

        assertEquals(1, entrenador.id)
        assertEquals("Juan", entrenador.nombre)
        assertEquals("Pérez", entrenador.apellidos)
        assertEquals(fechaNacimiento, entrenador.fechaNacimiento)
        assertEquals(fechaIncorporacion, entrenador.fechaIncorporacion)
        assertEquals(50000.0, entrenador.salario)
        assertEquals("España", entrenador.paisOrigen)
        assertEquals(Entrenador.Especializacion.ENTRENADOR_ASISTENTE, entrenador.especializacion)
    }

    @Test
    @DisplayName("Prueba de desigualdad entre dos entrenadores")
    fun testEntrenadorInequality() {
        val fechaNacimiento = LocalDate.of(1980, 5, 15)
        val fechaIncorporacion = LocalDate.of(2020, 1, 10)
        val entrenador1 = Entrenador(
            id = 1,
            nombre = "Juan",
            apellidos = "Pérez",
            fechaNacimiento = fechaNacimiento,
            fechaIncorporacion = fechaIncorporacion,
            salario = 50000.0,
            paisOrigen = "España",
            especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL
        )
        val entrenador2 = Entrenador(
            id = 2,
            nombre = "Carlos",
            apellidos = "Gómez",
            fechaNacimiento = fechaNacimiento,
            fechaIncorporacion = fechaIncorporacion,
            salario = 60000.0,
            paisOrigen = "Argentina",
            especializacion = Entrenador.Especializacion.ENTRENADOR_ASISTENTE
        )

        assertNotEquals(entrenador1, entrenador2)
    }
}
