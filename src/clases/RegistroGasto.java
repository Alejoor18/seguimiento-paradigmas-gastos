package clases;

import java.util.Objects;

public class RegistroGasto {

    private Categoria categoria;
    private String descripcion;
    private double monto;

    public RegistroGasto(Categoria categoria, String descripcion, double monto) {
        setCategoria(categoria);
        setDescripcion(descripcion);
        setMonto(monto);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = Objects.requireNonNull(
                categoria, "La categoría no puede ser nula."
        );
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(
                    "La descripción no puede estar vacía."
            );
        }

        this.descripcion = descripcion.trim();
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser un número finito mayor que cero."
            );
        }

        this.monto = monto;
    }

    @Override
    public String toString() {
        return """
                Categoría: %s
                Descripción: %s
                Monto: $%.2f
                """.formatted(categoria, descripcion, monto);
    }
}
