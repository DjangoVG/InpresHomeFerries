package SecurityCrypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DigestSaltPassword
{
    public void DigestSaltPassword(){};
    public StringBuilder DigestSaltPassword(String pwd) 
    {
        StringBuilder sb = null;
        MessageDigest md;
        try
        {
            // Select the message digest for the hash computation -> SHA-256
            md = MessageDigest.getInstance("SHA-256");

            // Generate the random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Passing the salt to the digest for the computation
            md.update(salt);

            // Generate the salted hash
            byte[] hashedPassword = md.digest(pwd.getBytes(StandardCharsets.UTF_8));

            sb = new StringBuilder();
            for (byte b : hashedPassword)
                sb.append(String.format("%02x", b));

            System.out.println(sb.toString());
        } catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        return sb;
    }
}
