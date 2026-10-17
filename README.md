# Seguimiento de Paradigmas de Programación

Sistema de control de gastos personales implementado mediante diferentes paradigmas de programación, aplicando trabajo colaborativo con Git y GitHub.

## Objetivo

Analizar, diseñar e implementar una solución de software para la gestión de gastos personales mediante los paradigmas estructurado, orientado a objetos, orientado a eventos, declarativo y funcional, comparando sus características, ventajas y diferencias mediante un mismo problema práctico.

La actividad también aplica buenas prácticas de control de versiones, como el uso de ramas, commits descriptivos, Pull Requests y revisión de código.

## Funcionalidades

Todas las implementaciones deben contemplar las siguientes seis funcionalidades:

1. **Registrar gasto:** capturar la categoría, la descripción y el monto.
2. **Mostrar listado de gastos:** presentar todos los gastos registrados.
3. **Calcular gasto total acumulado:** sumar los montos de los gastos.
4. **Mostrar el gasto de mayor valor:** identificar el gasto con el monto más alto.
5. **Filtrar gastos por monto:** mostrar los gastos que superen un umbral especificado.
6. **Buscar gasto por concepto:** localizar gastos cuya descripción contenga una palabra o subcadena, sin distinguir mayúsculas y minúsculas.

**Categorías iniciales:** `ALIMENTACION`, `TRANSPORTE`, `SERVICIOS` y `ENTRETENIMIENTO`.

## Paradigmas y ramas

| Paradigma | Rama | Estado |
|---|---|---|
| Estructurado | `feature/paradigma-estructurado` | Implementado |
| Orientado a Objetos (POO) | `feature/paradigma-poo` | Implementado |
| Declarativo (SQL) | `feature/paradigma-declarativo` | Implementado |
| Funcional | `feature/paradigma-funcional` | Implementado |

Los estados deben actualizarse a medida que cada implementación se complete, pruebe e integre en `develop`.

## Paradigma orientado a objetos (POO)

La implementación del paradigma orientado a objetos se desarrolla en la rama `feature/paradigma-poo` mediante clases, objetos, encapsulamiento y composición.

### Conceptos aplicados

- **Clases e instanciación:** cada gasto se representa mediante un objeto de la clase `RegistroGasto`.
- **Encapsulamiento:** los atributos se declaran privados y se accede a ellos mediante getters y setters.
- **Validación:** se verifica que la categoría sea válida, la descripción no esté vacía y el monto sea finito y mayor que cero.
- **Composición:** `GestorGastos` administra una colección de objetos `RegistroGasto`.
- **Métodos de instancia:** se implementan operaciones para registrar, listar, calcular totales, obtener el gasto de mayor valor, filtrar y buscar gastos.
- **Enumeraciones:** `Categoria` define las categorías permitidas.

### Clases principales

| Clase | Responsabilidad |
|---|---|
| `Categoria` | Enumeración de las categorías de gastos permitidas. |
| `RegistroGasto` | Representa un gasto individual con sus atributos encapsulados. |
| `GestorGastos` | Administra la colección de gastos y ejecuta las operaciones requeridas. |
| `Main` | Presenta el menú de consola, recibe las entradas del usuario y delega las operaciones al gestor. |

### Estructura del proyecto POO

```text
seguimiento-paradigmas-gastos/
├── src/
│   ├── clases/
│   │   ├── Categoria.java
│   │   ├── RegistroGasto.java
│   │   └── GestorGastos.java
│   └── Main.java
├── .gitignore
└── README.md
```

Esta estructura corresponde a la implementación POO. Las demás implementaciones pueden organizarse en carpetas o paquetes independientes, de acuerdo con las decisiones del equipo.

## Tecnologías

- Java.
- JDK 17 o superior para la implementación descrita.
- Git para el control de versiones.
- GitHub para el alojamiento del repositorio y la colaboración.
- IntelliJ IDEA o cualquier entorno compatible con Java.

## Ejecución de la implementación POO

### Desde IntelliJ IDEA

1. Abrir el proyecto.
2. Verificar que `src` esté configurada como carpeta de código fuente (*Sources Root*).
3. Comprobar que los archivos tengan las declaraciones de paquete correspondientes.
4. Ejecutar la clase `Main`.

### Desde la terminal

Desde la raíz del proyecto, ejecutar:

```bash
javac -d out src/clases/*.java src/Main.java
java -cp out Main
```

Este procedimiento supone que los archivos están ubicados en las rutas indicadas y que la clase principal se llama `Main`.

### Ejemplo del menú de consola

```text
=============================
Sistema de Control de Gastos
=============================

1. Registrar gasto
2. Mostrar lista de gastos
3. Calcular gasto total acumulado
4. Mostrar el gasto de mayor valor
5. Mostrar gastos mayores a un monto
6. Buscar gasto por concepto
7. Salir
```

## Estructura de trabajo con Git y GitHub

El proyecto utiliza un flujo de trabajo basado en ramas para separar las implementaciones y facilitar su revisión e integración.

- `main`: contiene la versión estable y final del proyecto.
- `develop`: integra las funcionalidades completadas y revisadas.
- Ramas `feature/*`: permiten desarrollar cada paradigma de manera independiente.

Las ramas de funcionalidad deben crearse a partir de `develop`. La integración de cada rama en `develop` debe realizarse mediante un Pull Request, con revisión y aprobación de otro integrante.

Cuando todas las funcionalidades estén integradas y verificadas, se preparará la versión final en `main` y se identificará con la etiqueta `v1.0`.

### Convención de commits

Se emplearán mensajes claros, descriptivos e imperativos, acompañados de prefijos según el tipo de cambio:

- `feat:` incorpora una funcionalidad.
- `fix:` corrige un error.
- `docs:` modifica la documentación.
- `refactor:` mejora la estructura interna sin cambiar el comportamiento esperado.
- `test:` incorpora o modifica pruebas.

Ejemplos:

```text
feat: agrega la clase GestorGastos en POO
fix: corrige la búsqueda por concepto
docs: actualiza la documentación del proyecto
```

## Integrantes y distribución del trabajo

| Integrante | Paradigma asignado |
|---|---|
| Peter Carvajal | Orientado a Objetos |
| Alejandro Ospina | Estructurado |
| Sofía Murillo | Funcional |
| Samuel Velázquez | Declarativo |

La distribución indicada corresponde a las responsabilidades asignadas al equipo. El historial de commits y los Pull Requests permitirán evidenciar las contribuciones individuales.

## Análisis comparativo de paradigmas

Al finalizar las implementaciones, se documentarán las diferencias observadas entre los paradigmas, considerando:

- Organización y legibilidad del código.
- Separación de responsabilidades.
- Gestión y transformación de los datos.
- Facilidad de mantenimiento y ampliación.
- Ventajas y limitaciones de cada enfoque.
- Adecuación de cada paradigma al problema de gestión de gastos personales.

## Referencias bibliográficas

- *Programming Languages: Principles and Paradigms.* Consulta de los capítulos 12, 13, 14, 15 y 16.
- *El Gran Libro de NodeJS.* Autores: Gascon Gonzalez, Eulises. Capítulo: 3.
