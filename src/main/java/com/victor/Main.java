package com.victor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // Diccionario principal en memoria
    private static Map<String, Map<String, Object>> diccionario = new HashMap<>();

    public static void main(String[] args) {
        // Inicialización y carga de datos
        diccionario = GestorDatos.cargarDatosSeguro();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // 1. Menú interactivo estructurado con un ciclo while
        while (continuar) {
            System.out.println("\n=== ⚙️ SISTEMA DE GESTIÓN V4 (Vista Modular) ===");
            System.out.println("1. Agregar un nuevo registro");
            System.out.println("2. Ver todos los registros");
            System.out.println("3. Guardar y Salir");
            System.out.print("👉 Elige una opción: ");

            // 2. Captura de datos usando Scanner
            String opcion = scanner.nextLine();

            // 3. Estructura de control (switch) y delegación a funciones
            switch (opcion) {
                case "1":
                    agregarRegistro(scanner); // Llamado a método
                    break;
                case "2":
                    mostrarRegistros();       // Llamado a método
                    break;
                case "3":
                    System.out.println("💾 Guardando datos y cerrando el sistema...");
                    continuar = false;        // Rompe el ciclo while
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta nuevamente.");
                    break;
            }
        }
        scanner.close();
    }

    // --- Métodos Delegados (Funciones modulares) ---

    private static void agregarRegistro(Scanner scanner) {
        System.out.print("ID: ");
        String id = scanner.nextLine();

        // Salida temprana si el ID ya existe
        if (diccionario.containsKey(id)) {
            System.out.println("⚠️ Ese ID ya existe en el sistema.");
            return; 
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: ");
        try {
            // Prevención de errores al capturar números (Buenas prácticas)
            double precio = Double.parseDouble(scanner.nextLine());

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("nombre", nombre);
            atributos.put("precio", precio);

            diccionario.put(id, atributos);
            GestorDatos.guardarDatos(diccionario);
            System.out.println("✅ Producto guardado correctamente.");
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El precio debe ser un número válido.");
        }
    }

    private static void mostrarRegistros() {
        if (diccionario.isEmpty()) {
            System.out.println("📂 No hay datos para mostrar.");
        } else {
            System.out.println("\n--- Lista de Registros ---");
            for (Map.Entry<String, Map<String, Object>> entry : diccionario.entrySet()) {
                System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}