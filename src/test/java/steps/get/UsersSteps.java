package steps.get;

import io.cucumber.java.en.And;
import io.restassured.response.Response;
import steps.SharedContext;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class UsersSteps {
    private final SharedContext context;

    public UsersSteps(SharedContext context) {
        this.context = context;
    }

    @And("the response should contain a list under {string}")
    public void theResponseShouldContainAListUnder(String key) {
        Response response = context.getResponse();
        List<Map<String, Object>> users = response.jsonPath().getList(key);
        assertNotNull("List under '" + key + "' should not be null", users);
        assertFalse("List under '" + key + "' should not be empty", users.isEmpty());
    }

    @And("each object in {string} should have the fields:")
    public void eachObjectShouldHaveTheFields(String key, List<String> fields) {
        Response response = context.getResponse();
        List<Map<String, Object>> users = response.jsonPath().getList(key);

        for (Map<String, Object> user : users) {
            for (String field : fields) {
                assertTrue("Field '" + field + "' missing in user: " + user,
                        user.containsKey(field));
            }
        }
    }

    @And("one of the users should have the email {string}")
    public void oneOfTheUsersShouldHaveTheEmail(String expectedEmail) {
        Response response = context.getResponse();
        List<Map<String, Object>> users = response.jsonPath().getList("data");

        boolean emailFound = users.stream()
                .anyMatch(user -> expectedEmail.equals(user.get("email")));

        assertTrue("Expected email not found: " + expectedEmail, emailFound);
    }
}