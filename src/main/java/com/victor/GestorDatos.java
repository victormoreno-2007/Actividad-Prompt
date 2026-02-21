package com.victor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestorDatos {
    private static final String ARCHIVO = "productos.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    // 1. Método para guardar (Equivalente al toJson)
    public static void guardarDatos(Map<String, Map<String, Object>> diccionario) {
        try {
            // writeValue abre el archivo, escribe el JSON formateado y lo cierra de forma segura
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARCHIVO), diccionario);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar en JSON: " + e.getMessage());
        }
    }

    // 2 y 3. Método para cargar con comprobación de existencia (Arranque Seguro)
    public static Map<String, Map<String, Object>> cargarDatosSeguro() {
        File archivo = new File(ARCHIVO);
        
        // Comprobación: Si es la primera vez que se abre el programa y no hay archivo
        if (!archivo.exists()) {
            System.out.println("⚠️ Archivo no encontrado. Iniciando con diccionario vacío.");
            return new HashMap<>(); // Retorna un mapa vacío para que el programa no colapse
        }

        try {
            // readValue lee el archivo y reconstruye la estructura anidada (Equivalente a fromJson)
            return mapper.readValue(archivo, new TypeReference<Map<String, Map<String, Object>>>() {});
        } catch (IOException e) {
            System.out.println("❌ Error al leer el JSON: " + e.getMessage());
            return new HashMap<>();
        }
    }
}