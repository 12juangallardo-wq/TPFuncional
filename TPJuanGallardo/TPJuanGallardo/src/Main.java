import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Alumno> alumnos = List.of(
                new Alumno("Mateo", 8.5, "Java"),
                new Alumno("Lucía", 6.0, "Java"),
                new Alumno("Juan", 9.0, "Python"),
                new Alumno("Sofía", 7.5, "Java"),
                new Alumno("Pedro", 5.0, "Python"),
                new Alumno("Ana", 10.0, "C#")
        );

        // 1. Nombres de los alumnos aprobados (nota ≥ 7), en mayúsculas y ordenados
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .toList();
        System.out.println("Alumnos aprobados (mayúsculas y ordenados): " + aprobados);

        // 2. Promedio general de notas
        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.println("Promedio general de notas: " + promedioGeneral);

        // 3. Agrupar alumnos por curso
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        System.out.println("\nAlumnos agrupados por curso:");
        alumnosPorCurso.forEach((curso, lista) -> {
            System.out.println(curso + ": " + lista);
        });

        // 4. Los 3 mejores promedios
        List<Alumno> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .toList();
        System.out.println("\nTop 3 alumnos por nota: " + top3);
        // Crear lista de productos
        List<Producto> productos = List.of(
                new Producto("Laptop", "Electrónica", 1200, 10),
                new Producto("Mouse", "Electrónica", 25, 50),
                new Producto("Teclado", "Electrónica", 45, 30),
                new Producto("Silla", "Muebles", 150, 15),
                new Producto("Mesa", "Muebles", 300, 5),
                new Producto("Libro", "Libros", 80, 100)
        );

        // 1. Productos con precio > 100, orden descendente
        List<Producto> caros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .toList();
        System.out.println("Productos caros: " + caros);

        // 2. Agrupar por categoría y calcular stock total
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        System.out.println("Stock por categoría: " + stockPorCategoria);

        // 3. Generar String con nombre y precio
        String listaProductos = productos.stream()
                .map(p -> p.getNombre() + ": " + p.getPrecio())
                .collect(Collectors.joining("; "));
        System.out.println("Lista de productos: " + listaProductos);

        // 4a. Promedio general
        double promedioGeneral2 = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println("Promedio general de precio: " + promedioGeneral2);

        // 4b. Promedio por categoría
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("Promedio por categoría: " + promedioPorCategoria);
        // Crear lista de libros
        List<Libro> biblioteca = List.of(
                new Libro("Java Avanzado", "Mateo Pérez", 450, 1500),
                new Libro("Python Básico", "Lucía Gómez", 250, 800),
                new Libro("Ciencia de Datos", "Juan López", 320, 1200),
                new Libro("Historia Universal", "Sofía Torres", 500, 1000),
                new Libro("Algoritmos", "Pedro Ruiz", 280, 900),
                new Libro("Inteligencia Artificial", "Ana Fernández", 350, 1800)
        );

        // 1. Títulos de libros con más de 300 páginas, ordenados alfabéticamente
        List<String> titulosLargos = biblioteca.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .toList();
        System.out.println("Libros con más de 300 páginas: " + titulosLargos);

        // 2. Promedio de páginas de todos los libros
        double promedioPaginas = biblioteca.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.println("Promedio de páginas: " + promedioPaginas);

        // 3. Agrupar libros por autor y contar cuántos tiene cada uno
        Map<String, Long> librosPorAutor = biblioteca.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        System.out.println("Cantidad de libros por autor: " + librosPorAutor);

        // 4. Obtener el libro más caro
        Libro libroMasCaro = biblioteca.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .orElse(null);
        System.out.println("Libro más caro: " + libroMasCaro);
        // Crear lista de empleados
        List<Empleado> personal = List.of(
                new Empleado("Mateo", "TI", 2500, 28),
                new Empleado("Lucía", "TI", 1800, 35),
                new Empleado("Juan", "Ventas", 2200, 30),
                new Empleado("Sofía", "Ventas", 1900, 25),
                new Empleado("Pedro", "Marketing", 2100, 24),
                new Empleado("Ana", "Marketing", 1700, 27)
        );

        // 1. Empleados con salario > 2000, ordenados por salario descendente
        List<Empleado> altosSalarios = personal.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .toList();
        System.out.println("Empleados con salario > 2000: " + altosSalarios);

        // 2. Salario promedio general
        double promedioSalario = personal.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Salario promedio general: " + promedioSalario);

        // 3. Agrupar por departamento y sumar salarios
        Map<String, Double> sumaSalariosPorDepto = personal.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        System.out.println("Suma de salarios por departamento: " + sumaSalariosPorDepto);

        // 4. Nombres de los 2 empleados más jóvenes
        List<String> masJovenes = personal.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .toList();
        System.out.println("2 empleados más jóvenes: " + masJovenes);
    }
    }
