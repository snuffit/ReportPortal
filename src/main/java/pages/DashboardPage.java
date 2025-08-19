package pages;

import dto.ui.Widget;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class DashboardPage extends BasePage {

    private final By ADD_NEW_WIDGET = By.xpath("//*[text()='Add new widget']");
    private final String WIDGET_NAME_FORMAT = "//*[text()='%s']",
            URI = BASE_URL + PROJECT_NAME + DASHBOARD_ENDPOINT + "/";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Dashboard page by URI - {URI}{dashboardID}")
    public DashboardPage open(String dashboardID) {
        driver.get(URI + dashboardID);
        log.info("Open Dashboard page by URI - '{}{}'", URI, dashboardID);
        return this;
    }

    @Step("Checking if Dashboard page is open")
    @Override
    public DashboardPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(ADD_NEW_WIDGET)));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't loaded");
        }
        log.info("Page is loaded");
        return this;
    }

    @Step("Click 'Add New Widget' button")
    public WidgetPage clickAddNewWidgetButton() {
        driver.findElement(ADD_NEW_WIDGET).click();
        log.info("Click 'Add New Widget' button");
        return new WidgetPage(driver);
    }

    @Step("Checking if the widget is created")
    public boolean isWidgetCreated(Widget widget) {
        try {
            wait.until(ExpectedConditions.visibilityOf(
                    driver.findElement(By.xpath(String.format(WIDGET_NAME_FORMAT, widget.getName())))));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            log.info("Widget isn't created");
            return false;
        }
        log.info("Widget is created");
        return true;
    }
}
