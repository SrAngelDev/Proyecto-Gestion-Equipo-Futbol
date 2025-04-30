-- Tabla base para la herencia
CREATE TABLE IF NOT EXISTS Personal (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellidos TEXT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_incorporacion DATE NOT NULL,
    salario REAL NOT NULL,
    pais_origen TEXT NOT NULL,
    tipo TEXT NOT NULL CHECK (tipo IN ('ENTRENADOR', 'JUGADOR')),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para Entrenadores (hereda de Personal)
CREATE TABLE IF NOT EXISTS Entrenadores (
    id INTEGER PRIMARY KEY,
    especializacion TEXT NOT NULL CHECK (
        especializacion IN ('ENTRENADOR_PRINCIPAL', 'ENTRENADOR_ASISTENTE', 'ENTRENADOR_PORTEROS')
    ),
    FOREIGN KEY (id) REFERENCES Personal(id) ON DELETE CASCADE
);

-- Tabla para Jugadores (hereda de Personal)
CREATE TABLE IF NOT EXISTS Jugadores (
    id INTEGER PRIMARY KEY,
    posicion TEXT NOT NULL CHECK (
        posicion IN ('PORTERO', 'DEFENSA', 'CENTROCAMPISTA', 'DELANTERO')
    ),
    dorsal INTEGER NOT NULL,
    altura REAL NOT NULL,
    peso REAL NOT NULL,
    goles INTEGER NOT NULL DEFAULT 0,
    partidos_jugados INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (id) REFERENCES Personal(id) ON DELETE CASCADE
);