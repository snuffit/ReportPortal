package steps.ui;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginUIStep {

        WebDriver driver;
        LoginPage loginPage;

    public LoginUIStep(WebDriver driver) {
            this.driver = driver;
            loginPage = new LoginPage(driver);
    }

    public void auth(String login, String password) {
        loginPage
                .open()
                .isPageOpened()
                .login(login, password);
    }
}
