package adapters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApi {

    static final String BASE_URL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));
    static final String TOKEN = System.getProperty("token", PropertyReader.getProperty("token"));

    public static RequestSpecification getAuthenticatedSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL.concat("/api/v1"))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + TOKEN)
                .build();
    }

    public ResponseWrapper post(String endpoint, Map<String, Object> body) {
        return new ResponseWrapper(given()
                .spec(getAuthenticatedSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract().response());
    }

    public ResponseWrapper delete(String endpoint) {
        return new ResponseWrapper(given()
                .spec(getAuthenticatedSpec())
                .when()
                .delete(endpoint)
                .then()
                .extract().response());
    }

    public ResponseWrapper get(String endpoint) {
        return new ResponseWrapper(given()
                .spec(getAuthenticatedSpec())
                .when()
                .get(endpoint)
                .then()
                .extract().response());
    }
}
