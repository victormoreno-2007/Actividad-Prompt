# 🧪 Taller: Construcción de un Programa en Python usando Diccionarios y Persistencia en JSON mediante Prompts

## 📌 Contexto del taller

En el desarrollo de software moderno, saber **formular correctamente un problema** y **guiar a una IA mediante prompts claros** es tan importante como programar. En este taller, el estudiante no solo programará en Python, sino que **aprenderá a diseñar prompts efectivos** para obtener soluciones correctas, mantenibles y bien estructuradas.

------

## 🎯 Objetivo general

Que el estudiante sea capaz de:

- Diseñar **prompts claros y precisos**
- Construir un programa en **Python**
- Usar **diccionarios** como estructura principal de datos
- Implementar **persistencia en archivos JSON**
- Aplicar **manejo básico de errores**
- Comprender la relación entre *prompt → solución generada*

------

## 👥 Público objetivo

- Estudiantes de programación (nivel básico – intermedio)
- Cursos introductorios de Python
- Talleres de Prompt Engineering aplicado a código

------

## 🛠️ Requisitos técnicos

- Python 3.10+
- Editor de texto o IDE (VS Code recomendado)
- Conocimientos previos:
  - Diccionarios en Python
  - Funciones
  - Uso básico de archivos

------

## 📦 Problema a resolver

Se debe construir un **programa de consola en java** que permita gestionar información usando **diccionarios**, con las siguientes características:

### 📂 Dominio del problema (ejemplo)

Sistema de gestión de **contactos** / **productos** / **estudiantes**
 *(el docente puede elegir uno)*

Cada registro debe contener al menos:

- Identificador único
- Nombre
- Un atributo adicional (correo, precio, edad, etc.)

La información debe:

- Guardarse en un **diccionario**
- Persistirse en un **archivo JSON**
- Recuperarse al reiniciar el programa



## 🤖 composición del proyecto



- **Propósito:** Es una aplicación interactiva de consola en Java para administrar un inventario básico de productos.

- producto y el *valor* es el objeto **Producto** (que contiene su nombre y precio).

- **Funciones principales:** Tiene un menú que permite **agregar** nuevos registros (validando que el ID no se repita) y **listar** todos los productos almacenados.

- **Almacenamiento (Persistencia):** Es la parte clave. Al iniciar, el programa busca y carga un archivo local llamado `productos.json`. Cada vez que registras un producto nuevo, el sistema actualiza ese archivo automáticamente usando la librería **Gson**. Esto garantiza que la información se recupere intacta cada vez que reinicias el programa.



------

## 🧩 Estructura del taller (por retos)

------

## 🧠 Reto 1: Comprensión del problema mediante prompt

### 📌 Instrucción al estudiante

Redacta un prompt que le pida a una IA **explicar el problema** y **proponer una solución general en Python**.

### 🧾 Prompt esperado (ejemplo)

```
Actúa como un Arquitecto de Software Senior experto en Java. >
Tu objetivo es analizar un programa de consola básico en Java diseñado para gestionar un inventario de productos. Este programa almacena actualmente la información en la memoria RAM usando un diccionario (HashMap) y asegura la persistencia guardando y recuperando los datos en un archivo JSON local.

Esta explicación va dirigida a un estudiante universitario de tecnología que necesita comprender cómo pasar de un script funcional a un diseño de software profesional, escalable y mantenible.

Restricciones: > - Céntrate en la arquitectura y el diseño, no expliques la sintaxis básica de Java (como qué es un String o un int).

No generes el código fuente completo del sistema; enfócate en explicar los conceptos y la estructura.

Organiza tu respuesta estrictamente en el siguiente formato y orden:

Explicación del problema (Analiza por qué tener toda la lógica mezclada en una sola clase es un problema).

Propuesta de la solución general en Java (Explica la arquitectura por capas y el patrón de diseño adecuado).

Explicación del uso de diccionarios (Cómo y por qué se usa el HashMap aquí).

Explicación de lectura/escritura de JSON (Cómo interactúa la memoria con el archivo de texto).

Flujo general del programa (Paso a paso lógico de la ejecución).

Criterios de calidad: > - Exige la aplicación de buenas prácticas de programación (específicamente el Principio de Responsabilidad Única - SRP).

La explicación debe ser técnica, pero altamente didáctica y fácil de digerir. Utiliza viñetas para facilitar la lectura.
```

