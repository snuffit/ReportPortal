package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import steps.api.DashboardAPIStep;
import steps.ui.LoginUIStep;
import utils.PropertyReader;

import java.time.Duration;

import static utils.AllureUtils.takeScreenshot;

public class BaseTest {

    protected static WebDriver driver;
    protected static DashboardAPIStep dashboardAPIStep;
    protected static String login = System.getProperty("login", PropertyReader.getProperty("login"));
    protected static String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected static LoginUIStep loginUIStep;

    @Parameters
    @BeforeMethod(alwaysRun = true, description = "Открытие браузера")
    public void setup(@Optional("chrome") String browser) {
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        dashboardAPIStep = new DashboardAPIStep();
        loginUIStep = new LoginUIStep(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
