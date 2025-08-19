package ui.widget;

import dto.api.Dashboard;
import dto.ui.Widget;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.BaseUITest;
import utils.Retry;

import static org.testng.Assert.assertTrue;

public class CreateWidgetUITest extends BaseUITest {

    Dashboard dashboard = Dashboard.builder()
            .name("Test Dashboard-".concat(faker.regexify("[A-Za-z0-9]{8}")))
            .build();
    Widget widget = Widget.builder()
            .name("Task Progress")
            .build();

    @BeforeMethod(description = "Create Dashboard")
    public void createDashboard() {
        dashboardAPIStep.createDashboard(dashboard);
    }

    @Epic("Create Widget")
    @Feature("Widget")
    @Story("Create Widget with valid value by UI")
    @Test(testName = "Create Widget with valid value", retryAnalyzer = Retry.class)
    public void createWidgetTest() {
        loginUIStep.auth(login, password);
        dashboardUIStep.openDashboardPage(dashboard);
        widgetUIStep.createWidget(widget);
        assertTrue(dashboardPage.isWidgetCreated(widget), "Widget not created");
    }

    @AfterMethod(description = "Delete Dashboard")
    public void deleteDashboard() {
        dashboardAPIStep.deleteDashboard(dashboard);
    }
}
