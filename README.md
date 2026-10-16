# Seguimiento de Paradigmas de Programación

## Descripción

Proyecto académico que implementa un sistema de control de gastos personales mediante diferentes paradigmas de programación. Su propósito es comparar distintas formas de resolver un mismo problema utilizando Java y herramientas de control de versiones.

## Objetivo

Desarrollar y analizar soluciones para la gestión de gastos personales, aplicando los principios de diferentes paradigmas de programación y promoviendo el trabajo colaborativo con Git y GitHub.

## Paradigma funcional

La implementación funcional se encuentra en `src/funcional/` y utiliza características de Java para procesar los gastos mediante operaciones que evitan modificar directamente las listas originales.

### Funcionalidades

1. Registrar gastos con categoría, concepto y monto.
2. Mostrar el listado de gastos.
3. Calcular el total acumulado.
4. Identificar el gasto de mayor valor.
5. Filtrar gastos superiores a un monto determinado.
6. Buscar gastos por concepto, ignorando mayúsculas y minúsculas.

### Archivos principales

- `Categoria.java`: define las categorías disponibles.
- `Gasto.java`: representa un gasto mediante un `record` y valida sus datos.
- `OperacionesGastos.java`: contiene las operaciones funcionales.
- `MainFuncional.java`: permite interactuar con el sistema mediante un menú.
- `test/OperacionesGastosTest.java`: contiene pruebas de las operaciones funcionales.

## Tecnologías

- Java 21.
- Git.
- GitHub.
- PowerShell para ejecutar los comandos en Windows.

## Compilación y ejecución

Desde la carpeta raíz del repositorio, compila los archivos del paradigma funcional:

```powershell
javac -encoding UTF-8 -d out src\funcional\Categoria.java src\funcional\Gasto.java src\funcional\OperacionesGastos.java src\funcional\MainFuncional.java
```

Ejecuta el programa:

```powershell
java -cp out funcional.MainFuncional
```

## Ejecución de las pruebas

Después de compilar la aplicación, compila las pruebas:

```powershell
javac -encoding UTF-8 -cp out -d out test\OperacionesGastosTest.java
```

Ejecuta las pruebas:

```powershell
java -cp out OperacionesGastosTest
```

Las pruebas verifican las seis operaciones principales y algunos casos de búsqueda vacía o nula.

## Control de versiones

El proyecto utiliza ramas para organizar el trabajo de cada contribución y commits para registrar los cambios. La implementación funcional se desarrolla en la rama `feature/paradigma-funcional`.

