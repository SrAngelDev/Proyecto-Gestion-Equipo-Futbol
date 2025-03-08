package srangeldev.view

import org.lighthousegames.logging.logging

class View {
    private val logger = logging()

    fun showMenu() {
        println("Bienvenido a la aplicación de Entrenadores")
        println("1. Cargar datos desde fichero según la especificación indicada.")
        println("2. Crear miembro del equipo.")
        println("3. Actualizar miembro de equipo.")
        println("4. Eliminar miembro del equipo")
        println("5. Copiar datos a fichero según la especificación realizada.")
        println("6. Realizar consultas indicadas.")
        println("7. Salir.")

        print("Seleccione una opción: ")
        val opcion = readln().toInt()

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
    }

    fun cargarDatos() {
        logger.debug { "Cargando datos" }
    }

    fun crearMiembro() {
        logger.debug { "Creando miembro" }
    }

    fun actualizarMiembro() {
        logger.debug { "Actualizando miembro" }
    }

    fun eliminarMiembro() {
        logger.debug { "Eliminando miembro" }
    }

    fun copiarDatos() {
        logger.debug { "Copiando datos" }
    }

    fun realizarConsultas() {
        logger.debug { "Realizando consultas" }
    }

    fun salir() {
        logger.debug { "Saliendo de la aplicación" }
        println("Saliendo de la aplicación")
    }
}