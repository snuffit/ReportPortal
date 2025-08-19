package api.dashboard;

import adapters.ResponseWrapper;
import api.BaseAPITest;
import dto.api.Dashboard;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class CreateDashboardNegativeAPITest extends BaseAPITest {

    Dashboard dashboard = Dashboard.builder()
            .name("12")
            .build();

    @Epic("Create Dashboard ")
    @Feature("Dashboard")
    @Story("Create Dashboard with invalid value by API")
    @Test(testName = "Create Dashboard with invalid value")
    public void createDashboardWithInvalidValue() {
        ResponseWrapper response = dashboardAPIStep.createDashboard(dashboard);
        softAssert.assertFalse(dashboardAPIStep.isDashboardCreated(dashboard), "Dashboard is created");
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();
    }
}
