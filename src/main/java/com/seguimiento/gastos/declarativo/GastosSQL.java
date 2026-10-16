package com.seguimiento.gastos.declarativo;

import java.sql.*;
import java.util.Scanner;

/**
 * Paradigma Declarativo - Sistema de Control de Gastos Personales
 * Usa SQLite + JDBC. Se expresa QUÉ se quiere obtener y el motor de BD
 * se encarga de CÓMO obtenerlo.
 */
public class GastosSQL {

    private static final String DB_URL = "jdbc:sqlite:gastos.db";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        crearTablaSiNoExiste();
        mostrarMenu();
    }

    // ==================== CREAR TABLA ====================
    private static void crearTablaSiNoExiste() {
        String sql = """
            CREATE TABLE IF NOT EXISTS gastos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                concepto TEXT NOT NULL,
                monto REAL NOT NULL CHECK (monto >= 0),
                categoria TEXT NOT NULL
            )
            """;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    // ==================== MENÚ PRINCIPAL ====================
    private static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("  CONTROL DE GASTOS - DECLARATIVO");
            System.out.println("=================================");
            System.out.println("1. Registrar gasto");
            System.out.println("2. Mostrar listado de gastos");
            System.out.println("3. Calcular gasto total acumulado");
            System.out.println("4. Mostrar el gasto de mayor valor");
            System.out.println("5. Mostrar gastos mayores a un monto");
            System.out.println("6. Buscar gasto por concepto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> registrarGasto();
                case 2 -> mostrarListado();
                case 3 -> calcularTotal();
                case 4 -> mostrarMayor();
                case 5 -> filtrarPorMonto();
                case 6 -> buscarPorConcepto();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    // ==================== 1. REGISTRAR GASTO ====================
    private static void registrarGasto() {
        System.out.print("Concepto: ");
        String concepto = scanner.nextLine().trim();
        if (concepto.isEmpty()) {
            System.out.println("El concepto no puede estar vacío.");
            return;
        }

        System.out.print("Monto: ");
        double monto;
        try {
            monto = Double.parseDouble(scanner.nextLine().trim());
            if (monto < 0) {
                System.out.println("El monto no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido.");
            return;
        }

        System.out.print("Categoría (Alimentación, Transporte, Servicios, Entretenimiento): ");
        String categoria = scanner.nextLine().trim();
        if (categoria.isEmpty()) {
            System.out.println("La categoría no puede estar vacía.");
            return;
        }

        String sql = "INSERT INTO gastos (concepto, monto, categoria) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, concepto);
            pstmt.setDouble(2, monto);
            pstmt.setString(3, categoria);
            pstmt.executeUpdate();
            System.out.println("Gasto registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    // ==================== 2. MOSTRAR LISTADO ====================
    private static void mostrarListado() {
        String sql = "SELECT id, concepto, monto, categoria FROM gastos ORDER BY id";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean hayDatos = false;
            System.out.println("\n--- LISTADO DE GASTOS ---");
            while (rs.next()) {
                hayDatos = true;
                System.out.printf("%d. %s | $%.2f | %s%n",
                        rs.getInt("id"),
                        rs.getString("concepto"),
                        rs.getDouble("monto"),
                        rs.getString("categoria"));
            }
            if (!hayDatos) {
                System.out.println("No hay gastos registrados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    // ==================== 3. CALCULAR TOTAL ====================
    private static void calcularTotal() {
        // Declarativo: pedimos SUM y el motor calcula
        String sql = "SELECT COALESCE(SUM(monto), 0) AS total FROM gastos";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.printf("Gasto total acumulado: $%.2f%n", rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular total: " + e.getMessage());
        }
    }

    // ==================== 4. GASTO DE MAYOR VALOR ====================
    private static void mostrarMayor() {
        // Declarativo: ORDER BY + LIMIT
        String sql = "SELECT * FROM gastos ORDER BY monto DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("\n--- GASTO DE MAYOR VALOR ---");
                System.out.printf("Concepto: %s | Monto: $%.2f | Categoría: %s%n",
                        rs.getString("concepto"),
                        rs.getDouble("monto"),
                        rs.getString("categoria"));
            } else {
                System.out.println("No hay gastos registrados.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== 5. FILTRAR POR MONTO ====================
    private static void filtrarPorMonto() {
        System.out.print("Mostrar gastos mayores a: ");
        double limite;
        try {
            limite = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        // Declarativo: WHERE monto > ?
        String sql = "SELECT * FROM gastos WHERE monto > ? ORDER BY monto DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, limite);
            ResultSet rs = pstmt.executeQuery();

            boolean hayDatos = false;
            System.out.println("\n--- GASTOS MAYORES A $" + limite + " ---");
            while (rs.next()) {
                hayDatos = true;
                System.out.printf("%s | $%.2f | %s%n",
                        rs.getString("concepto"),
                        rs.getDouble("monto"),
                        rs.getString("categoria"));
            }
            if (!hayDatos) {
                System.out.println("No se encontraron gastos mayores a $" + limite);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== 6. BUSCAR POR CONCEPTO ====================
    private static void buscarPorConcepto() {
        System.out.print("Buscar por concepto (palabra clave): ");
        String texto = scanner.nextLine().trim();
        if (texto.isEmpty()) {
            System.out.println("Debe ingresar un texto.");
            return;
        }

        // Declarativo: LIKE (búsqueda por subcadena)
        String sql = "SELECT * FROM gastos WHERE LOWER(concepto) LIKE LOWER(?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + texto + "%");
            ResultSet rs = pstmt.executeQuery();

            boolean hayDatos = false;
            System.out.println("\n--- RESULTADOS DE BÚSQUEDA ---");
            while (rs.next()) {
                hayDatos = true;
                System.out.printf("%s | $%.2f | %s%n",
                        rs.getString("concepto"),
                        rs.getDouble("monto"),
                        rs.getString("categoria"));
            }
            if (!hayDatos) {
                System.out.println("No se encontraron coincidencias.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}