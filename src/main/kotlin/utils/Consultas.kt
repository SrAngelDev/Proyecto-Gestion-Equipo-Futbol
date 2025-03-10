import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.service.PersonalServiceImpl

class Consultas(private val service: PersonalServiceImpl = PersonalServiceImpl()) {

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


        // 9. Jugadores con un salario mayor al promedio del equipo.
        val jugadoresConSalarioMayorAlPromedio = jugadores.filter { it.salario > jugadores.map { it.salario }.average() }
        println("Jugadores con salario mayor al promedio: $jugadoresConSalarioMayorAlPromedio")

        // 10. Número total de partidos jugados por todos los jugadores.
        val numeroTotalDePartidos = jugadores.sumBy { it.partidosJugados }
        println("Número total de partidos jugados: $numeroTotalDePartidos")

        // 11. Jugadores agrupados por el año de su incorporación al club.
        val jugadoresAgrupadosPorAnioIncorporacion = jugadores.groupBy { it.fechaIncorporacion }
        println("Jugadores agrupados por el año de su incorporación: $jugadoresAgrupadosPorAnioIncorporacion")

        // 12. Entrenadores agrupados por su especialidad.
        val entrenadoresAgrupadosPorEspecialidad = entrenadores.groupBy { it.especializacion }
        println("Entrenadores agrupados por su especialidad: $entrenadoresAgrupadosPorEspecialidad")

        // 13. Jugador más joven en el equipo.
        val jugadorMasJoven = jugadores.minByOrNull { it.fechaNacimiento }
        println("Jugador más joven: $jugadorMasJoven")

        // 14. Promedio de peso de los jugadores por posición.
        val promedioPesoPorPosicion = jugadores.groupBy { it.posicion }.mapValues {
            it.value.map { jugador -> jugador.peso }.average() }
        println("Promedio de peso por posición: $promedioPesoPorPosicion")

        // 15. Listado de todos los jugadores que tienen un dorsal par.
        val jugadoresConDorsalPar = jugadores.filter { it.dorsal % 2 == 0 }
        println("Jugadores con dorsal par: $jugadoresConDorsalPar")

        // 16. Jugadores que han jugado menos de 5 partidos.
        val jugadoresConMenosDeCincoPartidos = jugadores.filter { it.partidosJugados < 5 }
        println("Jugadores que han jugado menos de 5 partidos: $jugadoresConMenosDeCincoPartidos")
    }
}