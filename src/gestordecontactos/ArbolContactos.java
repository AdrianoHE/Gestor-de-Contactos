/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestordecontactos;

import java.io.Serializable; // Permite serializar el árbol para guardarlo en archivo
import java.util.*; // Importa utilidades como ArrayList

public class ArbolContactos implements Serializable { // Clase que representa el árbol binario de contactos
    private Nodo raiz; // Nodo raíz del árbol

    public void insertar(Contacto contacto) { // Método para insertar un contacto
        raiz = insertarRecursivo(raiz, contacto); // Llama al método recursivo con la raíz actual
    }

    private Nodo insertarRecursivo(Nodo actual, Contacto contacto) { // Método recursivo para insertar contacto
        if (actual == null) { // Si no hay nodo en esta posición
            return new Nodo(contacto); // Crea y retorna un nuevo nodo
        }

        if (contacto.getNombre().compareToIgnoreCase(actual.contacto.getNombre()) < 0) { // Comparación alfabética
            actual.izquierda = insertarRecursivo(actual.izquierda, contacto); // Inserta a la izquierda
        } else if (contacto.getNombre().compareToIgnoreCase(actual.contacto.getNombre()) > 0) {
            actual.derecha = insertarRecursivo(actual.derecha, contacto); // Inserta a la derecha
        }
        return actual; // Retorna el nodo actual
    }

    public Contacto buscar(String nombre) { // Método público para buscar contacto exacto por nombre
        return buscarRecursivo(raiz, nombre); // Llama al método recursivo con la raíz
    }

    private Contacto buscarRecursivo(Nodo actual, String nombre) { // Búsqueda recursiva
        if (actual == null) return null; // Si no hay nodo, no se encontró
        if (nombre.equalsIgnoreCase(actual.contacto.getNombre())) return actual.contacto; // Si coincide, retorna

        if (nombre.compareToIgnoreCase(actual.contacto.getNombre()) < 0) {
            return buscarRecursivo(actual.izquierda, nombre); // Busca en subárbol izquierdo
        } else {
            return buscarRecursivo(actual.derecha, nombre); // Busca en subárbol derecho
        }
    }

    public void eliminar(String nombre) { // Método para eliminar un contacto
        raiz = eliminarRecursivo(raiz, nombre); // Llama al método recursivo
    }

    private Nodo eliminarRecursivo(Nodo actual, String nombre) { // Eliminación recursiva
        if (actual == null) return null; // Si nodo es nulo, no hay nada que eliminar

        if (nombre.compareToIgnoreCase(actual.contacto.getNombre()) < 0) {
            actual.izquierda = eliminarRecursivo(actual.izquierda, nombre); // Elimina por izquierda
        } else if (nombre.compareToIgnoreCase(actual.contacto.getNombre()) > 0) {
            actual.derecha = eliminarRecursivo(actual.derecha, nombre); // Elimina por derecha
        } else { // Nodo encontrado
            if (actual.izquierda == null) return actual.derecha; // Caso con un solo hijo derecho o nulo
            else if (actual.derecha == null) return actual.izquierda; // Caso con un solo hijo izquierdo o nulo

            Nodo sucesor = encontrarMinimo(actual.derecha); // Encuentra el nodo más pequeño del lado derecho
            actual.contacto = sucesor.contacto; // Copia el contenido del sucesor
            actual.derecha = eliminarRecursivo(actual.derecha, sucesor.contacto.getNombre()); // Elimina sucesor
        }

        return actual; // Retorna el nodo actualizado
    }

    private Nodo encontrarMinimo(Nodo nodo) { // Método auxiliar para encontrar el menor nodo en subárbol
        while (nodo.izquierda != null) { // Mientras haya hijo izquierdo
            nodo = nodo.izquierda; // Avanza
        }
        return nodo; // Retorna el nodo más pequeño
    }

    public void mostrarEnOrden() { // Muestra los contactos en orden alfabético
        mostrarEnOrdenRecursivo(raiz); // Llama al recorrido inorden
    }

    private void mostrarEnOrdenRecursivo(Nodo nodo) { // Recorrido inorden recursivo
        if (nodo != null) { // Si el nodo existe
            mostrarEnOrdenRecursivo(nodo.izquierda); // Visita subárbol izquierdo
            System.out.println(nodo.contacto); // Muestra el contacto
            mostrarEnOrdenRecursivo(nodo.derecha); // Visita subárbol derecho
        }
    }

    public List<Contacto> buscarParciales(String texto) { // Búsqueda parcial de texto en nombres
        List<Contacto> resultado = new ArrayList<>(); // Lista para almacenar coincidencias
        buscarParcialesRec(raiz, texto.toLowerCase(), resultado); // Llama recursivamente
        return resultado; // Devuelve lista
    }

    private void buscarParcialesRec(Nodo nodo, String texto, List<Contacto> resultado) {
        if (nodo != null) { // Si el nodo existe
            buscarParcialesRec(nodo.izquierda, texto, resultado); // Busca en hijo izquierdo
            if (nodo.contacto.getNombre().toLowerCase().contains(texto)) { // Si contiene el texto
                resultado.add(nodo.contacto); // Agrega contacto a resultados
            }
            buscarParcialesRec(nodo.derecha, texto, resultado); // Busca en hijo derecho
        }
    }
}