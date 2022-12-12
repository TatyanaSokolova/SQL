package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {

    @AfterAll
    static void teardown() {
        SQLHelper.cleanTables();
    }

    @Test
    public void shouldValidAuthorize() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldBeErrorMessageIfAuthoraizeWithInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.loginWithInvalidPassword(authInfo);
    }

    @Test
    public void shouldBeEnabledToLoginInAfterLoginThriceWithInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.loginThriceWithInvalidPassword(authInfo);
    }
}
