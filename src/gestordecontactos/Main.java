/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestordecontactos;

import java.io.*; // Importa clases para entrada/salida de archivos
import java.util.*; // Importa utilidades como Scanner y List

public class Main { // Clase principal donde inicia el programa
    static final String ARCHIVO = "agenda.dat"; // Nombre del archivo para guardar los contactos

    public static void main(String[] args) { // Método principal que se ejecuta al iniciar el programa
        Scanner sc = new Scanner(System.in); // Objeto para leer entrada del usuario
        ArbolContactos agenda = cargarArchivo(); // Carga los contactos desde el archivo si existe

        int opcion; // Variable para almacenar la opción del menú seleccionada
        do { // Bucle principal del menú hasta que el usuario decida salir
            System.out.println("\n--- Gestor de Contactos ---"); // Muestra título del menú
            System.out.println("1. Agregar contacto"); // Opción para agregar contacto
            System.out.println("2. Buscar contacto exacto"); // Opción para buscar contacto exacto
            System.out.println("3. Eliminar contacto"); // Opción para eliminar contacto
            System.out.println("4. Mostrar todos los contactos"); // Opción para mostrar todos los contactos
            System.out.println("5. Salir"); // Opción para salir
            System.out.println("6. Buscar por nombre parcial"); // Opción para buscar por nombre parcial
            System.out.print("Opción: "); // Solicita al usuario que ingrese una opción
            opcion = sc.nextInt(); // Lee la opción del usuario
            sc.nextLine(); // Limpia el buffer del scanner

            switch (opcion) { // Evalúa la opción ingresada
                case 1: // Caso para agregar contacto
                    System.out.print("Nombre: "); // Solicita nombre
                    String nombre = sc.nextLine(); // Lee nombre
                    System.out.print("Teléfono: "); // Solicita teléfono
                    String telefono = sc.nextLine(); // Lee teléfono
                    System.out.print("Correo: "); // Solicita correo
                    String correo = sc.nextLine(); // Lee correo
                    agenda.insertar(new Contacto(nombre, telefono, correo)); // Crea contacto y lo agrega al árbol
                    guardarArchivo(agenda); // Guarda los contactos actualizados en archivo
                    System.out.println("✅ Contacto agregado."); // Mensaje de éxito
                    break; // Fin del caso 1
                case 2: // Caso para buscar contacto exacto
                    System.out.print("Nombre a buscar: "); // Solicita nombre
                    String buscar = sc.nextLine(); // Lee nombre
                    Contacto encontrado = agenda.buscar(buscar); // Busca el contacto en el árbol
                    if (encontrado != null) { // Si se encontró
                        System.out.println("🔍 Contacto encontrado:\n" + encontrado); // Muestra el contacto
                    } else { // Si no se encontró
                        System.out.println("⚠️ Contacto no encontrado."); // Mensaje de error
                    }
                    break; // Fin del caso 2
                case 3: // Caso para eliminar contacto
                    System.out.print("Nombre a eliminar: "); // Solicita nombre
                    String eliminar = sc.nextLine(); // Lee nombre
                    agenda.eliminar(eliminar); // Elimina el contacto del árbol
                    guardarArchivo(agenda); // Guarda cambios en el archivo
                    System.out.println("🗑️ Contacto eliminado (si existía)."); // Mensaje de éxito
                    break; // Fin del caso 3
                case 4: // Caso para mostrar todos los contactos
                    System.out.println("📒 Lista de contactos:"); // Título de lista
                    agenda.mostrarEnOrden(); // Muestra contactos ordenados alfabéticamente
                    break; // Fin del caso 4
                case 5: // Caso para salir
                    System.out.println("👋 Saliendo del programa."); // Mensaje de salida
                    break; // Fin del caso 5
                case 6: // Caso para búsqueda parcial
                    System.out.print("Texto parcial a buscar: "); // Solicita texto parcial
                    String parcial = sc.nextLine(); // Lee texto
                    List<Contacto> coincidencias = agenda.buscarParciales(parcial); // Busca coincidencias parciales
                    if (coincidencias.isEmpty()) { // Si no se encontraron coincidencias
                        System.out.println("⚠️ No se encontraron coincidencias."); // Mensaje de error
                    } else { // Si hay coincidencias
                        System.out.println("🔎 Contactos encontrados:"); // Mensaje de éxito
                        for (Contacto c : coincidencias) { // Recorre los contactos encontrados
                            System.out.println(c); // Muestra cada contacto
                        }
                    }
                    break; // Fin del caso 6
                default: // Si la opción no es válida
                    System.out.println("❌ Opción no válida."); // Mensaje de error
            } // Fin del switch
        } while (opcion != 5); // Repite el menú hasta que el usuario elija salir
    }

    public static void guardarArchivo(ArbolContactos agenda) { // Método para guardar los contactos en archivo
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) { // Crea flujo de salida
            out.writeObject(agenda); // Escribe el objeto en el archivo
        } catch (IOException e) { // Si hay error
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage()); // Muestra el error
        }
    }

    public static ArbolContactos cargarArchivo() { // Método para cargar contactos desde archivo
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) { // Crea flujo de entrada
            return (ArbolContactos) in.readObject(); // Lee el objeto desde archivo
        } catch (IOException | ClassNotFoundException e) { // Si hay error o archivo no existe
            return new ArbolContactos(); // Retorna un nuevo árbol vacío
        }
    }
}