package com.victor; // Asegúrate de que este sea tu paquete



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    private static final String ARCHIVO_JSON = "productos.json";
    
    // 1. Aquí aplicamos el Paso 2: El Diccionario Anidado
    private static Map<String, Map<String, Object>> diccionario = new HashMap<>();
    
    // Instancia de Jackson para manejar el JSON
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        cargarDatos();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ V2 (Diccionarios Anidados y Jackson) ---");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Precio: ");
                double precio = Double.parseDouble(scanner.nextLine());

                // 2. Creamos el diccionario interno (los atributos)
                Map<String, Object> atributos = new HashMap<>();
                atributos.put("nombre", nombre);
                atributos.put("precio", precio);

                // 3. Lo guardamos en el diccionario principal usando el ID
                diccionario.put(id, atributos);
                
                guardarDatos();
                System.out.println("✅ Guardado en diccionario anidado.");
            } else if (opcion.equals("2")) {
                if (diccionario.isEmpty()) {
                    System.out.println("No hay datos.");
                } else {
                    // Imprimimos la estructura anidada
                    for (Map.Entry<String, Map<String, Object>> entry : diccionario.entrySet()) {
                        System.out.println("ID: " + entry.getKey() + " -> Datos: " + entry.getValue());
                    }
                }
            } else if (opcion.equals("3")) {
                continuar = false;
            }
        }
        scanner.close();
    }

    private static void guardarDatos() {
        try {
            // Jackson guarda el diccionario anidado con formato bonito (pretty printer)
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARCHIVO_JSON), diccionario);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void cargarDatos() {
        File archivo = new File(ARCHIVO_JSON);
        if (archivo.exists()) {
            try {
                // Jackson lee el JSON y reconstruye el Map anidado
                diccionario = mapper.readValue(archivo, new TypeReference<Map<String, Map<String, Object>>>() {});
            } catch (IOException e) {
                System.out.println("Error al cargar: " + e.getMessage());
            }
        }
    }
}