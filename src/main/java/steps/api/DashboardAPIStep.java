package steps.api;

import adapters.BaseApi;
import adapters.ResponseWrapper;
import dto.api.Dashboard;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
public class DashboardAPIStep extends BaseApi {

    final String DASHBOARD_ENDPOINT = "/dashboard",
            PROJECT_NAME = "/default_personal",
            URI = PROJECT_NAME + DASHBOARD_ENDPOINT;

    @Step("Create dashboard")
    public ResponseWrapper createDashboard(Dashboard dashboard) {
        log.info("Create Dashboard with name '{}'", dashboard.getName());
        Map<String, Object> body = Map.of(
                "name", dashboard.getName(),
                "description", dashboard.getDescription()
        );
        return post(URI, body);
    }

    @Step("Delete dashboard")
    public ResponseWrapper deleteDashboard(Dashboard dashboard) {
        String id = getDashboardID(dashboard.getName());
        log.info("Delete dashboard with id '{}' by API", id);
        return delete(String.format("%s/%s", URI, id));
    }

    @Step("Get dashboard ID by name")
    public String getDashboardID(String dashboardName) {
        log.info("Get dashboards by API");
        List<Map<String, Object>> dashboards = getAllDashboards().getResponse()
                .jsonPath()
                .getList("content");
        for (Map<String, Object> dashboard : dashboards) {
            if (dashboard.get("name").toString().equals(dashboardName)) {
                String id = dashboard.get("id").toString();
                log.info("Project with name '{}' has id '{}'", dashboardName, id);
                return id;
            }
        }
        log.warn("Project with name '{}' hasn't id", dashboardName);
        return null;
    }

    @Step("Get all dashboards")
    public ResponseWrapper getAllDashboards() {
        return get(URI);
    }

    @Step("Checking if Dashboard is created")
    public boolean isDashboardCreated(Dashboard dashboard) {
        if (getDashboardID(dashboard.getName()) != null) {
            log.info("Dashboard is created");
            return true;
        }
        log.warn("Dashboard isn't created");
        return false;
    }
}
