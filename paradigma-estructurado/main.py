"""Control de gastos personales con programación estructurada.

Los gastos se representan con diccionarios almacenados en una lista. El
programa utiliza funciones, condicionales y ciclos, sin clases personalizadas.
"""

import math


def solicitar_texto(mensaje):
    """Solicita texto no vacío y lo devuelve sin espacios externos."""
    while True:
        texto = input(mensaje).strip()
        if texto:
            return texto
        print("Este campo no puede quedar vacío.")


def solicitar_monto(mensaje, permitir_cero=False):
    """Solicita un número finito válido; los gastos deben ser mayores que cero."""
    while True:
        entrada = input(mensaje).strip().replace(",", ".")
        try:
            monto = float(entrada)
        except ValueError:
            print("Entrada inválida. Escribe un número, por ejemplo: 15000.")
            continue

        if not math.isfinite(monto):
            print("Ingresa un número válido y finito.")
        elif monto < 0:
            print("El monto no puede ser negativo.")
        elif monto == 0 and not permitir_cero:
            print("El monto del gasto debe ser mayor que cero.")
        else:
            return monto


def registrar_gasto(gastos):
    """Pide los datos de un gasto y lo agrega a la lista recibida."""
    print("\n--- Registrar gasto ---")
    concepto = solicitar_texto("Concepto: ")
    monto = solicitar_monto("Monto ($): ")
    categoria = solicitar_texto("Categoría: ")
    gastos.append({"concepto": concepto, "monto": monto, "categoria": categoria})
    print("Gasto registrado correctamente.")


def mostrar_gastos(gastos, titulo="Listado de gastos"):
    """Muestra una lista de gastos o informa cuando está vacía."""
    print(f"\n--- {titulo} ---")
    if not gastos:
        print("No hay gastos registrados.")
        return

    for indice, gasto in enumerate(gastos, start=1):
        print(
            f"{indice}. {gasto['concepto']} | "
            f"${gasto['monto']:,.2f} | {gasto['categoria']}"
        )


def calcular_total(gastos):
    """Devuelve la suma de los montos de todos los gastos."""
    total = 0.0
    for gasto in gastos:
        total += gasto["monto"]
    return total


def obtener_gasto_mayor(gastos):
    """Devuelve el gasto de mayor valor, o None si la lista está vacía."""
    if not gastos:
        return None

    mayor = gastos[0]
    for gasto in gastos[1:]:
        if gasto["monto"] > mayor["monto"]:
            mayor = gasto
    return mayor


def filtrar_gastos_por_monto(gastos, limite):
    """Devuelve los gastos cuyo monto sea estrictamente mayor que el límite."""
    encontrados = []
    for gasto in gastos:
        if gasto["monto"] > limite:
            encontrados.append(gasto)
    return encontrados


def buscar_gastos_por_concepto(gastos, texto):
    """Devuelve coincidencias parciales de concepto sin distinguir mayúsculas."""
    texto_busqueda = texto.strip().casefold()
    encontrados = []
    for gasto in gastos:
        if texto_busqueda in gasto["concepto"].casefold():
            encontrados.append(gasto)
    return encontrados


def mostrar_menu():
    """Imprime las opciones disponibles."""
    print("\n=== CONTROL DE GASTOS PERSONALES ===")
    print("1. Registrar gasto")
    print("2. Mostrar todos los gastos")
    print("3. Calcular gasto total acumulado")
    print("4. Mostrar gasto de mayor valor")
    print("5. Filtrar gastos por monto")
    print("6. Buscar gasto por concepto")
    print("0. Salir")


def main():
    """Ejecuta el menú hasta que el usuario elija salir."""
    gastos = []
    while True:
        mostrar_menu()
        opcion = input("Selecciona una opción: ").strip()

        if opcion == "1":
            registrar_gasto(gastos)
        elif opcion == "2":
            mostrar_gastos(gastos)
        elif opcion == "3":
            total = calcular_total(gastos)
            print(f"Gasto total acumulado: ${total:,.2f}")
        elif opcion == "4":
            mayor = obtener_gasto_mayor(gastos)
            if mayor is None:
                print("No hay gastos registrados.")
            else:
                print(
                    "Gasto de mayor valor: "
                    f"{mayor['concepto']} | ${mayor['monto']:,.2f} | "
                    f"{mayor['categoria']}"
                )
        elif opcion == "5":
            limite = solicitar_monto(
                "Mostrar gastos mayores a ($): ", permitir_cero=True
            )
            encontrados = filtrar_gastos_por_monto(gastos, limite)
            if encontrados:
                mostrar_gastos(encontrados, "Gastos superiores al monto indicado")
            else:
                print("No se encontraron gastos que superen ese monto.")
        elif opcion == "6":
            texto = solicitar_texto("Palabra o concepto a buscar: ")
            encontrados = buscar_gastos_por_concepto(gastos, texto)
            if encontrados:
                mostrar_gastos(encontrados, "Resultados de búsqueda")
            else:
                print("No se encontraron gastos con ese concepto.")
        elif opcion == "0":
            print("Hasta luego.")
            break
        else:
            print("Opción inválida. Selecciona una opción del menú.")


if __name__ == "__main__":
    main()
