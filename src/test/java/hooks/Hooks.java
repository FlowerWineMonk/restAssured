package hooks;

import config.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;

public class Hooks {
    private final String baseUrl = ConfigReader.get("BASE_URL");
    private final String apiKey = ConfigReader.get("API_KEY");

    @Before
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("x-api-key", apiKey)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}