### EXPLICACIÓN


```
Sirve para que la IA analice un código funcional pero desordenado, identifique el problema del acoplamiento (violación del Principio de Responsabilidad Única) y proponga una solución estructurada usando el patrón DAO.
```






### ✅ Resultado esperado

- Explicación del uso de diccionarios
- Explicación de lectura/escritura de JSON
- Flujo general del programa

```


1. Explicación del problema arquitectónico
En un sistema básico donde toda la lógica reside en una sola clase (usualmente el Main), nos enfrentamos a un problema severo de acoplamiento fuerte (Tight Coupling) y una violación directa del Principio de Responsabilidad Única (SRP) de SOLID.

Tener el código mezclado significa que una misma clase está:

Gestionando las entradas y salidas de la consola (UI).

Validando las reglas de negocio (como asegurar que un ID no se repita).

Manejando el acceso a los datos y la persistencia (escribiendo y leyendo el archivo JSON).

¿Por qué es un problema? Si el día de mañana el requerimiento cambia y debes conectar el sistema a una base de datos real (como MySQL) o cambiar la consola por una interfaz gráfica web, tendrías que reescribir y desarmar la clase entera. El código se vuelve frágil, difícil de leer y casi imposible de mantener en equipos de desarrollo.

2. Propuesta de la solución general en Java
Para transformar este script en un software profesional y escalable, la solución es implementar una Arquitectura por Capas apoyada en el Patrón de Diseño DAO (Data Access Object). Esto divide el sistema en responsabilidades independientes:

Capa de Modelo (Model): Entidades puras (ej. la clase Producto con sus atributos y métodos de acceso), sin ninguna lógica de cómo se guardan o muestran.

Capa de Acceso a Datos (DAO): Una interfaz (ProductoDAO) que define los contratos (guardar, buscar, eliminar). Luego, una implementación específica (ProductoDAOJson) que se encarga únicamente de interactuar con el archivo JSON. Si mañana usas una base de datos, solo creas un ProductoDAOMySql sin tocar el resto del sistema.

Capa de Servicio (Service / Controller): Contiene la lógica de negocio. Recibe los datos de la vista, los valida y los envía al DAO.

Capa de Vista (View): Exclusivamente maneja la interacción con el usuario (la consola y los menús).

3. Explicación del uso de diccionarios (HashMap)
En este contexto, el diccionario actúa como nuestra base de datos en memoria (RAM) durante la ejecución del programa. En Java, la implementación ideal es un HashMap<String, Producto>.

Estructura Clave-Valor: El diccionario almacena registros donde la Clave (Key) es el identificador único (ID) del producto, y el Valor (Value) es el objeto Producto completo en sí.

Eficiencia (O(1)): A diferencia de un ArrayList donde para buscar un producto tendrías que recorrer la lista elemento por elemento, un HashMap utiliza una función hash para ir directamente a la posición de memoria del registro. Esto hace que las búsquedas, inserciones y validaciones de duplicados sean instantáneas, sin importar si tienes 10 o 10.000 registros.

Integridad de datos: Como las claves en un HashMap son irrepetibles por naturaleza, el diccionario nos ayuda automáticamente a evitar IDs duplicados en el inventario.

4. Explicación de lectura/escritura de JSON
El archivo JSON es nuestro medio de persistencia. Como la memoria RAM es volátil (se borra al cerrar el programa), necesitamos un mecanismo para traducir los objetos de Java a texto plano y viceversa.

Escritura (Serialización): Cuando se agrega o modifica un producto en el diccionario, utilizamos una librería (como Gson o Jackson). Esta herramienta toma el HashMap de la memoria y lo "traduce" a un formato de texto estructurado en llaves y corchetes (JSON). Luego, mediante clases de Java como FileWriter, ese texto se guarda físicamente en el disco duro.

Lectura (Deserialización): Es el proceso inverso. Al iniciar la aplicación, Java usa FileReader para leer el texto del archivo JSON. La librería parsea ese texto, reconstruye las instancias de la clase Producto y vuelve a poblar el HashMap en la memoria RAM, dejando el sistema exactamente como estaba antes de cerrarse.

5. Flujo general del programa
El ciclo de ejecución de la aplicación, aplicando estas buenas prácticas, sigue esta secuencia lógica:

Inicialización (Boot & Load): El programa arranca y la capa Service le pide al DAO que cargue los datos. El DAO lee el archivo productos.json, deserializa la información y carga el HashMap en memoria. Si el archivo no existe, arranca con un diccionario vacío.

Interacción de Usuario (View): La consola despliega el menú principal. El usuario selecciona, por ejemplo, "Agregar Producto" e ingresa los datos.

Procesamiento y Validación (Service): La vista envía los datos capturados al Service. Este verifica que los datos sean correctos (ej. el precio es mayor a cero) y revisa en el diccionario que el ID no exista previamente.

Actualización en Memoria y Persistencia (DAO): Si todo es válido, el Service crea el objeto Producto y le ordena al DAO guardarlo. El DAO primero lo inserta en el HashMap (memoria) y, acto seguido, serializa todo el diccionario actualizado sobreescribiendo el archivo productos.json (disco duro).

Retroalimentación: La vista le confirma al usuario que la operación fue exitosa y vuelve a mostrar el menú principal, repitiendo el ciclo hasta que el usuario decida salir.






```



