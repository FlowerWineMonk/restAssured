package steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonSteps {
    private final SharedContext context;

    public CommonSteps(SharedContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer expectedStatus) {
        Response response = context.getResponse();
        response.then().statusCode(expectedStatus);
    }

    @Then("the response status code should match the expected one")
    public void theResponseStatusCodeShouldMatchExpectedOne() {
        Response response = context.getResponse();
        assertThat(response.statusCode(), equalTo(200));
    }
}
