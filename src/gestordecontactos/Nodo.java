/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestordecontactos;

import java.io.Serializable; // Permite que los nodos se puedan guardar como objetos

public class Nodo implements Serializable { // Clase que representa un nodo en el árbol binario
    Contacto contacto; // Atributo que almacena un objeto de tipo Contacto
    Nodo izquierda; // Referencia al nodo hijo izquierdo
    Nodo derecha; // Referencia al nodo hijo derecho

    public Nodo(Contacto contacto) { // Constructor que inicializa el nodo con un contacto
        this.contacto = contacto; // Asigna el contacto
        this.izquierda = null; // Inicializa el hijo izquierdo como nulo
        this.derecha = null; // Inicializa el hijo derecho como nulo
    }
}