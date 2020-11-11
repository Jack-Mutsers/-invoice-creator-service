package com.example.invoicecreatorservice.tools;

import com.example.invoicecreatorservice.tools.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordEncoderTest {

    @Test
    void passwordValidationSuccessful() {
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
    void passwordValidationFailure() {
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
    void passwordValidationException() {
        String accountPassword = "Password1!";
        String password = "Password1!";

        Exception exception = assertThrows(InvalidPropertiesFormatException.class, () -> {
            boolean validPassword = PasswordEncoder.check(password, accountPassword);
        });

        String expectedMessage = "The stored password must have the form 'salt$hash'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void passwordEncryption() {
        String password = "Password1!";
        String newPassword = "";

        try {
            newPassword = PasswordEncoder.getSaltedHash(password);

            assertNotEquals("", newPassword);
            assertNotEquals(password, newPassword);
            assertFalse(newPassword.contains(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void emptyPasswordEncryptionException() {
        String password = "";

        Exception exception = assertThrows(InvalidPropertiesFormatException.class, () -> {
            PasswordEncoder.getSaltedHash(password);
        });

        String expectedMessage = "Empty passwords are not supported.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
