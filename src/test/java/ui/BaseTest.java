package ui;

import com.github.javafaker.Faker;
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
import pages.DashboardPage;
import steps.api.DashboardAPIStep;
import steps.ui.DashboardUIStep;
import steps.ui.LoginUIStep;
import steps.ui.WidgetUIStep;
import utils.DriverFactory;
import utils.PropertyReader;

import java.time.Duration;
import java.util.HashMap;

import static utils.AllureUtils.takeScreenshot;
import static utils.DriverFactory.*;

public class BaseTest {

    protected static WebDriver driver;
    protected static DashboardAPIStep dashboardAPIStep;
    protected static String login = System.getProperty("login", PropertyReader.getProperty("login"));
    protected static String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected static LoginUIStep loginUIStep;
    protected static WidgetUIStep widgetUIStep;
    protected static DashboardUIStep dashboardUIStep;
    protected static DashboardPage dashboardPage;
    protected static Faker faker = new Faker();

    @Parameters
    @BeforeMethod(alwaysRun = true, description = "Открытие браузера")
    public void setup(@Optional("chrome") String browser) {
        createDriver(browser);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
        dashboardAPIStep = new DashboardAPIStep();
        loginUIStep = new LoginUIStep(getDriver());
        widgetUIStep = new WidgetUIStep(getDriver());
        dashboardUIStep = new DashboardUIStep(getDriver());
        dashboardPage = new DashboardPage(getDriver());
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(getDriver());
        }
        if (getDriver() != null) {
            quitDriver();
        }
    }
}
