package ui.widget;

import dto.api.Dashboard;
import dto.ui.Widget;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.BaseUITest;

import static org.testng.Assert.assertTrue;

public class CreateWidgetUITest extends BaseUITest {

    Dashboard dashboard = Dashboard.builder()
            .name("Test Dashboard-".concat(faker.regexify("[A-Za-z0-9]{8}")))
            .build();
    Widget widget = Widget.builder()
            .name("Task Progress")
            .build();

    @BeforeMethod
    public void createDashboard() {
       dashboardAPIStep.createDashboard(dashboard);
    }

    @Test
    public void createWidgetTest() {
        loginUIStep.auth(login, password);
        dashboardUIStep.openDashboardPage(dashboard);
        widgetUIStep.createWidget(widget);
        assertTrue(dashboardPage.isWidgetDisplayed(widget), "Widget not created");
    }

    @AfterMethod
    public void deleteDashboard() {
        dashboardAPIStep.deleteDashboard(dashboard);
    }
}
