
package funcional;

import java.util.List;
import java.util.Scanner;

public class MainFuncional {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        List<Gasto> gastos = List.of();
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();

            try {
                int opcion = leerEntero("Seleccione una opción: ");

                switch (opcion) {
                    case 1 -> {
                        Gasto nuevo = leerGasto();
                        gastos = OperacionesGastos.registrar(gastos, nuevo);
                        System.out.println("Gasto registrado correctamente.");
                    }

                    case 2 -> mostrarGastos(
                        OperacionesGastos.listar(gastos)
                    );

                    case 3 -> System.out.printf(
                        "Total acumulado: $ %.2f%n",
                        OperacionesGastos.calcularTotal(gastos)
                    );

                    case 4 -> OperacionesGastos.obtenerMayor(gastos)
                        .ifPresentOrElse(
                            gasto -> System.out.println(
                                "Gasto de mayor valor:\n" + gasto
                            ),
                            () -> System.out.println(
                                "No hay gastos registrados."
                            )
                        );

                    case 5 -> {
                        double umbral = leerMonto(
                            "Ingrese el monto mínimo: "
                        );

                        mostrarGastos(
                            OperacionesGastos.mayoresA(gastos, umbral)
                        );
                    }

                    case 6 -> {
                        System.out.print("Concepto que desea buscar: ");
                        String concepto = SCANNER.nextLine().strip();

                        mostrarGastos(
                            OperacionesGastos.buscarPorConcepto(
                                gastos, concepto
                            )
                        );
                    }

                    case 0 -> {
                        continuar = false;
                        System.out.println("Hasta pronto.");
                    }

                    default -> System.out.println("Opción no válida.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        SCANNER.close();
    }

    private static void mostrarMenu() {
        System.out.println("""
                
                ================================
                  SISTEMA DE GASTOS FUNCIONAL
                ================================
                1. Registrar gasto
                2. Mostrar listado de gastos
                3. Calcular gasto total acumulado
                4. Mostrar gasto de mayor valor
                5. Mostrar gastos mayores a un monto
                6. Buscar gasto por concepto
                0. Salir
                """);
    }

    private static Gasto leerGasto() {
        Categoria[] categorias = Categoria.values();

        System.out.println("Categorías disponibles:");

        for (int i = 0; i < categorias.length; i++) {
            System.out.printf("%d. %s%n", i + 1, categorias[i]);
        }

        int opcion = leerEntero("Seleccione la categoría: ");

        if (opcion < 1 || opcion > categorias.length) {
            throw new IllegalArgumentException(
                "Categoría no válida."
            );
        }

        Categoria categoria = categorias[opcion - 1];

        System.out.print("Concepto del gasto: ");
        String concepto = SCANNER.nextLine();

        double monto = leerMonto("Monto del gasto: ");

        return new Gasto(categoria, concepto, monto);
    }

    private static void mostrarGastos(List<Gasto> gastos) {
        if (gastos.isEmpty()) {
            System.out.println("No se encontraron gastos.");
            return;
        }

        System.out.printf(
            "%-18s | %-25s | %s%n",
            "CATEGORÍA", "CONCEPTO", "MONTO"
        );

        System.out.println("-".repeat(65));
        gastos.forEach(System.out::println);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String entrada = SCANNER.nextLine().strip();

        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Debe ingresar un número entero."
            );
        }
    }

    private static double leerMonto(String mensaje) {
        System.out.print(mensaje);
        String entrada = SCANNER.nextLine()
            .strip()
            .replace(',', '.');

        try {
            double monto = Double.parseDouble(entrada);

            if (!Double.isFinite(monto) || monto <= 0) {
                throw new IllegalArgumentException(
                    "El monto debe ser finito y mayor que cero."
                );
            }

            return monto;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Ingrese un monto numérico válido."
            );
        }
    }
}
