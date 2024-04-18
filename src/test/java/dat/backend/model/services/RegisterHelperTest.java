package dat.backend.model.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterHelperTest {


        @Test
        public void testHashPassword() {
            String password = "123456";
            String expectedHash = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
            String actualHash = RegisterHelper.hashPassword(password);
            Assertions.assertEquals(expectedHash, actualHash);
    }
}