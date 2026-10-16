# Paradigma estructurado: control de gastos personales

## Descripción

Este programa de consola permite registrar y consultar gastos personales. Está implementado en Python mediante programación estructurada: organiza el trabajo en funciones que reciben parámetros y utiliza condicionales, ciclos, una lista y diccionarios sencillos. No define clases personalizadas ni requiere dependencias externas.

Los datos se conservan en memoria durante la ejecución y se pierden al cerrar el programa.

## Objetivo

Aplicar el paradigma de programación estructurada a un problema cotidiano, separando las tareas en funciones y controlando su ejecución con un menú interactivo.

## Características del paradigma utilizadas

- **Secuencia:** cada operación solicita datos, procesa la información y presenta resultados en orden.
- **Selección:** las estructuras `if`/`elif`/`else` validan datos y determinan la opción elegida.
- **Iteración:** los ciclos `while` mantienen activo el menú y permiten volver a solicitar entradas inválidas; los ciclos `for` recorren los gastos.
- **Descomposición funcional:** cada función realiza una tarea concreta y recibe los datos que necesita mediante parámetros.
- **Estructuras simples:** una lista contiene los gastos y cada gasto se representa con un diccionario de concepto, monto y categoría.

## Funcionalidades

1. Registrar un gasto con concepto, monto y categoría.
2. Mostrar todos los gastos registrados.
3. Calcular el total acumulado.
4. Identificar y mostrar el gasto de mayor valor.
5. Mostrar los gastos cuyo monto sea estrictamente superior a un límite.
6. Buscar gastos por coincidencia parcial en el concepto, sin distinguir mayúsculas y minúsculas.

El programa rechaza montos de gasto iguales a cero, negativos o no numéricos; también solicita nuevamente los campos de texto vacíos. Los montos usados como límite de filtrado pueden ser cero. Se informa claramente cuando todavía no hay gastos y se puede realizar más de una operación antes de salir.

## Ejecución

Desde la raíz del repositorio, ejecuta:

```bash
python paradigma-estructurado/main.py
```

En algunos equipos el comando disponible puede ser `python3` o el lanzador `py -3`. No es necesario instalar paquetes.

## Funciones principales

- `solicitar_texto(mensaje)` y `solicitar_monto(mensaje, permitir_cero=False)`: validan las entradas del usuario sin finalizar el programa ante un dato inválido.
- `registrar_gasto(gastos)`: crea un diccionario de gasto y lo agrega a la lista recibida.
- `mostrar_gastos(gastos, titulo)`: presenta los gastos y controla el caso de una lista vacía.
- `calcular_total(gastos)`: recorre la lista y devuelve la suma de los montos.
- `obtener_gasto_mayor(gastos)`: devuelve el gasto mayor o `None` cuando no hay gastos.
- `filtrar_gastos_por_monto(gastos, limite)`: devuelve los gastos que superan el límite.
- `buscar_gastos_por_concepto(gastos, texto)`: devuelve coincidencias parciales sin diferenciar mayúsculas.
- `mostrar_menu()` y `main()`: presentan las opciones y mantienen el ciclo principal de interacción.

## Ejemplo de uso

1. Selecciona `1` y registra `Almuerzo`, `15000` y `Alimentación`.
2. Registra otro gasto: `Bus`, `3000` y `Transporte`.
3. Selecciona `3`: el total mostrado será `$18,000.00`.
4. Selecciona `4`: el gasto mayor será `Almuerzo`.
5. Selecciona `5` e ingresa `5000`: aparecerá `Almuerzo`.
6. Selecciona `6` y escribe `alm`: se encontrará `Almuerzo`.
7. Selecciona `0` para salir.

## Consulta bibliográfica del paradigma

**Estado: pendiente de consulta real.** Este espacio se completará después de consultar la biblioteca institucional o una base de datos bibliográfica. No se ha registrado aquí una consulta ni una fuente que todavía no se hayan verificado.

- Biblioteca institucional o base consultada: [completar tras la consulta]
- Términos de búsqueda: [completar tras la consulta]
- Referencia bibliográfica verificada: [completar con los datos de la fuente]
- Fecha de consulta: [completar tras la consulta]

## Trabajo colaborativo

El trabajo de este paradigma corresponde a la rama `feature/paradigma-estructurado`, creada a partir de `develop`. La integración deberá solicitarse mediante un Pull Request hacia `develop` y ser revisada y aprobada por otro integrante antes del merge. El commit, push y Pull Request quedan pendientes de autorización y realización; no se presentan como actividades ya completadas.
