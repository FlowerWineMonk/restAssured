package steps.patch;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import steps.SharedContext;
import utils.LoggerHelper;

import static io.restassured.RestAssured.given;

public class PatchUserSteps {
    private static final Logger logger = LoggerHelper.getLogger(PatchUserSteps.class);
    private final SharedContext context;

    public PatchUserSteps(SharedContext context) {
        this.context = context;
    }

    @When("I send a PATCH request to {string} with body:")
    public void iSendAPatchRequestToWithBody(String endpoint, String body) {
        logger.info("Sending PATCH request to endpoint: {} with body: {}", endpoint, body);

        Response response = given()
                .spec(RestAssured.requestSpecification)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .extract()
                .response();

        context.setResponse(response);
        logger.info("Received response with status code: {}", response.statusCode());
    }
}

