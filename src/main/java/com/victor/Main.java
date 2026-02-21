package com.victor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // Diccionario principal en memoria
    private static Map<String, Map<String, Object>> diccionario = new HashMap<>();

    public static void main(String[] args) {
        // 1. Cargamos los datos usando la nueva clase GestorDatos
        diccionario = GestorDatos.cargarDatosSeguro();
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ V3 (Persistencia Separada) ---");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                System.out.print("ID: ");
                String id = scanner.nextLine();
                
                // Evitar duplicados
                if (diccionario.containsKey(id)) {
                    System.out.println("⚠️ Ese ID ya existe.");
                    continue;
                }

                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Precio: ");
                double precio = Double.parseDouble(scanner.nextLine());

                // Diccionario interno
                Map<String, Object> atributos = new HashMap<>();
                atributos.put("nombre", nombre);
                atributos.put("precio", precio);

                // Insertar en diccionario principal
                diccionario.put(id, atributos);
                
                // 2. Guardamos usando la nueva clase
                GestorDatos.guardarDatos(diccionario);
                System.out.println("✅ Producto guardado correctamente.");

            } else if (opcion.equals("2")) {
                if (diccionario.isEmpty()) {
                    System.out.println("📂 No hay datos.");
                } else {
                    for (Map.Entry<String, Map<String, Object>> entry : diccionario.entrySet()) {
                        System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
                    }
                }
            } else if (opcion.equals("3")) {
                continuar = false;
            }
        }
        scanner.close();
    }
}