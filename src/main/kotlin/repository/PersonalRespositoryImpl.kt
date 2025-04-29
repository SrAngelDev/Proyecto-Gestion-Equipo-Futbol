package srangeldev.repository

import org.lighthousegames.logging.logging
import srangeldev.database.DataBaseManager
import srangeldev.dto.PersonalXmlDto
import srangeldev.models.Entrenador
import srangeldev.models.Jugador
import srangeldev.models.Personal
import java.sql.Statement
import java.time.LocalDateTime

/**
 * Implementación del repositorio de personal.
 */
class PersonalRespositoryImpl : PersonalRepository {
    private val logger = logging()
    private val personal = mutableListOf<Personal>()

    init {
        logger.debug { "Inicializando repositorio de personal" }
    }

    private fun getEntrenadores(): List<Entrenador> {
        val sql = "SELECT * FROM Entrenadores"
        DataBaseManager.use { db ->
            val res = db.connection?.prepareStatement(sql)!!.executeQuery()
            while (res.next()) {
                val entrenadores = Entrenador(
                    id = res.getInt("id"),
                    nombre = res.getString("nombre"),
                    apellidos = res.getString("apellidos"),
                    fechaNacimiento = res.getDate("fechaNacimiento").toLocalDate(),
                    fechaIncorporacion = res.getDate("fechaIncorporacion").toLocalDate(),
                    salario = res.getDouble("salario"),
                    paisOrigen = res.getString("paisOrigen"),
                    especializacion = Entrenador.Especializacion.valueOf(res.getString("especializacion")),
                    createdAt = LocalDateTime.parse(res.getString("createdAt")),
                    updatedAt = LocalDateTime.parse(res.getString("updatedAt"))
                )
                personal.add(entrenadores)
            }
        }
        return personal.filterIsInstance<Entrenador>()
    }

    private fun getJugadores(): List<Jugador> {
        val sql = "SELECT * FROM Jugadores"
        DataBaseManager.use { db ->
            val res = db.connection?.prepareStatement(sql)!!.executeQuery()
            while (res.next()) {
                val jugadores = Jugador(
                    id = res.getInt("id"),
                    nombre = res.getString("nombre"),
                    apellidos = res.getString("apellidos"),
                    fechaNacimiento = res.getDate("fechaNacimiento").toLocalDate(),
                    fechaIncorporacion = res.getDate("fechaIncorporacion").toLocalDate(),
                    salario = res.getDouble("salario"),
                    paisOrigen = res.getString("paisOrigen"),
                    posicion = Jugador.Posicion.valueOf(res.getString("posicion")),
                    dorsal = res.getInt("dorsal"),
                    altura = res.getDouble("altura"),
                    peso = res.getDouble("peso"),
                    goles = res.getInt("goles"),
                    partidosJugados = res.getInt("partidosJugados"),
                    createdAt = LocalDateTime.parse(res.getString("createdAt")),
                    updatedAt = LocalDateTime.parse(res.getString("updatedAt"))
                )
                personal.add(jugadores)
            }
        }
        return personal.filterIsInstance<Jugador>()
    }

    /**
     * Obtiene una lista de todos los objetos personal.
     *
     * @return Una lista de objetos personal.
     */
    override fun getAll(): List<Personal> {
        logger.debug { "Obteniendo a todo el personal" }
        if (personal.isEmpty()) {
            logger.debug { "Cargando personal desde la base de datos" }
            getEntrenadores()
            getJugadores()
        }
        return personal
    }

