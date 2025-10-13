package steps.put;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import steps.SharedContext;
import utils.LoggerHelper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PutUserSteps {

    private static final Logger logger = LoggerHelper.getLogger(PutUserSteps.class);
    private final SharedContext context;

    public PutUserSteps(SharedContext context) {
        this.context = context;
    }

    @When("I send a PUT request to {string} with body:")
    public void iSendAPUTRequestToWithBody(String endpoint, String requestBody) {
        logger.info("Sending PUT request to endpoint: {}", endpoint);
        logger.info("Request Body: {}", requestBody);

        Response response = given()
                .spec(RestAssured.requestSpecification)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();

        context.setResponse(response);
        logger.info("Received response with status code: {}", response.statusCode());
    }

    @And("the response body should contain:")
    public void theResponseBodyShouldContain(String expectedBody) {
        Response response = context.getResponse();
        JSONObject expectedJson = new JSONObject(expectedBody);
        JSONObject actualJson = new JSONObject(response.getBody().asString());

        for (String key : expectedJson.keySet()) {
            String expectedValue = expectedJson.getString(key);
            String actualValue = actualJson.optString(key, null);

            logger.info("Validating key '{}' -> expected: '{}', actual: '{}'", key, expectedValue, actualValue);
            assertEquals(String.format("Expected key '%s' to have value '%s', but got '%s'", key, expectedValue, actualValue), expectedValue, actualValue);
        }
    }

    @And("the response should contain a field {string}")
    public void theResponseShouldContainAField(String fieldName) {
        Response response = context.getResponse();
        JSONObject actualJson = new JSONObject(response.getBody().asString());

        logger.info("Checking if response contains field: {}", fieldName);
        assertTrue(
                String.format("Expected response to contain field '%s'", fieldName),
                actualJson.has(fieldName)
        );
    }
}
