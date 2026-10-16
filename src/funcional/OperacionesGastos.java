
package funcional;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class OperacionesGastos {

    private OperacionesGastos() {
    }

    // 1. Registrar gasto sin modificar la lista original.
    public static List<Gasto> registrar(
            List<Gasto> gastos, Gasto nuevoGasto) {

        return Stream.concat(
                gastos.stream(),
                Stream.of(nuevoGasto)
            ).toList();
    }

    // 2. Obtener todos los gastos.
    public static List<Gasto> listar(List<Gasto> gastos) {
        return List.copyOf(gastos);
    }

    // 3. Calcular el total acumulado.
    public static double calcularTotal(List<Gasto> gastos) {
        return gastos.stream()
            .mapToDouble(Gasto::monto)
            .sum();
    }

    // 4. Obtener el gasto de mayor valor.
    public static Optional<Gasto> obtenerMayor(List<Gasto> gastos) {
        return gastos.stream()
            .max(Comparator.comparingDouble(Gasto::monto));
    }

    // 5. Obtener los gastos superiores a un umbral.
    public static List<Gasto> mayoresA(
            List<Gasto> gastos, double umbral) {

        return filtrar(
            gastos,
            gasto -> gasto.monto() > umbral
        );
    }

    // 6. Buscar por concepto ignorando mayúsculas.
    public static List<Gasto> buscarPorConcepto(
            List<Gasto> gastos, String concepto) {

        String criterio = concepto.toLowerCase(Locale.ROOT);

        return filtrar(
            gastos,
            gasto -> gasto.concepto()
                .toLowerCase(Locale.ROOT)
                .contains(criterio)
        );
    }

    // Función de orden superior: recibe un criterio.
    public static List<Gasto> filtrar(
            List<Gasto> gastos, Predicate<Gasto> criterio) {

        return gastos.stream()
            .filter(criterio)
            .toList();
    }
}
