import nl.adaptivity.xmlutil.serialization.XML
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import srangeldev.dto.EquipoDto
import srangeldev.exceptions.PersonalException
import srangeldev.models.Entrenador
import srangeldev.storage.PersonalStorageXml
import java.io.File
import java.nio.file.Path
import java.time.LocalDate

class PersonalStorageXmlTest {

    private lateinit var personalStorageXml: PersonalStorageXml
    private lateinit var xml: XML

    @BeforeEach
    fun setUp() {
        personalStorageXml = PersonalStorageXml()
        xml = XML {}
    }

    @Test
    @DisplayName("Leer de un fichero que no existe")
    fun readFromFileNotExist(@TempDir tempDir: Path) {
        val file = tempDir.resolve("nonexistent.xml").toFile()

        val exception = assertThrows(PersonalException.PersonalStorageException::class.java) {
            personalStorageXml.readFromFile(file)
        }

        assertEquals("Error en el almacenamiento: El fichero no existe o es un fichero que no se puede leer: $file", exception.message)
    }

    @Test
    @DisplayName("Escribir en un fichero XML")
    fun writeToFile(@TempDir tempDir: Path) {
        val file = tempDir.resolve("personals.xml").toFile()

        val personalList = listOf(
            Entrenador(id = 1, nombre = "Angel", apellidos = "Sanchez", fechaNacimiento = LocalDate.parse("2000-01-01"), fechaIncorporacion = LocalDate.parse("2020-01-01"), salario = 1000.0, paisOrigen = "ES", especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL)
        )

        personalStorageXml.writeToFile(file, personalList)

        val xmlString = file.readText()

        val personalDto: EquipoDto = xml.decodeFromString(EquipoDto.serializer(), xmlString)

        assertEquals(1, personalDto.equipo.size)
        assertEquals("Angel", personalDto.equipo[0].nombre)
    }

    @Test
    @DisplayName("Leer de un fichero vacío")
    fun readFromFileEmpty(@TempDir tempDir: Path) {
        val file = tempDir.resolve("empty.xml").toFile()
        file.createNewFile()

        val exception = assertThrows(PersonalException.PersonalStorageException::class.java) {
            personalStorageXml.readFromFile(file)
        }

        assertEquals("Error en el almacenamiento: El fichero no existe o es un fichero que no se puede leer: $file", exception.message)
    }

    @Test
    @DisplayName("Escribir en un fichero con directorio padre inexistente")
    fun writeToFileParentDirNotExist(@TempDir tempDir: Path) {
        val file = File(tempDir.resolve("nonexistent_dir/personals.xml").toString())

        val personalList = listOf(
            Entrenador(id = 1, nombre = "Angel", apellidos = "Sanchez", fechaNacimiento = LocalDate.parse("2000-01-01"), fechaIncorporacion = LocalDate.parse("2020-01-01"), salario = 1000.0, paisOrigen = "ES", especializacion = Entrenador.Especializacion.ENTRENADOR_PRINCIPAL)
        )

        val exception = assertThrows(PersonalException.PersonalStorageException::class.java) {
            personalStorageXml.writeToFile(file, personalList)
        }

        assertEquals("Error en el almacenamiento: El directorio padre del fichero no existe o no es un directorio o el fichero no tiene extensión XML: ${file.parentFile.absolutePath}", exception.message)
    }

    @Test
    @DisplayName("Leer de un fichero con formato incorrecto")
    fun readFromFileIncorrectFormat(@TempDir tempDir: Path) {
        val file = tempDir.resolve("personals.txt").toFile()
        file.writeText("Invalid content")

        val exception = assertThrows(PersonalException.PersonalStorageException::class.java) {
            personalStorageXml.readFromFile(file)
        }

        assertEquals("Error en el almacenamiento: El fichero no existe o es un fichero que no se puede leer: $file", exception.message)
    }

    @Test
    @DisplayName("Leer de un fichero con contenido válido")
    fun readFromFileValidContent(@TempDir tempDir: Path) {
        val file = tempDir.resolve("personals.xml").toFile()
        file.writeText(
            """
            <equipo>
                <personal id="1">
                    <tipo>Entrenador</tipo>
                    <nombre>Angel</nombre>
                    <apellidos>Sánchez</apellidos>
                    <fechaNacimiento>1975-02-10</fechaNacimiento>
                    <fechaIncorporacion>2022-05-15</fechaIncorporacion>
                    <salario>60000.0</salario>
                    <pais>ES</pais>
                    <especialidad>ENTRENADOR_PORTEROS</especialidad>
                </personal>
            </equipo>
            """.trimIndent()
        )

        val personalList = personalStorageXml.readFromFile(file)

        assertEquals(1, personalList.size)
        assertTrue(personalList[0] is Entrenador)
        val entrenador = personalList[0] as Entrenador
        assertEquals(1, entrenador.id)
        assertEquals("Angel", entrenador.nombre)
        assertEquals("Sánchez", entrenador.apellidos)
        assertEquals(LocalDate.parse("1975-02-10"), entrenador.fechaNacimiento)
        assertEquals(LocalDate.parse("2022-05-15"), entrenador.fechaIncorporacion)
        assertEquals(60000.0, entrenador.salario)
        assertEquals("ES", entrenador.paisOrigen)
        assertEquals(Entrenador.Especializacion.ENTRENADOR_PORTEROS, entrenador.especializacion)
    }
}