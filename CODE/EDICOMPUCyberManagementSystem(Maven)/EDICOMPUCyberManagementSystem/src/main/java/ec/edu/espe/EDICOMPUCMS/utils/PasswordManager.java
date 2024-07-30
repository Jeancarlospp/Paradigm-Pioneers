package ec.edu.espe.EDICOMPUCMS.utils;

/**
 *
 * @author Andrea Raura,Paradigm Pioneers Squad, DCCO-ESPE
 */
public class PasswordManager {

    public static String encrypt(String password) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : password.toCharArray()) {
            encrypted.append((char) (ch + 1));
        }
        return encrypted.toString();
    }

    public static String decrypt(String password) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : password.toCharArray()) {
            decrypted.append((char) (ch - 1));
        }
        return decrypted.toString();
    }

}
