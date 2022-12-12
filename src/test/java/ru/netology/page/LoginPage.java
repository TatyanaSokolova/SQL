package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] .input__control");
    private SelenideElement passwordField = $("[data-test-id=password] .input__control");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage loginWithInvalidPassword(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(DataHelper.getRandomPassword());
        loginButton.click();
        errorNotification.shouldBe(visible);
        return new LoginPage();
    }

    public LoginPage loginThriceWithInvalidPassword(DataHelper.AuthInfo info) {
        for (int i = 0; i < 3; i++) {
            loginField.doubleClick();
            loginField.sendKeys(Keys.DELETE);
            passwordField.doubleClick();
            passwordField.sendKeys(Keys.DELETE);
            loginWithInvalidPassword(info);
        }
        loginButton.shouldBe(disabled);
        return new LoginPage();
    }
}
