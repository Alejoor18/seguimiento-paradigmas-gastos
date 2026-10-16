-- Tabla de gastos para el paradigma declarativo
CREATE TABLE IF NOT EXISTS gastos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    concepto TEXT NOT NULL,
    monto REAL NOT NULL CHECK (monto >= 0),
    categoria TEXT NOT NULL
);