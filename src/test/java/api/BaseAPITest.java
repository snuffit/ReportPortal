package api;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import steps.api.DashboardAPIStep;

public class BaseAPITest {

    protected static DashboardAPIStep dashboardAPIStep;
    protected static Faker faker = new Faker();
    protected static SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        dashboardAPIStep = new DashboardAPIStep();
        softAssert = new SoftAssert();
    }
}
