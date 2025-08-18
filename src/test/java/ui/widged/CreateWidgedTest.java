package ui.widged;

import dto.api.Dashboard;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.BaseTest;

public class CreateWidgedTest extends BaseTest {

    Dashboard dashboard = Dashboard.builder()
            .name("TestDashboard")
            .build();

    @BeforeMethod
    public void createDashboard() {
       dashboardAPIStep.createDashboard(dashboard);
    }

    @Test
    public void createWidgedTest() {
        loginUIStep.auth(login, password);
        openDashboard(dashboard.getTitle);
        createWidged(widged);
        Assert.assertTrue(isWidgedDispayed(widget.getTitle), "Widget not created");
    }

    @AfterMethod
    public void deleteDashboard() {
        dashboardAPIStep.deleteDashboard(dashboard);
    }
}
