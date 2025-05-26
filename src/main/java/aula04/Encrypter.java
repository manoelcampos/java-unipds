package aula04;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

/**
 * @author Manoel Campos
 */
public class Encrypter {
    public static final String ALGORITHM = "AES";

    public static void main(String[] args) throws Exception  {
        final var keygen = KeyGenerator.getInstance(ALGORITHM); // algoritmo a ser usado
        keygen.init(256); // Tamanho da chave em bits
        final SecretKey secretKey = keygen.generateKey();
        final String text = "Hello World";
        System.out.println("Criptografando " + text);
        final String encrypted = crypt(text, secretKey);
        System.out.println("\t" + encrypted);

        System.out.println("Descriptografando");
        System.out.println("\t" + decrypt(encrypted, secretKey));
    }

    public static String crypt(final String text, final Key key) throws Exception {
        final var cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] bytes = cipher.doFinal(text.getBytes());
        final Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    public static String decrypt(final String text, final Key chave) throws Exception {
        final var cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, chave);

        final byte[] decodedBytes = Base64.getDecoder().decode(text.getBytes());
        return new String(cipher.doFinal(decodedBytes));
    }
}
