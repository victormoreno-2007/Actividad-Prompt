package com.victor;



import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    // Clase interna para el modelo de datos
    static class Producto {
        String id;
        String nombre;
        double precio;

        Producto(String id, String nombre, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
        }
    }

    private static final String ARCHIVO_JSON = "productos.json";
    private static Map<String, Producto> diccionario = new HashMap<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        cargarDatos();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ V1 ---");
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

                diccionario.put(id, new Producto(id, nombre, precio));
                guardarDatos();
                System.out.println("Guardado.");
            } else if (opcion.equals("2")) {
                System.out.println(gson.toJson(diccionario));
            } else if (opcion.equals("3")) {
                continuar = false;
            }
        }
        scanner.close();
    }

    private static void guardarDatos() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(diccionario, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar.");
        }
    }

    private static void cargarDatos() {
        File f = new File(ARCHIVO_JSON);
        if (f.exists()) {
            try (FileReader reader = new FileReader(f)) {
                Type tipo = new TypeToken<HashMap<String, Producto>>(){}.getType();
                diccionario = gson.fromJson(reader, tipo);
            } catch (IOException e) {
                System.out.println("Error al cargar.");
            }
        }
    }
}