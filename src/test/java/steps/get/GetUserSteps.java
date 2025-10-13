package steps.get;

import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import steps.SharedContext;
import utils.LoggerHelper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class GetUserSteps {
    private static final Logger logger = LoggerHelper.getLogger(GetUserSteps.class);
    private final SharedContext context;

    public GetUserSteps(SharedContext context) {
        this.context = context;
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        Response response = given()
                .spec(RestAssured.requestSpecification)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        logger.info("Status code: {}", response.statusCode());
        context.setResponse(response);
    }

    @And("the response should contain the field {string}")
    public void theResponseShouldContainField(String fieldName) {
        Response response = context.getResponse();
        Object value = response.jsonPath().get("data." + fieldName);
        assertNotNull(fieldName + " should not be null", value);
    }
}
