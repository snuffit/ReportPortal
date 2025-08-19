package adapters;

import groovy.util.logging.Log4j2;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log4j2
public class ResponseWrapper {

    private static final Logger log = LoggerFactory.getLogger(ResponseWrapper.class);
    private Response response;

    public ResponseWrapper(Response response) {
        this.response = response;
        logAndAttachResponse();
    }

    private void logAndAttachResponse() {
        String body = response.asPrettyString();
        int status = response.getStatusCode();
        String contentType = response.getContentType();
        log.info("Response Status: {}", status);
        log.info("Response Content-Type: {}", contentType);
        log.info("Response Body:\n{}", body);
        Allure.addAttachment("Response Status", String.valueOf(status));
        Allure.addAttachment("Response Content-Type", contentType);
        Allure.addAttachment("Response Body", "application/json", body, ".json");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public Response getResponse() {
        return response;
    }
}
