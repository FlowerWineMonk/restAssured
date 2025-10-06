package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class UserSteps {
  private SharedContext context;
  private String baseUrl;

  public UserSteps(SharedContext context) {
    this.context = context;
  }

  @Given("the base API is {string}")
  public void theBaseAPIIs(String baseUrl) {
    // Write code here that turns the phrase above into concrete actions
    this.baseUrl = baseUrl;
  }

  @When("I send a GET request to {string}")
  public void iSendAGETRequestTo(String endpoint) {
    // Write code here that turns the phrase above into concrete actions
    Response response = given().when().get(baseUrl + endpoint);
    context.setResponse(response);
  }

  @And("the response should contain {string}")
  public void theResponseShouldContain(String key) {
    Response response = context.getResponse();
    String value = response.jsonPath().getString("data.email");
    assertNotNull(value, key + " should not be null");
  }
}
