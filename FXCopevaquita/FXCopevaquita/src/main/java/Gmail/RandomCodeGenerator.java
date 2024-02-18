/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gmail;
import java.util.Random;

/**
 *
 * @author aleke
 */
public class RandomCodeGenerator {
     public static String generateRandomCode(int length) {
        // Define los caracteres posibles en el código
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Inicializa un generador de números aleatorios
        Random random = new Random();

        // Crea un StringBuilder para construir el código
        StringBuilder codeBuilder = new StringBuilder();

        // Genera el código seleccionando caracteres aleatorios de la cadena
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }

        // Retorna el código generado
        return codeBuilder.toString();
    }

}
