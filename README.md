# seguimiento-paradigmas-gastos
Sistema de control de gastos personales implementado mediante diferentes paradigmas de programación, aplicando trabajo colaborativo con Git y GitHub.

<b> Paradigma de  POO</b><br>
#SeguimientoParadigmas: Sistema de Control de Gastos Personales

Actividad de seguimiento en **Java** que resuelve un mismo problema (gestión de gastos personales) aplicando distintos paradigmas de programación, con trabajo colaborativo mediante **Git y GitHub**.

## Objetivo

Analizar, diseñar e implementar una misma solución de software con los paradigmas Estructurado, Orientado a Objetos, Orientado a Eventos, Declarativo y Funcional, comparando sus características, ventajas y diferencias, y aplicando buenas prácticas de control de versiones (feature branching, Pull Requests y code review).

## Funcionalidades

Todas las versiones implementan las mismas 6 funcionalidades:

1. **Registrar gasto:** captura categoría, concepto (descripción) y monto ($).
2. **Mostrar listado de gastos:** presenta todos los gastos registrados.
3. **Calcular gasto total acumulado:** suma el monto de todos los gastos.
4. **Mostrar el gasto de mayor valor:** muestra el registro con el monto más alto.
5. **Mostrar gastos mayores a un monto:** filtra los gastos que superan un umbral ingresado.
6. **Buscar gasto por concepto:** busca por subcadena en la descripción (sin distinguir mayúsculas).

Categorías disponibles: `ALIMENTACION`, `TRANSPORTE`, `SERVICIOS`, `ENTRETENIMIENTO`.

## Paradigmas y ramas

| Paradigma | Rama | Estado |
|-----------|------|--------|
| Estructurado | `feature/paradigma-estructurado` | Pendiente |
| Orientado a Objetos (POO) | `feature/paradigma-poo` | Implementado |
| Orientado a Eventos (GUI) | `feature/paradigma-eventos` | Pendiente |
| Declarativo (SQL) | `feature/paradigma-declarativo` | Pendiente |
| Funcional | `feature/paradigma-funcional` | Pendiente |

> Actualizar la columna *Estado* a medida que se integren las ramas a `develop`.

## Paradigma Orientado a Objetos (rama `feature/paradigma-poo`)

### Conceptos aplicados

- **Clases e instanciación de objetos:** cada gasto es un objeto `RegistroGasto`.
- **Encapsulamiento:** atributos `private` con getters y setters que validan los datos (categoría no nula, descripción no vacía, monto finito y mayor que cero).
- **Composición:** `GestorGastos` *tiene* una lista de `RegistroGasto` y es el único que la modifica.
- **Métodos de instancia:** agregar, listar, calcular total, obtener el mayor, filtrar y buscar.
- **Enumeración:** `Categoria` evita valores inválidos de categoría.

### Clases

| Clase | Responsabilidad |
|-------|-----------------|
| `Categoria` (enum) | Define las categorías permitidas. |
| `RegistroGasto` | Representa un gasto con categoría, descripción y monto encapsulados. |
| `GestorGastos` | Administra la colección de gastos y contiene la lógica de las 6 funcionalidades. |
| `Main` | Menú interactivo en consola; solo interactúa con el usuario y delega la lógica al gestor. |

## Estructura del proyecto

```
seguimiento-paradigmas-gastos
├── src
│   ├── clases
│   │   ├── Categoria.java
│   │   ├── RegistroGasto.java
│   │   └── GestorGastos.java
│   └── Main.java
├── .gitignore
└── README.md
```

## Requisitos

- JDK 17 o superior (el código usa *text blocks* y `switch` con flechas).
- IntelliJ IDEA (recomendado) o cualquier IDE/terminal con `javac`.

## Cómo ejecutar

### Desde IntelliJ IDEA

1. Abrir la carpeta del proyecto.
2. Verificar que `src` esté marcada como *Sources Root* (clic derecho → Mark Directory as → Sources Root).
3. Ejecutar la clase `Main`.

### Desde la terminal

```bash
# En la raíz del proyecto
javac -d out src/clases/*.java src/Main.java
java -cp out Main
```

## Ejemplo de uso

```
=============================
Buenos Días, ¿Qué desea Hacer?
=============================

1. Registrar Gasto
2. Mostrar lista de Gastos
3. Calcular gasto total acumulado
4. Mostrar el gasto de mayor valor
5. Mostrar los gastos mayores a: X
6. Buscar gasto por concepto
0. Salir
```

## Flujo de trabajo con Git

```
main (producción) ------------------------------------------> [Tag v1.0]
       \                                                  /
develop (integración) ------------------------------------
       \              \              \              \
        feature/...   feature/...    feature/...    feature/...
```

- `main`: código estable y listo para la entrega final.
- `develop`: rama de integración donde convergen las funcionalidades terminadas.
- `feature/*`: una rama por paradigma, creada a partir de `develop`.
- La integración `feature/*` → `develop` se hace **solo mediante Pull Request**, con la aprobación de al menos otro integrante.

### Convención de commits

Formato imperativo con prefijo:

- `feat:` nueva funcionalidad (ej. `feat: agrega clase GestorGastos en POO`)
- `fix:` corrección de errores (ej. `fix: corrige validación del monto`)
- `docs:` documentación (ej. `docs: actualiza README`)
- `refactor:` mejora interna sin cambiar el comportamiento

## Integrantes

| Nombre | Paradigma a cargo | Usuario de GitHub |
|--------|-------------------|-------------------|
| _Nombre 1_ | Orientado a Objetos | _@usuario_ |
| _Nombre 2_ | Estructurado | _@usuario_ |
| _Nombre 3_ | Orientado a Eventos | _@usuario_ |
| _Nombre 4_ | Declarativo | _@usuario_ |
| _Nombre 5_ | Funcional | _@usuario_ |

> Completar con los datos reales del equipo.

## Análisis comparativo de paradigmas

_Completar al finalizar todas las ramas: ventajas, desventajas y diferencias observadas entre los paradigmas al resolver el mismo problema._

## Referencias bibliográficas

_Programming languages : principles and paradigms → Capitulos:12,13,14,15,16._