package steps.api;

import adapters.BaseApi;
import dto.api.Dashboard;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
public class DashboardAPIStep extends BaseApi {

    final String DASHBOARD_ENDPOINT = "/dashboard",
    PROJECT_NAME = "/default_personal";

    @Step("Create dashboard")
    public Response createDashboard(Dashboard dashboard) {
        log.info("Create Dashboard with name '{}'", dashboard.getName());
        Map<String, Object> body = Map.of(
                "name", dashboard.getName(),
                "description", dashboard.getDescription()
        );
        return post(PROJECT_NAME + DASHBOARD_ENDPOINT, body).getResponse();
    }

    @Step("Delete dashboard")
    public Response deleteDashboard(Dashboard dashboard) {
        String id = getDashboardID(dashboard.getName());
        log.info("Delete dashboard with id '{}' by API", id);
        return delete(String.format("%s%s/%s", PROJECT_NAME, DASHBOARD_ENDPOINT, id)).getResponse();
    }

    @Step("Get dashboard ID by name")
    public String getDashboardID(String dashboardName) {
        log.info("Get dashboards by API");
        Response response = get(PROJECT_NAME + DASHBOARD_ENDPOINT).getResponse();
        List<Map<String, Object>> dashboards = response.jsonPath().getList("content");
        for (Map<String, Object> dashboard : dashboards) {
            if (dashboard.get("name").toString().contains(dashboardName)) {
                String id = dashboard.get("id").toString();
                log.info("Project with name '{}' has id '{}'", dashboardName, id);
                return id;
            }
        }
        return null;
    }
}
