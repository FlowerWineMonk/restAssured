package steps.get;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import steps.SharedContext;

import static io.restassured.RestAssured.given;

public class BaseUrlSteps {
    private final SharedContext context;
    private String baseUrl;

    public BaseUrlSteps(SharedContext context) {
        this.context = context;
    }

    @Given("the API base URL is loaded from configuration")
    public void theApiBaseUrlIsLoadedFromConfiguration() {
        baseUrl = ConfigReader.get("BASE_URL");
    }

    @When("I send a GET request to the base URL")
    public void iSendAGetRequestToTheBaseUrl() {
        Response response = given()
                .spec(RestAssured.requestSpecification)
                .when()
                .get(baseUrl)
                .then()
                .extract()
                .response();

        context.setResponse(response);
    }
}
