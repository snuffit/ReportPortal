package pages;

import dto.ui.Widget;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class WidgetPage extends BasePage {

    private String WIDGET_TYPE_FORMAT =
            "//*[text()='Choose widget type from the list below']/following-sibling::div//*[text()='%s']",
            WIDGET_FILTER_FORMAT =
                    "//*[text()='Choose filter from the list below']/../following-sibling::div//span[contains(text(),'%s')]";
    private final By NEXT_STEP_BUTTON = By.xpath("//*[text()='Next step']"),
    WIDGET_NAME_INPUT = By.xpath("//*[@placeholder='Enter widget name']"),
    WIDGET_DESCRIPTION_INPUT = By.xpath("//*[@placeholder='Enter widget description']"),
    ADD_BUTTON = By.xpath("//*[text()='Add']");

    public WidgetPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WidgetPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(NEXT_STEP_BUTTON)));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail();
        }
        log.info("Page is loaded");
        return this;
    }

    public DashboardPage createWidget(Widget widget) {
        return selectType(widget.getType())
                .clickNextStepButton()
                .selectFilter(widget.getFilter())
                .clickNextStepButton()
                .writeWidgetName(widget.getName())
                .writeWidgetDescription(widget.getDescription())
                .clickAddButton();
    }

    private WidgetPage selectType(String widgetType) {
        driver.findElement(By.xpath(String.format(WIDGET_TYPE_FORMAT, widgetType))).click();
        return this;
    }

    private WidgetPage clickNextStepButton() {
        WebElement button = driver.findElement(NEXT_STEP_BUTTON);
        js.executeScript("arguments[0].click();", button);
        return this;
    }

    private WidgetPage selectFilter(String widgetFilter) {
        driver.findElement(By.xpath(String.format(WIDGET_FILTER_FORMAT,widgetFilter))).click();
        return this;
    }

    private WidgetPage writeWidgetName(String widgetName) {
        WebElement widgetNameInput = driver.findElement(WIDGET_NAME_INPUT);
        widgetNameInput.clear();
        widgetNameInput.sendKeys(widgetName);
        return this;
    }

    private WidgetPage writeWidgetDescription(String widgetDescription) {
        driver.findElement(WIDGET_DESCRIPTION_INPUT).sendKeys(widgetDescription);
        return this;
    }

    private DashboardPage clickAddButton() {
        driver.findElement(ADD_BUTTON).click();
        return new DashboardPage(driver);
    }
}
