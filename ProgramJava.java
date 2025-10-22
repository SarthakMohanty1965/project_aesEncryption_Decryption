import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class ProgramJava {

    private static final String ALGORITHM = "AES/CFB/NoPadding";
    private static final byte[] IV = "1234567890123456".getBytes(); // Fixed IV 
    private static final byte[] KEY = "0123456789abcdef".getBytes(); 

    // Encrypt text
    public static String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt text
    public static String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decoded = Base64.getDecoder().decode(encryptedText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Encrypt or Decrypt? (e/d): ");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("e")) {
            System.out.print("Enter text to encrypt: ");
            String text = scanner.nextLine();
            try {
                String encrypted = encrypt(text);
                System.out.println("Encrypted: " + encrypted);
            } catch (Exception e) {
                System.out.println("Encryption failed: " + e.getMessage());
            }
        } else if (choice.equals("d")) {
            System.out.print("Enter text to decrypt: ");
            String text = scanner.nextLine();
            try {
                String decrypted = decrypt(text);
                System.out.println("Decrypted: " + decrypted);
            } catch (Exception e) {
                System.out.println("Decryption failed: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid choice");
        }

        scanner.close();
    }
}
