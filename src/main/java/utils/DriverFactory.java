package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void createDriver(String browser) {
        if (driverThreadLocal.get() == null) {
            WebDriver driver;
            if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--incognito");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-infobars");
                if(System.getProperty("headless", "true").equals("true")){
                    options.addArguments("--headless");
                }
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                driver = new ChromeDriver(options);
                driverThreadLocal.set(driver);
            } else if (browser.equals("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
                driverThreadLocal.set(driver);
            }
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        driver.quit();
        driverThreadLocal.remove();
    }
}
