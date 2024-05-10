package Encryption;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Somos Programadores Facebook
 * https://web.facebook.com/developers08062019
 */
public class Encoder {
    // Clave secreta para la encriptación y desencriptación

    String secretKey = "Capufe";

    // Método para encriptar una cadena de texto
    public String ecnode(String cadena) {
        String encriptacion = "";
        try {
            // Se utiliza el algoritmo MD5 para obtener una clave segura
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // Se genera una matriz de bytes a partir de la clave secreta
            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
            // Se ajusta la longitud de la clave a 24 bytes
            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
            // Se crea un objeto SecretKey a partir de la clave
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            // Se instancia un objeto Cipher para encriptar
            Cipher cifrado = Cipher.getInstance("DESede");
            // Se inicializa el Cipher en modo de encriptación con la clave
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            // Se convierte la cadena de texto en una matriz de bytes
            byte[] plainTextBytes = cadena.getBytes("utf-8");
            // Se realiza la encriptación
            byte[] buf = cifrado.doFinal(plainTextBytes);
            // Se convierte el resultado en Base64 y se guarda como cadena
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encriptacion = new String(base64Bytes);
        } catch (Exception ex) {
            // Se muestra un mensaje de error en caso de excepción
            JOptionPane.showMessageDialog(null, "Error al encriptar");
        }
        // Se devuelve la cadena encriptada
        return encriptacion;
    }

    // Método para desencriptar una cadena encriptada
    public String deecnode(String cadenaEncriptada) {
        String desencriptacion = "";
        try {
            // Se decodifica la cadena encriptada de Base64 a una matriz de bytes
            byte[] message = Base64.decodeBase64(cadenaEncriptada.getBytes("utf-8"));
            // Se utiliza el algoritmo MD5 para obtener la clave secreta
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // Se genera una matriz de bytes a partir de la clave secreta
            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
            // Se ajusta la longitud de la clave a 24 bytes
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            // Se crea un objeto SecretKey a partir de la clave
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            // Se instancia un objeto Cipher para desencriptar
            Cipher decipher = Cipher.getInstance("DESede");
            // Se inicializa el Cipher en modo de desencriptación con la clave
            decipher.init(Cipher.DECRYPT_MODE, key);
            // Se realiza la desencriptación
            byte[] plainText = decipher.doFinal(message);
            // Se convierte el resultado a una cadena utilizando UTF-8
            desencriptacion = new String(plainText, "UTF-8");
        } catch (Exception ex) {
            // Se muestra un mensaje de error en caso de excepción
            JOptionPane.showMessageDialog(null, "Error al desencriptar");
        }
        // Se devuelve la cadena desencriptada
        return desencriptacion;
    }
}
