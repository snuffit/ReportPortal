package steps.ui;

import dto.ui.Widget;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.WidgetPage;

public class WidgetUIStep {

    WebDriver driver;
    WidgetPage widgetPage;
    DashboardPage dashboardPage;

    public WidgetUIStep(WebDriver driver) {
        this.driver = driver;
        widgetPage = new WidgetPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    public void createWidget(Widget widget) {
        dashboardPage.clickAddNewWidgetButton()
                .isPageOpened()
                .createWidget(widget)
                .isPageOpened();
    }
}
