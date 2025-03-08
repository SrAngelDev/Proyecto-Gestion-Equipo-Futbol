package srangeldev.view

class View {
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
        val opcion = readln().toInt(

        when(opcion) {
            1 -> cargarDatos()
            2 -> crearMiembro()
            3 -> actualizarMiembro()
            4 -> eliminarMiembro()
            5 -> copiarDatos()
    }
}