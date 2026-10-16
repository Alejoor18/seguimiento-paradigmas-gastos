
package funcional;

import java.util.Objects;

public record Gasto(Categoria categoria, String concepto, double monto) {

    public Gasto {
        Objects.requireNonNull(categoria, "La categoría es obligatoria");

        if (concepto == null || concepto.isBlank()) {
            throw new IllegalArgumentException(
                "El concepto no puede estar vacío"
            );
        }

        concepto = concepto.strip();

        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(
                "El monto debe ser finito y mayor que cero"
            );
        }
    }

    @Override
    public String toString() {
        return String.format(
            "%-18s | %-25s | $ %.2f",
            categoria,
            concepto,
            monto
        );
    }
}