------

## 🧠 Reto 2: Prompt para estructura de datos

### 📌 Instrucción

Crea un prompt que solicite a la IA **definir la estructura del diccionario principal**.

### 🧾 Prompt esperado

```
1. Rol: Actúa como un Arquitecto de Software Senior experto en Java.
2. Objetivo: Definir la estructura de datos principal del sistema basándose estrictamente en un diccionario anidado para gestionar los registros.
3. Contexto: Dirigido a estudiantes que están aprendiendo a modelar estructuras de datos en memoria para su posterior exportación a JSON.
4. Restricciones: No utilices clases de modelo complejas. Explica la estructura puramente en términos de diccionarios (Mapas), sus claves y sus niveles de anidamiento.
5. Formato: Organiza la respuesta desarrollando obligatoriamente estos tres puntos:

Identificación del ID como clave principal.

Estructura del diccionario anidado.

Claves bien definidas (internas).
6. Criterios de Calidad: Muestra cómo se inicializa esta estructura en Java y proporciona una representación visual clara de cómo se vería la estructura anidada.
```

### EXPLCIACIÓN

```
Instruye a la IA para definir estrictamente el "corazón" del almacenamiento temporal: un diccionario (`HashMap`) donde la clave principal es el ID único (para evitar duplicados y acelerar búsquedas) y el valor es el objeto encapsulado.
```



### ✅ Resultado esperado

- Diccionario anidado
- Claves bien definidas
- Identificación del `id` como clave principal

```

Para modelar este sistema de gestión sin depender de clases complejas y prepararlo para su persistencia en JSON, la mejor estrategia es utilizar una estructura de diccionario anidado (un diccionario dentro de otro diccionario). En Java, esto se logra anidando interfaces Map.

A continuación, se detalla la estructura:

1. Identificación del ID como clave principal
El nivel más alto (o externo) de nuestra estructura debe garantizar que ningún registro se duplique y que las búsquedas sean instantáneas. Para esto, la clave principal del diccionario será siempre el Identificador Único (ID) del producto, estudiante o contacto.
Al usar el ID como llave de acceso primario, evitamos tener que recorrer listas; simplemente le pedimos al sistema: "Dame los datos del ID '123'".

2. Diccionario anidado
El valor asociado a esa clave principal no será un simple texto, sino otro diccionario completo que contendrá los detalles del registro.
En código Java, esta estructura anidada se declara de la siguiente manera:

Java
// Un Mapa donde la Clave es el ID (String), 
// y el Valor es OTRO Mapa con los atributos del registro.
Map<String, Map<String, Object>> diccionarioPrincipal = new HashMap<>();
Esta estructura de "diccionario de diccionarios" es la representación en memoria más fiel a un objeto JSON, lo que facilita enormemente la lectura y escritura del archivo.

3. Claves bien definidas
Para que el diccionario interno (el nivel anidado) sea predecible y no genere errores, las claves de los atributos deben estar estandarizadas y bien definidas en todo el programa.
Cada registro que insertemos en el diccionarioPrincipal debe tener un sub-diccionario con las mismas llaves exactas. Por ejemplo, para un producto:

"id": Almacena el identificador (opcionalmente redundante con la clave principal, pero útil).

"nombre": Almacena el nombre del registro en texto (String).

"precio" (o el atributo adicional elegido): Almacena el valor numérico (Double o Integer).

Representación visual de la estructura anidada:
Si insertamos datos en esta estructura, lógicamente se vería así (y así mismo se guardará en el JSON):

JSON
{
  "P-001": {
    "nombre": "Laptop Asus",
    "precio": 1200.50
  },
  "P-002": {
    "nombre": "Monitor Samsung",
    "precio": 350.00
  }
}
En este ejemplo visual: "P-001" es la clave principal, y el bloque que contiene "nombre" y "precio" con sus respectivos valores conforman el diccionario anidado con claves bien definidas.


```



