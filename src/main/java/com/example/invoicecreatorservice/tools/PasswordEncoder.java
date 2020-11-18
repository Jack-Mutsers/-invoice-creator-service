package com.example.invoicecreatorservice.tools;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.InvalidPropertiesFormatException;

public class PasswordEncoder {

    private static PasswordEncoder myself;

    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private final int ITERATIONS = 20*1000;
    private final int SALT_LENGTH = 32;
    private final int DESIRED_LENGTH = 256;

    private PasswordEncoder(){}

    public static PasswordEncoder getInstance(){
        if(myself == null){ myself = new PasswordEncoder(); }

        return myself;
    }

    /** Computes a salted PBKDF2 hash of given plaintext password
     suitable for storing in a database.
     Empty passwords are not supported. */
    public String getSaltedHash(String password) throws InvalidPropertiesFormatException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LENGTH);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds
     to a stored salted hash of the password. */
    public boolean check(String password, String stored) throws InvalidPropertiesFormatException, NoSuchAlgorithmException, InvalidKeySpecException{
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            throw new InvalidPropertiesFormatException("The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    private String hash(String password, byte[] salt) throws InvalidPropertiesFormatException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (password == null || password.length() == 0)
            throw new InvalidPropertiesFormatException("Empty passwords are not supported.");

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, ITERATIONS, DESIRED_LENGTH));
        return Base64.encodeBase64String(key.getEncoded());
    }

}
