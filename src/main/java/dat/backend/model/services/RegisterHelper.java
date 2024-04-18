package dat.backend.model.services;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.protobuf.Message;

public class RegisterHelper {

    /**
     * Genererer en hash-værdi for den givne adgangskode ved hjælp af SHA-256 værdien
     * Metoden anvender MessageDigest-klassen til at udføre hashing-operationen.
     *
     * @param password Adgangskoden, der skal hashes.
     * @return En streng, der repræsenterer den hashede værdi af adgangskoden.
     */

            public static String hashPassword(String password) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(password.getBytes());
                    byte[] byteData = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < byteData.length; i++) {
                        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    return sb.toString();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return null;
                }
            }


            /*      String password = "myPassword123";
                    String hashedPassword = PasswordHasher.hashPassword(password);
                    System.out.println(hashedPassword);         */

        }



