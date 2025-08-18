package pages;

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

    @Override
    public LoginPage open() {
        log.info("Open LoginPage URI: '{}'", BASE_URL);
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(LOGIN_BUTTON)));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail();
        }
        log.info("Page is loaded");
        return this;
    }

    public HomePage login(String login, String password) {
        log.info("Login");
        driver.findElement(LOGIN_INPUT).sendKeys(login);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new HomePage(driver);
    }
}
