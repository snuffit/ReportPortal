package steps.api;

import adapters.BaseApi;
import dto.api.Dashboard;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class DashboardAPIStep extends BaseApi {

    final String CREATE_DASHBOARD = "/dashboard",
    PROJECT_NAME = "/default_personal";

    @Step("Create dashboard")
    public Response createDashboard(String projectName, Dashboard dashboard) {
        log.info("Create Dashboard with name '{}'", dashboard.getName());
        Map<String, Object> body = Map.of(
                "name", dashboard.getName(),
                "description", dashboard.getDescription()
        );
        return post(PROJECT_NAME + CREATE_DASHBOARD, body).getResponse();
    }
}
