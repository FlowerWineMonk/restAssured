package steps.delete;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.apache.logging.log4j.Logger;
import steps.SharedContext;
import utils.LoggerHelper;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteUserSteps {
    private static final Logger logger =LoggerHelper.getLogger(DeleteUserSteps.class);
    private final SharedContext context;

    public DeleteUserSteps(SharedContext context) {
        this.context = context;
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        logger.info("Sending DELETE request to endpoint: {}", endpoint);
        Response response = given()
                .spec(RestAssured.requestSpecification)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        context.setResponse(response);
        logger.info("Received response with status code: {}", response.statusCode());
    }
}
