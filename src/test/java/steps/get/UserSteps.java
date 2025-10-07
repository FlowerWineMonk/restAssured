package steps.get;

import config.ConfigReader;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import steps.SharedContext;
import utils.LoggerHelper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class UserSteps {
    private static final Logger logger = LoggerHelper.getLogger(ConfigReader.class);
    private final SharedContext context;
    private final String baseUrl = ConfigReader.get("BASE_URL");

    public UserSteps(SharedContext context) {
        this.context = context;
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        String fullUrl = baseUrl + endpoint;
        logger.info("Sending GET to: {}", fullUrl);

        Response response = given()
                .when()
                .get(baseUrl + endpoint)
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
