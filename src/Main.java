import clases.Categoria;
import clases.GestorGastos;
import clases.RegistroGasto;

import java.util.List;
import java.util.Scanner;

private static final Scanner sc = new Scanner(System.in);
private static final GestorGastos gestor = new GestorGastos();

public static void main(String[] args) {

    System.out.println("=============================");
    System.out.println("Buenos Días, ¿Qué desea Hacer?");
    System.out.println("=============================");

    int seleccion;
    do {
        mostrarMenu();
        seleccion = leerEntero("Seleccione una opción: ");

        switch (seleccion) {
            case 1 -> {
                cargando("Registrar Gasto");
                registrarGasto();
            }
            case 2 -> {
                cargando("Mostrar lista de Gastos");
                listaGastos();
            }
            case 3 -> {
                cargando("Calcular gasto total acumulado");
                gastoTotalAcumulado();
            }
            case 4 -> {
                cargando("Mostrar el gasto de mayor valor");
                gastoMayorValor();
            }
            case 5 -> {
                cargando("Mostrar los gastos mayores a: X");
                gastosMayoresA();
            }
            case 6 -> {
                cargando("Buscar gasto por concepto");
                buscarConcepto();
            }
            case 0 -> System.out.println("Hasta pronto.");
            default -> System.out.println("Opción inválida, intente de nuevo.");
        }
    } while (seleccion != 0);

    sc.close();
}

private static void mostrarMenu() {
    System.out.println("""

            1. Registrar Gasto
            2. Mostrar lista de Gastos
            3. Calcular gasto total acumulado
            4. Mostrar el gasto de mayor valor
            5. Mostrar los gastos mayores a: X
            6. Buscar gasto por concepto
            0. Salir
            """);
}

private static void registrarGasto() {
    Categoria categoria = leerCategoria();

    System.out.print("Descripción (concepto): ");
    String descripcion = sc.nextLine();

    double monto = leerDouble("Monto ($): ");

    try {
        gestor.agregar(new RegistroGasto(categoria, descripcion, monto));
        System.out.println("Gasto registrado correctamente.");
    } catch (IllegalArgumentException e) {
        System.out.println("No se pudo registrar: " + e.getMessage());
    }
}

private static void listaGastos() {
    if (gestor.estaVacio()) {
        System.out.println("No hay gastos registrados.");
        return;
    }
    imprimir(gestor.listar());
}

private static void gastoTotalAcumulado() {
    System.out.printf("Gasto total acumulado: $%.2f%n", gestor.calcularTotal());
}

private static void gastoMayorValor() {
    RegistroGasto mayor = gestor.obtenerMayor();
    if (mayor == null) {
        System.out.println("No hay gastos registrados.");
    } else {
        System.out.println(mayor);
    }
}

private static void gastosMayoresA() {
    double umbral = leerDouble("Mostrar gastos mayores a ($): ");
    List<RegistroGasto> resultado = gestor.filtrarMayoresA(umbral);
    if (resultado.isEmpty()) {
        System.out.printf("Ningún gasto supera $%.2f.%n", umbral);
    } else {
        imprimir(resultado);
    }
}

private static void buscarConcepto() {
    System.out.print("Palabra clave a buscar: ");
    String palabra = sc.nextLine();
    List<RegistroGasto> resultado = gestor.buscarPorConcepto(palabra);
    if (resultado.isEmpty()) {
        System.out.println("No se encontraron coincidencias.");
    } else {
        imprimir(resultado);
    }
}

// ---------- Utilidades de entrada / salida ----------

private static void imprimir(List<RegistroGasto> gastos) {
    int i = 1;
    for (RegistroGasto gasto : gastos) {
        System.out.println("--- Gasto #" + i++ + " ---");
        System.out.print(gasto);
    }
}

private static Categoria leerCategoria() {
    Categoria[] categorias = Categoria.values();
    while (true) {
        System.out.println("Categoría:");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println("  " + (i + 1) + ". " + categorias[i]);
        }
        int opcion = leerEntero("Seleccione la categoría: ");
        if (opcion >= 1 && opcion <= categorias.length) {
            return categorias[opcion - 1];
        }
        System.out.println("Categoría inválida.");
    }
}

private static int leerEntero(String mensaje) {
    while (true) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número entero válido.");
        }
    }
}

private static double leerDouble(String mensaje) {
    while (true) {
        System.out.print(mensaje);
        try {
            return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido (ej. 25000 o 25.5).");
        }
    }
}

public static void cargando(String opcion) {
    System.out.println("=============================");
    System.out.println(opcion + ".");
    System.out.print("Cargando");
    for (int i = 0; i < 3; i++) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print(".");
    }
    System.out.println("\n=============================");
}