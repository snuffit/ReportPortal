package api.dashboard;

import adapters.ResponseWrapper;
import api.BaseAPITest;
import dto.api.Dashboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateDashboardAPITest extends BaseAPITest {

    Dashboard dashboard;

    @Test
    public void createDashboardWithValidValue() {
        dashboard = Dashboard.builder()
                .name("Test Dashboard-".concat(faker.regexify("[A-Za-z0-9]{8}")))
                .build();
        ResponseWrapper response = dashboardAPIStep.createDashboard(dashboard);
        softAssert.assertTrue(dashboardAPIStep.isDashboardCreated(dashboard), "Dashboard isn't created");
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertAll();
    }

    @Test
    public void createDashboardWithInvalidValue() {
        dashboard = Dashboard.builder()
                .name("12")
                .build();
        ResponseWrapper response = dashboardAPIStep.createDashboard(dashboard);
        softAssert.assertFalse(dashboardAPIStep.isDashboardCreated(dashboard), "Dashboard is created");
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();
    }

    @AfterMethod
    public void deleteDashboard() {
        dashboardAPIStep.deleteDashboard(dashboard);
    }
}