    /**
     * Obtiene un objeto personal por su ID.
     *
     * @param id El ID del objeto personal a obtener.
     * @return El objeto personal con el ID especificado, o null si no se encuentra.
     */
    override fun getById(id: Int): Personal? {
        logger.debug { "Obteniendo personal por ID: $id" }
        var personal: Personal? = null

        val sql = "SELECT * FROM Personal"

        DataBaseManager.use { db ->
            val res = db.connection?.prepareStatement(sql)!!.executeQuery()
            while (res.next()) {
                if (res.getInt("id") == id) {
                    personal = when (res.getString("tipo")) {
                        "Entrenador" -> Entrenador(
                            id = res.getInt("id"),
                            nombre = res.getString("nombre"),
                            apellidos = res.getString("apellidos"),
                            fechaNacimiento = res.getDate("fechaNacimiento").toLocalDate(),
                            fechaIncorporacion = res.getDate("fechaIncorporacion").toLocalDate(),
                            salario = res.getDouble("salario"),
                            paisOrigen = res.getString("paisOrigen"),
                            especializacion = Entrenador.Especializacion.valueOf(res.getString("especializacion")),
                            createdAt = LocalDateTime.parse(res.getString("createdAt")),
                            updatedAt = LocalDateTime.parse(res.getString("updatedAt"))
                        )

                        "Jugador" -> Jugador(
                            id = res.getInt("id"),
                            nombre = res.getString("nombre"),
                            apellidos = res.getString("apellidos"),
                            fechaNacimiento = res.getDate("fechaNacimiento").toLocalDate(),
                            fechaIncorporacion = res.getDate("fechaIncorporacion").toLocalDate(),
                            salario = res.getDouble("salario"),
                            paisOrigen = res.getString("paisOrigen"),
                            posicion = Jugador.Posicion.valueOf(res.getString("posicion")),
                            dorsal = res.getInt("dorsal"),
                            altura = res.getDouble("altura"),
                            peso = res.getDouble("peso"),
                            goles = res.getInt("goles"),
                            partidosJugados = res.getInt("partidosJugados"),
                            createdAt = LocalDateTime.parse(res.getString("createdAt")),
                            updatedAt = LocalDateTime.parse(res.getString("updatedAt"))
                        )

                        else -> null
                    }
                }
            }
        }
        return personal
    }

