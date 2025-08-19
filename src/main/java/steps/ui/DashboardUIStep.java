package steps.ui;

import dto.api.Dashboard;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import steps.api.DashboardAPIStep;

public class DashboardUIStep {

    WebDriver driver;
    DashboardPage dashboardPage;
    DashboardAPIStep dashboardAPIStep;

    public DashboardUIStep(WebDriver driver) {
        this.driver = driver;
        dashboardPage = new DashboardPage(driver);
        dashboardAPIStep = new DashboardAPIStep();
    }

    public void openDashboardPage(Dashboard dashboard) {
        dashboardPage
                .open(dashboardAPIStep.getDashboardID(dashboard.getName()))
                .isPageOpened();
    }
}
