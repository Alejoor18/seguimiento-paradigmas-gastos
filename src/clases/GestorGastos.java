package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class GestorGastos {

    private final List<RegistroGasto> gastos = new ArrayList<>();

    public void agregar(RegistroGasto gasto) {
        gastos.add(Objects.requireNonNull(gasto, "El gasto no puede ser nulo."));
    }

    public List<RegistroGasto> listar() {
        return Collections.unmodifiableList(gastos);
    }

    public boolean estaVacio() {
        return gastos.isEmpty();
    }

    public double calcularTotal() {
        double total = 0;
        for (RegistroGasto gasto : gastos) {
            total += gasto.getMonto();
        }
        return total;
    }

    public RegistroGasto obtenerMayor() {
        RegistroGasto mayor = null;
        for (RegistroGasto gasto : gastos) {
            if (mayor == null || gasto.getMonto() > mayor.getMonto()) {
                mayor = gasto;
            }
        }
        return mayor;
    }

    public List<RegistroGasto> filtrarMayoresA(double umbral) {
        List<RegistroGasto> resultado = new ArrayList<>();
        for (RegistroGasto gasto : gastos) {
            if (gasto.getMonto() > umbral) {
                resultado.add(gasto);
            }
        }
        return resultado;
    }

    public List<RegistroGasto> buscarPorConcepto(String palabraClave) {
        List<RegistroGasto> resultado = new ArrayList<>();
        if (palabraClave == null || palabraClave.isBlank()) {
            return resultado;
        }
        String buscado = palabraClave.trim().toLowerCase();
        for (RegistroGasto gasto : gastos) {
            if (gasto.getDescripcion().toLowerCase().contains(buscado)) {
                resultado.add(gasto);
            }
        }
        return resultado;
    }
}