------

## 🧠 Reto 3: Prompt para persistencia en JSON

### 📌 Instrucción

Diseña un prompt que le pida a la IA **crear funciones para guardar y cargar datos desde un archivo JSON**.

### 🧾 Prompt esperado

```
¡Totalmente de acuerdo! Como pivotamos nuestra arquitectura para usar Jackson (que te solucionó el problema del IDE) y Diccionarios Anidados (como me pediste en el paso 2 para no usar la clase Producto), ese prompt antiguo y su respuesta quedaron desactualizados.

Aquí tienes la corrección exacta de ambos bloques. Puedes copiar y pegar esto directamente en tu documento de entregables para que haya 100% de coherencia en tu proyecto.

📝 El Prompt Corregido (Para tu documento)
1. Rol: Actúa como un Arquitecto de Software Senior experto en Java.
2. Objetivo: Diseñar y explicar los métodos estáticos encargados de la persistencia de datos, permitiendo guardar y cargar un diccionario anidado (Map<String, Map<String, Object>>) desde un archivo JSON utilizando la librería Jackson (ObjectMapper).
3. Contexto: Dirigido a estudiantes de programación en Java que necesitan implementar lectura y escritura de archivos de texto de forma segura y estructurada, sin depender de clases de modelo complejas.
4. Restricciones: No construyas todo el programa. Enfócate exclusivamente en los métodos de serialización y deserialización.
5. Formato: Organiza tu respuesta desarrollando obligatoriamente estos tres puntos con sus respectivos ejemplos de código:

Uso de ObjectMapper.writeValue() (para guardar los datos).

Uso de ObjectMapper.readValue() junto con TypeReference (para cargar los datos).

Manejo de IOException y comprobación de existencia con File.exists() (para un arranque seguro).
6. Criterios de Calidad: Asegúrate de explicar cómo Jackson maneja el cierre de archivos automáticamente al pasarle un objeto File, garantizando buenas prácticas y un código limpio.
```



### EXPLICACIÓN 

```
Se enfoca exclusivamente en la lectura y escritura de archivos. Obliga a la IA a usar Gson de forma segura, incluyendo el manejo de FileNotFoundException para garantizar que el programa no colapse durante su primera ejecución.
```



### ✅ Resultado esperado

- Uso de `json.dump`
- Uso de `json.load`
- Manejo de `FileNotFoundError`

```
Para garantizar que nuestro diccionario de datos persista entre ejecuciones en Java, utilizaremos la librería Jackson para traducir nuestro diccionario anidado (Map) a texto JSON y viceversa.

A continuación, se detalla la implementación lógica y segura para este proceso.

1. Uso de ObjectMapper.writeValue (Guardar datos)
La clase ObjectMapper es el motor principal de Jackson. Su método writeValue() se encarga de la serialización: toma nuestro diccionario de la memoria RAM, lo convierte al formato de texto JSON y lo escribe directamente en el disco duro.

Al pasarle un objeto File, Jackson se encarga internamente de abrir el flujo, escribir los datos y cerrarlo de forma segura, evitando fugas de memoria. Utilizamos writerWithDefaultPrettyPrinter() para que el JSON guardado tenga saltos de línea y sea legible para humanos.

Java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GestorDatos {
    // Instancia reutilizable de Jackson
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void guardarDatos(Map<String, Map<String, Object>> diccionario, String rutaArchivo) {
        try {
            // writeValue guarda el diccionario en el archivo físico
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), diccionario);
            System.out.println("✅ Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }
}
2. Uso de ObjectMapper.readValue (Cargar datos)
El método readValue() realiza la deserialización: lee el texto del archivo JSON y reconstruye el mapa anidado en la memoria RAM.

Dado que en Java los diccionarios usan genéricos complejos (Map<String, Map<String, Object>>), necesitamos usar la clase TypeReference de Jackson. Esto le indica al motor exactamente qué tipo de estructura de datos debe reconstruir a partir del texto plano.

3. Comprobación de existencia para un Arranque Seguro
Existe un punto crítico: la primera vez que se ejecuta el programa. Si intentamos usar readValue() y el archivo datos.json no existe en el disco duro, Java lanzará una excepción y el programa colapsará.

Para solucionar esto, verificamos si el archivo existe usando la clase File antes de intentar leerlo. Si no existe, simplemente devolvemos un diccionario vacío (new HashMap<>()) para que el programa pueda arrancar limpiamente.

Java
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;

// ... (dentro de la misma clase GestorDatos)

    public static Map<String, Map<String, Object>> cargarDatosSeguro(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        
        // Verificamos si el archivo existe (Arranque Seguro)
        if (!archivo.exists()) {
            System.out.println("⚠️ Archivo no encontrado. Iniciando con un diccionario vacío.");
            return new HashMap<>(); // Retornamos diccionario vacío
        }

        try {
            // readValue lee el archivo y retorna el diccionario reconstruido usando TypeReference
            return mapper.readValue(archivo, new TypeReference<Map<String, Map<String, Object>>>() {});
        } catch (IOException e) {
            System.out.println("❌ Error de lectura: " + e.getMessage());
            return new HashMap<>();
        }
    }
¡Con esto ya tienes tanto la instrucción como el resultado final adaptados al 100% a la realidad de tu código fuente actual!
```



