package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseUrlSteps {
  private final SharedContext context;
  private String baseUrl;

  public BaseUrlSteps(SharedContext context) {
    this.context = context;
  }

  @Given("the base API URL is {string}")
  public void theBaseAPIURLIs(String baseUrl) {
    // Write code here that turns the phrase above into concrete actions
    this.baseUrl = baseUrl;
  }

  @When("I send a GET request to the base URL")
  public void iSendAGETRequestToTheBaseURL() {
    // Write code here that turns the phrase above into concrete actions
    Response response = given().when().get(baseUrl);
    context.setResponse(response);
  }
}
