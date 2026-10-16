"""Pruebas unitarias de las funcionalidades estructuradas de gastos."""

from contextlib import redirect_stdout
from io import StringIO
import unittest
from unittest.mock import patch

import main as programa


class TestFuncionalidades(unittest.TestCase):
    """Comprueba las operaciones públicas del módulo de gastos."""

    def gastos_de_ejemplo(self):
        """Devuelve gastos simples para las pruebas."""
        return [
            {"concepto": "Almuerzo", "monto": 15000.0, "categoria": "Comida"},
            {"concepto": "Bus", "monto": 3000.0, "categoria": "Transporte"},
            {"concepto": "Café", "monto": 5000.0, "categoria": "Comida"},
        ]

    def test_registrar_gasto_agrega_concepto_monto_y_categoria(self):
        gastos = []
        salida = StringIO()

        with patch("builtins.input", side_effect=["Almuerzo", "15000", "Comida"]):
            with redirect_stdout(salida):
                programa.registrar_gasto(gastos)

        self.assertEqual(
            gastos,
            [{"concepto": "Almuerzo", "monto": 15000.0, "categoria": "Comida"}],
        )
        self.assertIn("Gasto registrado correctamente.", salida.getvalue())

    def test_mostrar_gastos_imprime_elementos_y_datos(self):
        gastos = self.gastos_de_ejemplo()
        salida = StringIO()

        with redirect_stdout(salida):
            programa.mostrar_gastos(gastos)

        contenido = salida.getvalue()
        self.assertIn("1. Almuerzo | $15,000.00 | Comida", contenido)
        self.assertIn("2. Bus | $3,000.00 | Transporte", contenido)
        self.assertIn("3. Café | $5,000.00 | Comida", contenido)

    def test_mostrar_gastos_informa_si_la_lista_esta_vacia(self):
        salida = StringIO()

        with redirect_stdout(salida):
            programa.mostrar_gastos([])

        self.assertIn("No hay gastos registrados.", salida.getvalue())

    def test_calcular_total_suma_todos_los_montos(self):
        self.assertEqual(programa.calcular_total(self.gastos_de_ejemplo()), 23000.0)

    def test_calcular_total_de_lista_vacia_devuelve_cero(self):
        self.assertEqual(programa.calcular_total([]), 0.0)

    def test_obtener_gasto_mayor_devuelve_el_de_monto_mas_alto(self):
        mayor = programa.obtener_gasto_mayor(self.gastos_de_ejemplo())

        self.assertEqual(mayor["concepto"], "Almuerzo")
        self.assertEqual(mayor["monto"], 15000.0)

    def test_obtener_gasto_mayor_de_lista_vacia_devuelve_none(self):
        self.assertIsNone(programa.obtener_gasto_mayor([]))

    def test_filtrar_gastos_devuelve_solo_montos_estrictamente_superiores(self):
        gastos = self.gastos_de_ejemplo()

        encontrados = programa.filtrar_gastos_por_monto(gastos, 5000.0)

        self.assertEqual(encontrados, [gastos[0]])

    def test_filtrar_lista_vacia_devuelve_lista_vacia(self):
        self.assertEqual(programa.filtrar_gastos_por_monto([], 0.0), [])

    def test_buscar_concepto_parcial_sin_distinguir_mayusculas(self):
        gastos = self.gastos_de_ejemplo()

        encontrados = programa.buscar_gastos_por_concepto(gastos, "ALM")

        self.assertEqual(encontrados, [gastos[0]])

    def test_buscar_en_lista_vacia_devuelve_lista_vacia(self):
        self.assertEqual(programa.buscar_gastos_por_concepto([], "bus"), [])


if __name__ == "__main__":
    unittest.main()
