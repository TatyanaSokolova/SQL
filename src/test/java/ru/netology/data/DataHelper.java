package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo getRandomAuthInfo() {
        return new AuthInfo(getRandomLogin(), getRandomPassword());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getRandomCode() {
        return new VerificationCode(String.valueOf(faker.number().numberBetween(100000, 999999)));
    }
}