------

## 🧠 Reto 4: Prompt para menú interactivo

### 📌 Instrucción

Formula un prompt para crear un **menú de consola** que permita interactuar con el sistema.

### 🧾 Prompt esperado

```

1. Rol: Actúa como un Arquitecto de Software Senior experto en Java.
2. Objetivo: Diseñar y explicar la construcción de un menú interactivo por consola que permita al usuario controlar el flujo del sistema de gestión.
3. Contexto: Dirigido a estudiantes de desarrollo de software que necesitan estructurar la interfaz de usuario (Vista) de una aplicación de consola de forma modular y limpia.
4. Restricciones: No incluyas la lógica interna de los diccionarios o JSON. Enfócate exclusivamente en el bucle principal, la captura de datos y la delegación de responsabilidades.
5. Formato: Organiza tu respuesta desarrollando obligatoriamente estos tres puntos con sus respectivos ejemplos de código en Java:

Menú interactivo estructurado con un ciclo while.

Captura de datos del usuario usando Scanner (equivalente a input en otros lenguajes).

Estructura de control para el llamado a métodos (funciones) según la opción elegida.
6. Criterios de Calidad: Asegúrate de incluir buenas prácticas, como el uso de switch para evaluar las opciones y la prevención de errores al capturar datos por consola.




```



### EXPLCIACIÓN

```
Pide construir la Capa de Vista de forma aislada. Exige un ciclo while para mantener el programa vivo, el uso de Scanner para capturar la entrada y un switch limpio que delegue las acciones.
```





### ✅ Resultado esperado

- Menú con `while`
- Uso de `input`
- Llamado a funciones

