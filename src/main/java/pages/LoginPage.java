package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By LOGIN_BUTTON = By.xpath("//button[@type='submit']"),
            LOGIN_INPUT = By.name("login"),
            PASSWORD_INPUT = By.name("password");
    private final String URI = BASE_URL.concat("/#login");

    @Step("Open Login page by URI: {URI}")
    public LoginPage open() {
        log.info("Open LoginPage URI: '{}'", URI);
        driver.get(URI);
        return this;
    }

    @Step("Checking if Login page is open")
    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(LOGIN_BUTTON)));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't loaded");
        }
        log.info("Page is loaded");
        return this;
    }

    @Step("Login with login - '{login}' and password - '{password}'")
    public void login(String login, String password) {
        log.info("Login with login - '{}' and password - '{}'", login, password);
        driver.findElement(LOGIN_INPUT).sendKeys(login);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
