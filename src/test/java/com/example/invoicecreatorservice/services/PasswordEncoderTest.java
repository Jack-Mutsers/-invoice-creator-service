package com.example.invoicecreatorservice.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncoderTest {

    @Test
    public void passwordValidationSuccessful() {
        String accountPassword = "HrLULWgjBCAnyhtFxsBljMJtWQhl++50A++Yij/1wUI=$0U2bjzl1Nik9emOoMlRkUkfUHRUXskHnqJE9FTCcCfs=";
        String password = "Password1!";

        try {
            boolean validPassword = PasswordEncoder.check(password, accountPassword);

            assertTrue(validPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void passwordValidationFailure() {
        String accountPassword = "HrLULWgjBCAnyhtFxsBljMJtWQhl++50A++Yij/1wUI=$0U2bjzl1Nik9emOoMlRkUkfUHRUXskHnqJE9FTCcCfs=";
        String password = "Wr0ngP@s$w0rd1!";

        try {
            boolean validPassword = PasswordEncoder.check(password, accountPassword);

            assertFalse(validPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void passwordValidationException() {
        String accountPassword = "Password1!";
        String password = "Password1!";

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            boolean validPassword = PasswordEncoder.check(password, accountPassword);
        });

        String expectedMessage = "The stored password must have the form 'salt$hash'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void passwordEncryption() {
        String password = "Password1!";
        String newPassword = "";

        try {
            newPassword = PasswordEncoder.getSaltedHash(password);

            assertFalse(password.equals(newPassword));
            assertFalse(newPassword.contains(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void emptyPasswordEncryptionException() {
        String password = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PasswordEncoder.getSaltedHash(password);
        });

        String expectedMessage = "Empty passwords are not supported.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
