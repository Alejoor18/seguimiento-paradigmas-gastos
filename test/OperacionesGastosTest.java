
import funcional.Categoria;
import funcional.Gasto;
import funcional.OperacionesGastos;

import java.util.List;

public class OperacionesGastosTest {

    public static void main(String[] args) {
        Gasto alimentacion = new Gasto(
            Categoria.ALIMENTACION, "Mercado", 80.0
        );
        Gasto transporte = new Gasto(
            Categoria.TRANSPORTE, "Bus", 20.0
        );
        Gasto servicios = new Gasto(
            Categoria.SERVICIOS, "Internet", 100.0
        );

        List<Gasto> gastos = List.of(alimentacion, transporte);

        // 1. Registrar sin modificar la lista original.
        List<Gasto> registrados =
            OperacionesGastos.registrar(gastos, servicios);

        comprobar(registrados.size() == 3, "Registrar gasto");
        comprobar(gastos.size() == 2, "Conservar lista original");

        // 2. Listar los gastos.
        comprobar(
            OperacionesGastos.listar(gastos).equals(gastos),
            "Listar gastos"
        );

        // 3. Calcular el total.
        comprobar(
            OperacionesGastos.calcularTotal(gastos) == 100.0,
            "Calcular total"
        );

        // 4. Obtener el gasto de mayor valor.
        comprobar(
            OperacionesGastos.obtenerMayor(gastos)
                .orElseThrow().equals(alimentacion),
            "Obtener gasto mayor"
        );

        // 5. Filtrar por monto.
        comprobar(
            OperacionesGastos.mayoresA(gastos, 50.0)
                .equals(List.of(alimentacion)),
            "Filtrar gastos por monto"
        );

        // 6. Buscar concepto ignorando mayúsculas.
        comprobar(
            OperacionesGastos.buscarPorConcepto(gastos, "MER")
                .equals(List.of(alimentacion)),
            "Buscar por concepto"
        );

        // Comprobar búsqueda vacía o nula.
        comprobar(
            OperacionesGastos.buscarPorConcepto(gastos, "").isEmpty(),
            "Buscar concepto vacío"
        );
        comprobar(
            OperacionesGastos.buscarPorConcepto(gastos, null).isEmpty(),
            "Buscar concepto nulo"
        );

        System.out.println("Todas las pruebas finalizaron correctamente.");
    }

    private static void comprobar(boolean resultado, String prueba) {
        if (!resultado) {
            throw new AssertionError("Falló la prueba: " + prueba);
        }

        System.out.println("OK: " + prueba);
    }
}
