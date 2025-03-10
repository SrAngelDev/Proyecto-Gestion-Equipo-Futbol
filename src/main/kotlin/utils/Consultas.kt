import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.service.PersonalServiceImpl
import java.time.LocalDate

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

        //17. Media de goles por partido de cada jugador.
        val mediaGolesPorJugador = jugadores.map { it.goles.toDouble() / it.partidosJugados }
        println("Media de goles por jugador: $mediaGolesPorJugador")


        //18. Listado de jugadores que tienen una altura superior a la media del equipo.
        val alturaMedia = jugadores.map { it.altura }.average()
        val jugadoresQueTieneAlturaSuperior = jugadores.filter { it.altura > alturaMedia }

        //19. Entrenadores que se incorporaron al club en los últimos 5 años.
        val fechaLimite = LocalDate.now().minusYears(5)
        val incorporacionEntrenadores = entrenadores.filter { it.fechaIncorporacion >= fechaLimite }
        println("Entrenadores incorporados en los últimos 5 años: $incorporacionEntrenadores")


        //20. Jugadores que han anotado más goles que el promedio de su posición.
        val promedioPorPosicion = jugadores.groupBy { it.posicion }
            .mapValues { jugador -> jugador.value.map { it.goles }.average() }
        val jugadoresMasGolesDeSuPosicion = jugadores.filter { jugador ->
            val promedioDePosicion = promedioPorPosicion[jugador.posicion] ?: 0.0
            jugador.goles > promedioDePosicion
        }

        //21. Por posición, máximo de goles, mínimo de goles y media.
        val estadisticasPorPosicion = jugadores.groupBy { it.posicion }.mapValues { entry ->
            val goles = entry.value.map { it.goles }
            Triple(goles.maxOrNull(), goles.minOrNull(), goles.average())
        }
        println("Estadísticas por posición (máximo, mínimo, media): $estadisticasPorPosicion")

        //22. Estimación del coste total de la plantilla.
        val costeTotalPlantilla = jugadores.sumBy { it.salario.toInt() }
        println("Coste total de la plantilla: $costeTotalPlantilla")

        //23. Total del salario pagado, agrupados por año de incorporación.
        val salarioPorAnioIncorporacion = jugadores.groupBy { it.fechaIncorporacion }.mapValues { entry ->
            entry.value.sumBy { it.salario.toInt() }
        }
        println("Total de salario pagado por año de incorporación: $salarioPorAnioIncorporacion")

        //24. Jugadores agrupados por país y, dentro de cada grupo, el jugador con más partidos jugados.
        val jugadorMasPartidosPorPais = jugadores.groupBy { it.paisOrigen }.mapValues { entry ->
            entry.value.maxByOrNull { it.partidosJugados }
        }
        println("Jugador con más partidos por país: $jugadorMasPartidosPorPais")

        //25. Promedio de goles por posición, y dentro de cada posición, el jugador con el mayor número de goles.
        val promedioYGoleadorPorPosicion = jugadores.groupBy { it.posicion }.mapValues { entry ->
            val promedio = entry.value.map { it.goles }.average()
            val maxGoleador = entry.value.maxByOrNull { it.goles }
            Pair(promedio, maxGoleador)
        }
        println("Promedio de goles y máximo goleador por posición: $promedioYGoleadorPorPosicion")

        //26. Entrenadores agrupados por especialidad, y dentro de cada especialidad, el entrenador con el salario más alto.
        val salarioMaximoPorEspecialidad = entrenadores.groupBy { it.especializacion }.mapValues { entry ->
            entry.value.maxByOrNull { it.salario }
        }
        println("Entrenadores con el salario más alto por especialidad: $salarioMaximoPorEspecialidad")

        //27. Jugadores agrupados por década de nacimiento, y dentro de cada grupo, el promedio de partidos jugados.
        val promedioPartidosPorDecada = jugadores.groupBy { it.fechaNacimiento.year / 10 * 10 }.mapValues { entry ->
            entry.value.map { it.partidosJugados }.average()
        }
        println("Promedio de partidos por década de nacimiento: $promedioPartidosPorDecada")

        //28. Salario promedio de los jugadores agrupados por su país de origen, y dentro de cada grupo, el jugador con el salario más bajo y alto.
        val salarioPorPais = jugadores.groupBy { it.paisOrigen }.mapValues { entry ->
            val promedio = entry.value.map { it.salario }.average()
            val minSalario = entry.value.minByOrNull { it.salario }
            val maxSalario = entry.value.maxByOrNull { it.salario }
            Triple(promedio, minSalario, maxSalario)
        }
        println("Salario promedio, mínimo y máximo por país: $salarioPorPais")

    }
}