```

Para que el usuario pueda interactuar con nuestro sistema de gestión (agregar registros, listarlos, etc.), necesitamos construir una interfaz de consola. A nivel arquitectónico, esta es nuestra Capa de Vista. Su única responsabilidad debe ser mostrar opciones, recibir comandos y delegar el trabajo.

A continuación, se detalla la estructura ideal para lograr esto en Java:

1. Uso de Scanner (El equivalente a input)
En Java, para leer lo que el usuario escribe en la terminal, instanciamos un objeto de la clase Scanner. Es una buena práctica usar siempre scanner.nextLine() para capturar la entrada como texto y luego convertirla, evitando el clásico "bug" de salto de línea que ocurre al mezclar nextInt() con nextLine().

2. Menú interactivo con ciclo while
Para que el programa no se cierre inmediatamente después de ejecutar una acción, envolvemos nuestro menú en un ciclo while. Este ciclo mantendrá la aplicación "viva" mostrando el menú una y otra vez, hasta que el usuario seleccione explícitamente la opción de salir (por ejemplo, la opción 3).

3. Llamado a métodos (Funciones)
Dentro del ciclo, usamos una estructura switch para evaluar la opción elegida por el usuario. En lugar de escribir toda la lógica de negocio dentro de los case, aplicamos el principio de código limpio delegando la acción a métodos específicos (agregarRegistro(), mostrarRegistros()).

Aquí tienes el código de esta estructura implementada:

Java
import java.util.Scanner;

public class InterfazConsola {

    public static void iniciarMenu() {
        // 1. Instanciamos el Scanner para leer la entrada (input)
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // 2. Ciclo while para mantener el programa en ejecución
        while (continuar) {
            System.out.println("\n=== ⚙️ SISTEMA DE GESTIÓN ===");
            System.out.println("1. Agregar un nuevo registro");
            System.out.println("2. Ver todos los registros");
            System.out.println("3. Guardar y Salir");
            System.out.print("👉 Elige una opción: ");

            // Capturamos la entrada del usuario
            String entrada = scanner.nextLine();

            // 3. Estructura switch para el llamado a métodos (funciones)
            switch (entrada) {
                case "1":
                    agregarRegistro(scanner); // Llamado a método externo
                    break;
                case "2":
                    mostrarRegistros();       // Llamado a método externo
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
        
        // Es buena práctica cerrar el scanner al finalizar el programa
        scanner.close(); 
    }

    // --- Métodos delegados (Esqueletos) ---

    private static void agregarRegistro(Scanner scanner) {
        // Aquí pediremos los datos (ID, nombre, etc.) usando el scanner
        // y llamaremos a la lógica para guardar en el diccionario.
        System.out.println("Llamaste a la función: Agregar Registro");
    }

    private static void mostrarRegistros() {
        // Aquí llamaremos al diccionario para imprimir los datos.
        System.out.println("Llamaste a la función: Mostrar Registros");
    }
}
Con esto, ya tenemos la estructura completa de la interfaz del usuario, aislada y lista para conectarse con la lógica de diccionarios y JSON que construimos antes.



```

------

## 🧠 Reto 5: Integración final mediante prompt maestro

### 📌 Instrucción

Construye un **prompt maestro** que integre todo el sistema.

### 🧾 Prompt maestro esperado

```
1. Rol: Actúa como un Arquitecto de Software Senior y Desarrollador Java Experto.

2. Objetivo: Desarrollar el código completo e integrado de un "Sistema de Gestión de Productos" de consola. Debes unificar una arquitectura limpia, el almacenamiento en memoria estrictamente mediante diccionarios anidados (Map<String, Map<String, Object>>) y la persistencia de datos en archivos JSON usando la librería Jackson (ObjectMapper).

3. Contexto: Dirigido a estudiantes de ingeniería de software que necesitan un ejemplo práctico de cómo integrar la capa de Vista (menú interactivo), la estructura de datos en memoria (sin usar clases de modelo complejas) y la capa de Acceso a Datos (archivos JSON) en un proyecto funcional.

4. Restricciones: Utiliza estrictamente el lenguaje Java. Aplica el Principio de Responsabilidad Única (SRP) separando la lógica de persistencia de la interfaz de usuario. No utilices clases de modelo (POJOs) para los registros; la información debe vivir puramente en los diccionarios anidados. No uses bases de datos SQL, la persistencia debe ser 100% en JSON.

5. Formato: Organiza tu respuesta entregando el código fuente estructurado y comentado para los siguientes componentes:

Estructura de Datos: Explicación rápida de cómo se declara el diccionario anidado principal en memoria.

Clase de Persistencia (GestorDatos.java): Métodos estáticos utilizando ObjectMapper (writeValue y readValue con TypeReference), incluyendo el manejo de IOException y la comprobación de existencia del archivo (arranque seguro).

Clase Principal (Main.java / Vista): Menú interactivo con un ciclo while, uso de Scanner para la entrada de datos, y una estructura switch que llame a métodos delegados limpios.

6. Criterios de Calidad: Código modular, prevención de errores de salto de línea o lectura de números al usar Scanner, e inclusión obligatoria de las importaciones correctas de com.fasterxml.jackson.*.
```

------



### EXPLCIACIÓN

```
Es la instrucción definitiva. Le exige a la IA asumir el rol de Arquitecto Senior para ensamblar todas las piezas anteriores desde cero, garantizando que el código final nazca modularizado y respetando las restricciones de Java.
```





## 📋 Entregables del estudiante

1. Documento con:
   - Prompts diseñados
   - Explicación breve de cada prompt
2. Código fuente en Python
3. Archivo JSON generado por el programa