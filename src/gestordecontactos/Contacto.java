/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestordecontactos;


import java.io.Serializable; // Importa la interfaz Serializable para permitir guardar objetos en archivo

public class Contacto implements Serializable { // Clase que representa un contacto y puede ser serializada
    private String nombre; // Atributo para almacenar el nombre del contacto
    private String telefono; // Atributo para almacenar el número de teléfono
    private String correo; // Atributo para almacenar el correo electrónico

    public Contacto(String nombre, String telefono, String correo) { // Constructor que recibe los datos
        this.nombre = nombre; // Asigna el nombre
        this.telefono = telefono; // Asigna el teléfono
        this.correo = correo; // Asigna el correo
    }

    public String getNombre() { // Método para obtener el nombre
        return nombre; // Retorna el nombre del contacto
    }

    public String getTelefono() { // Método para obtener el teléfono
        return telefono; // Retorna el número telefónico
    }

    public String getCorreo() { // Método para obtener el correo
        return correo; // Retorna el correo electrónico
    }

    @Override
    public String toString() { // Método para representar el contacto como texto
        return "Nombre: " + nombre + ", Teléfono: " + telefono + ", Correo: " + correo; // Formato del contacto
    }
}