import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.service.PersonalServiceImpl

class Consultas(private val service: PersonalServiceImpl) {

    fun realizarConsultas() {
        println("Realizando consultas...")

        // 1. Listados de personal agrupados por entrenadores y jugadores.
        val entrenadores = service.getAll().filterIsInstance<Entrenador>()
        val jugadores = service.getAll().filterIsInstance<Jugador>()
        println("Entrenadores: $entrenadores")
        println("Jugadores: $jugadores")

        // 2. El delantero más alto.
        val delanteroMasAlto = jugadores.filter { it.posicion == Jugador.Posicion.DELANTERO }.maxByOrNull { it.altura }
        println("Delantero más alto: $delanteroMasAlto")

        // 3. Media de goles de los delanteros.
        val mediaGolesDelanteros = jugadores.filter { it.posicion == Jugador.Posicion.DELANTERO }.map { it.goles }.average()
        println("Media de goles de los delanteros: $mediaGolesDelanteros")

        // 4. Defensa con más partidos jugados.
        val defensaMasPartidos = jugadores.filter { it.posicion == Jugador.Posicion.DEFENSA }.maxByOrNull { it.partidosJugados }
        println("Defensa con más partidos jugados: $defensaMasPartidos")

        // 5. Jugadores agrupados por su país de origen.
        val jugadoresPorPais = jugadores.groupBy { it.paisOrigen }
        println("Jugadores agrupados por país de origen: $jugadoresPorPais")

        // 6. Entrenador con el mayor salario.
        val entrenadorMayorSalario = entrenadores.maxByOrNull { it.salario }
        println("Entrenador con el mayor salario: $entrenadorMayorSalario")

        // 7. Promedio de altura de los jugadores agrupados por posición.
        val promedioAlturaPorPosicion = jugadores.groupBy { it.posicion }.mapValues { entry -> entry.value.map { it.altura }.average() }
        println("Promedio de altura de los jugadores agrupados por posición: $promedioAlturaPorPosicion")

        // 8. Listado de todos los jugadores que han anotado más de 10 goles.
        val jugadoresMasDe10Goles = jugadores.filter { it.goles > 10 }
        println("Jugadores con más de 10 goles: $jugadoresMasDe10Goles")
    }
}