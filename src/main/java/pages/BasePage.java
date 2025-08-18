package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    protected static final String BASE_URL =
            System.getProperty("baseURL", PropertyReader.getProperty("baseURL")).concat("/ui/");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public abstract BasePage open();
    public abstract BasePage isPageOpened();
}
