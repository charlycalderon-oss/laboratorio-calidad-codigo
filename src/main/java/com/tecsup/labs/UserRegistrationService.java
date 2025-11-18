package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de registro de usuarios con varios problemas de calidad
 * intencionales para el laboratorio.
 */
public class UserRegistrationService {

    // Mala práctica: campo público y mutable (Corregido: ahora es privado)
    private String lastErrorMessage = "";
    
    // Mala práctica: lista sin genéricos (Corregido: ahora usa String y es final)
    // Se recomienda usar un objeto User en lugar de String para mejor diseño.
    private final List<String> users = new ArrayList<>(); 

    // Mala práctica: número mágico (Corregido: ya es una constante)
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Constructor del servicio de registro de usuarios.
     * Problema Corregido: Se eliminó la lógica innecesaria (if (users == null))
     */
    public UserRegistrationService() {
        // Se elimina el código innecesario aquí.
        // Si el objeto se inicializa con 'final List<String> users = new ArrayList<>();',
        // la condición 'if (users == null)' nunca se cumplirá.
        System.out.println("Constructor llamado");
    }

    /**
     * Obtiene el último mensaje de error.
     * @return mensaje de error
     */
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Registra un nuevo usuario.
     * Retorna true si se registra, false en caso contrario.
     */
    public boolean registerUser(String username, String password, String email) {
        // Validación de nulidad y vacío
        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario está vacío o es nulo.";
            return false;
        }

        // Validación de contraseña
        if (password == null) {
            lastErrorMessage = "La contraseña es nula.";
            return false;
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta (Mínimo " + MIN_PASSWORD_LENGTH + " caracteres).";
            return false;
        }

        // Validación de email (Mala lógica - Se mantiene simple, pero se corrige el return)
        if (!email.contains("@") || !email.contains(".")) {
            lastErrorMessage = "El correo electrónico no parece válido.";
            return false;
        }

        // Manejo de excepciones deficiente (¡Corregido!)
        try {
            // Simulación de acceso a base de datos
            saveUser(username); // Modificado para solo recibir datos necesarios.
            
        } catch (IllegalArgumentException e) {
            // Capturar excepción específica y loguear el mensaje (buena práctica)
            System.err.println("Error al guardar usuario: " + e.getMessage());
            lastErrorMessage = e.getMessage(); // Usamos el mensaje de la excepción específica
            return false;

        } catch (Exception e) {
            // Catch para otros errores inesperados (siempre es mejor capturar excepciones específicas)
            System.err.println("Error desconocido al guardar: " + e.getMessage());
            lastErrorMessage = "Error desconocido al guardar el usuario.";
            return false;
        }

        // Usuarios duplicados no se validan (Pendiente: requeriría un bucle/mapa de validación)
        System.out.println("Usuario registrado: " + username);
        return true;
    }

    /**
     * Simula la persistencia del usuario.
     * @param username El nombre de usuario a guardar.
     * @throws IllegalArgumentException Si el nombre de usuario no es permitido.
     */
    private void saveUser(String username) throws IllegalArgumentException {
        // Simula guardar el usuario en una lista
        // Nota: Idealmente se guardaría un objeto User, no solo el String.
        users.add(username); 
        
        if (username.equals("error")) {
            // Excepción más específica (IllegalArgumentException en lugar de Exception)
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }
    }

    /**
     * Devuelve la longitud de la cadena de entrada.
     * @param s La cadena a procesar.
     * @return La longitud de la cadena, o -1 si es nula.
     */
    public int getStringLength(String s) { // Nombre corregido
        if (s == null) {
            return -1;
        }
        // Uso ineficiente de String (¡Corregido!)
        // La concatenación en bucle es ineficiente; el método .length() es la solución.
        return s.length(); 
    }
}