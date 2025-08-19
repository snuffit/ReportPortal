package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    protected static final String BASE_URL =
            System.getProperty("baseURL", PropertyReader.getProperty("baseURL")).concat("/ui"),
            PROJECT_NAME = "/#default_personal",
            DASHBOARD_ENDPOINT = "/dashboard";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
    }

    public abstract BasePage isPageOpened();
}
