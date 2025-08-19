package api.dashboard;

import adapters.ResponseWrapper;
import api.BaseAPITest;
import dto.api.Dashboard;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateDashboardPositiveAPITest extends BaseAPITest {

    Dashboard dashboard = Dashboard.builder()
            .name("Test Dashboard-".concat(faker.regexify("[A-Za-z0-9]{8}")))
            .build();

    @Epic("Create Dashboard ")
    @Feature("Dashboard")
    @Story("Create Dashboard with valid value by API")
    @Test(testName = "Create Dashboard with valid value")
    public void createDashboardWithValidValue() {
        ResponseWrapper response = dashboardAPIStep.createDashboard(dashboard);
        softAssert.assertTrue(dashboardAPIStep.isDashboardCreated(dashboard), "Dashboard isn't created");
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertAll();
    }

    @AfterMethod(description = "Delete Dashboard")
    public void deleteDashboard() {
        dashboardAPIStep.deleteDashboard(dashboard);
    }
}
