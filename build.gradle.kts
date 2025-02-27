plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "srangeldev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Librería para logging
    implementation("org.lighthousegames:logging:1.5.0")
    implementation("ch.qos.logback:logback-classic:1.5.12")
    //mordant
    implementation("com.github.ajalt.mordant:mordant:3.0.2")
    // Librería para serialización en JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    // Librería para serialización en XML
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.90.3")
    // Librería para Mocks
    testImplementation("io.mockk:mockk:1.13.16")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    archiveFileName.set("EquipoFutbol.jar")
}

