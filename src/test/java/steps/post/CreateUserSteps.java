package steps.post;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import steps.SharedContext;
import utils.LoggerHelper;

import static io.restassured.RestAssured.given;

public class CreateUserSteps {
    private static final Logger logger = LoggerHelper.getLogger(CreateUserSteps.class);
    private final SharedContext context;

    public CreateUserSteps(SharedContext context) {
        this.context = context;
    }

    @When("I send a POST request to {string} with body:")
    public void iSendAPOSTRequestToWithBody(String endpoint, String body) {
        logger.info("Sending POST request to endpoint: {} with body: {}", endpoint, body);

        Response response = given()
                .spec(RestAssured.requestSpecification)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        context.setResponse(response);
        logger.info("Received response with status code: {}", response.statusCode());
    }
}
