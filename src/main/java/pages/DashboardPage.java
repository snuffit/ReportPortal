package pages;

import dto.ui.Widget;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class DashboardPage extends BasePage{

    private final By ADD_NEW_WIDGET = By.xpath("//*[text()='Add new widget']");
    private final String WIDGET_NAME_FORMAT = "//*[text()='%s']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage open(String dashboardID) {
        driver.get(BASE_URL + PROJECT_NAME + DASHBOARD_ENDPOINT + "/" + dashboardID);
        return this;
    }

    @Override
    public DashboardPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(ADD_NEW_WIDGET)));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail();
        }
        log.info("Page is loaded");
        return this;
    }

    public WidgetPage clickAddNewWidgetButton() {
        driver.findElement(ADD_NEW_WIDGET).click();
        return new WidgetPage(driver);
    }

    public boolean isWidgetDisplayed(Widget widget) {
        try {
            wait.until(ExpectedConditions.visibilityOf(
                    driver.findElement(By.xpath(String.format(WIDGET_NAME_FORMAT, widget.getName())))));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            return false;
        }
        log.info("Widget is created");
        return true;
    }
}
