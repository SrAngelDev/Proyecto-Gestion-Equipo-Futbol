package srangeldev.view

import org.lighthousegames.logging.logging
import srangeldev.controller.Controller

/**
 * Clase que representa la vista de la aplicación.
 */
class View {
    private val logger = logging()
    private val controller = Controller()

    /**
     * Muestra el menú de la aplicación.
     */
    fun showMenu() {
        println("Bienvenido a la aplicación de Entrenadores")
        var opcion: Int
        do {
            println("1. Cargar datos desde fichero según la especificación indicada.")
            println("2. Crear miembro del equipo.")
            println("3. Actualizar miembro de equipo.")
            println("4. Eliminar miembro del equipo")
            println("5. Copiar datos a fichero según la especificación realizada.")
            println("6. Realizar consultas indicadas.")
            println("7. Salir.")

            print("Seleccione una opción: ")
            opcion = readln().toInt()

            when(opcion) {
                1 -> cargarDatos()
                2 -> crearMiembro()
                3 -> actualizarMiembro()
                4 -> eliminarMiembro()
                5 -> copiarDatos()
                6 -> realizarConsultas()
                7 -> salir()
                else -> println("Opción no válida.")
            }
        } while (opcion != 7)
    }

    /**
     * Manda al controlador cargar los datos.
     */
    fun cargarDatos() {
        logger.debug { "Cargando datos" }
        controller.cargarDatos()
    }

    /**
     * Manda al controlador crear un miembro.
     */
    fun crearMiembro() {
        logger.debug { "Creando miembro" }
        controller.crearMiembro()
    }

    /**
     * Manda al controlador actualizar un miembro.
     */
    fun actualizarMiembro() {
        logger.debug { "Actualizando miembro" }
        controller.actualizarMiembro()
    }

    /**
     * Manda al controlador eliminar un miembro.
     */
    fun eliminarMiembro() {
        logger.debug { "Eliminando miembro" }
        controller.eliminarMiembro()
    }

    /**
     * Manda al controlador copiar los datos.
     */
    fun copiarDatos() {
        logger.debug { "Copiando datos" }
        controller.copiarDatos()
    }

    /**
     * Manda al controlador realizar consultas.
     */
    fun realizarConsultas() {
        logger.debug { "Realizando consultas" }
        controller.realizarConsultas()
    }

    /**
     * Muestra un mensaje de salida.
     */
    fun salir() {
        logger.debug { "Saliendo de la aplicación" }
        println("Saliendo de la aplicación")
    }
}