    /**
     * Guarda un objeto personal.
     *
     * @param entidad El objeto personal a guardar.
     * @return El objeto personal guardado.
     */
    override fun save(entidad: Personal): Personal {
        logger.debug { "Guardando personal: $entidad" }
        val timeStamp = LocalDateTime.now()

        val sql = when (entidad) {
            is Jugador -> """
        INSERT INTO Personal (nombre, apellidos, fecha_nacimiento, fecha_incorporacion, salario, pais_origen, tipo, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, 'JUGADOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
        INSERT INTO Jugadores (id, posicion, dorsal, altura, peso, goles, partidos_jugados) VALUES (last_insert_rowid(), ?, ?, ?, ?, ?, ?);
        """.trimIndent()

            is Entrenador -> """
        INSERT INTO Personal (nombre, apellidos, fecha_nacimiento, fecha_incorporacion,salario, pais_origen, tipo, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, 'ENTRENADOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
        INSERT INTO Entrenadores (id, especializacion) VALUES (last_insert_rowid(), ?);
        """.trimIndent()
            else -> throw IllegalArgumentException("Tipo desconocido de Personal")
        }

        DataBaseManager.use { db ->
            val preparedStatement = db.connection?.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)!!
            preparedStatement.setString(1, entidad.nombre)
            preparedStatement.setString(2, entidad.apellidos)
            preparedStatement.setDate(3, java.sql.Date.valueOf(entidad.fechaNacimiento))
            preparedStatement.setDate(4, java.sql.Date.valueOf(entidad.fechaIncorporacion))
            preparedStatement.setDouble(5, entidad.salario)
            preparedStatement.setString(6, entidad.paisOrigen)

            if (entidad is Jugador) {
                preparedStatement.setString(7, entidad.posicion.name)
                preparedStatement.setInt(8, entidad.dorsal)
                preparedStatement.setDouble(9, entidad.altura)
                preparedStatement.setDouble(10, entidad.peso)
                preparedStatement.setInt(11, entidad.goles)
                preparedStatement.setInt(12, entidad.partidosJugados)
            } else if (entidad is Entrenador) {
                preparedStatement.setString(7, entidad.especializacion.name)
            }

            preparedStatement.executeUpdate()
            val generatedId = preparedStatement.generatedKeys.let {
                it.next()
                it.getInt(1)
            }

            when (entidad) {
                is Jugador -> Jugador(
                    id = generatedId,
                    nombre = entidad.nombre,
                    apellidos = entidad.apellidos,
                    fechaNacimiento = entidad.fechaNacimiento,
                    fechaIncorporacion = entidad.fechaIncorporacion,
                    salario = entidad.salario,
                    paisOrigen = entidad.paisOrigen,
                    posicion = entidad.posicion,
                    dorsal = entidad.dorsal,
                    altura = entidad.altura,
                    peso = entidad.peso,
                    goles = entidad.goles,
                    partidosJugados = entidad.partidosJugados,
                    createdAt = timeStamp,
                    updatedAt = timeStamp
                )

                is Entrenador -> Entrenador(
                    id = generatedId,
                    nombre = entidad.nombre,
                    apellidos = entidad.apellidos,
                    fechaNacimiento = entidad.fechaNacimiento,
                    fechaIncorporacion = entidad.fechaIncorporacion,
                    salario = entidad.salario,
                    paisOrigen = entidad.paisOrigen,
                    especializacion = entidad.especializacion,
                    createdAt = timeStamp,
                    updatedAt = timeStamp
                )

                else -> throw IllegalArgumentException("Tipo desconocido de Personal")
            }
        }
        return entidad
    }

    /**
     * Actualiza un objeto personal por su ID.
     *
     * @param id El ID del objeto personal a actualizar.
     * @param entidad El objeto personal con los datos actualizados.
     * @return El objeto personal actualizado, o null si no se encuentra.
     */
    override fun update(id: Int, entidad: Personal): Personal? {
        logger.debug { "Actualizando personal con ID: $id" }
        // Aqui nos pasa lo mismo que en el save, tenemos que diferenciar entre si es un jugador o un entrenador
        val personal: Personal? = this.getById(id)
        val timeStamp = LocalDateTime.now()

        if (personal != null) {
            val sql = when (entidad) {
                is Jugador -> "UPDATE Jugadores SET nombre = ?, apellidos = ?, fechaNacimiento = ?, fechaIncorporacion = ?, salario = ?, paisOrigen = ?, posicion = ?, dorsal = ?, altura = ?, peso = ?, goles = ?, partidosJugados = ? WHERE id = ?"
                is Entrenador -> "UPDATE Entrenadores SET nombre = ?, apellidos = ?, fechaNacimiento = ?, fechaIncorporacion = ?, salario = ?, paisOrigen = ?, especializacion = ? WHERE id = ?"
                else -> throw IllegalArgumentException("Tipo desconocido de Personal")
            }
            DataBaseManager.use { db ->
                val preparedStatement = db.connection?.prepareStatement(sql)!!
                preparedStatement.setString(1, entidad.nombre)
                preparedStatement.setString(2, entidad.apellidos)
                preparedStatement.setDate(3, java.sql.Date.valueOf(entidad.fechaNacimiento))
                preparedStatement.setDate(4, java.sql.Date.valueOf(entidad.fechaIncorporacion))
                preparedStatement.setDouble(5, entidad.salario)
                preparedStatement.setString(6, entidad.paisOrigen)

                if (entidad is Jugador) {
                    preparedStatement.setString(7, entidad.posicion.name)
                    preparedStatement.setInt(8, entidad.dorsal)
                    preparedStatement.setDouble(9, entidad.altura)
                    preparedStatement.setDouble(10, entidad.peso)
                    preparedStatement.setInt(11, entidad.goles)
                    preparedStatement.setInt(12, entidad.partidosJugados)
                    preparedStatement.setInt(13, id)
                } else if (entidad is Entrenador) {
                    preparedStatement.setString(7, entidad.especializacion.name)
                    preparedStatement.setInt(8, id)
                }

                preparedStatement.executeUpdate()
            }
            return entidad
        } else {
            logger.debug { "No se encontró el personal con ID: $id" }
            return null
        }
    }

    /**
     * Elimina un objeto personal por su ID.
     *
     * @param id El ID del objeto personal a eliminar.
     * @return El objeto personal eliminado.
     */
    override fun delete(id: Int): Personal? {
        logger.debug { "Eliminando personal con ID: $id" }
        // Aqui hay que eliminar la id tanto de la tabla de personal como la propia de jugador o entrenador
        val personal: Personal? = this.getById(id)
        if (personal != null) {
            val sql = when (personal) {
                is Jugador -> "DELETE FROM Jugadores WHERE id = ?"
                is Entrenador -> "DELETE FROM Entrenadores WHERE id = ?"
                else -> throw IllegalArgumentException("Tipo desconocido de Personal")
            }
            DataBaseManager.use { db ->
                val preparedStatement = db.connection?.prepareStatement(sql)!!
                preparedStatement.setInt(1, id)
                preparedStatement.executeUpdate()
            }
            return personal
        } else {
            logger.debug { "No se encontró el personal con ID: $id" }
            return null
        }
    }
}