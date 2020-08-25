package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class StepsDefinitions {
    private final static Logger logger = Logger.getLogger(StepsDefinitions.class.getName());
    private String endpoint;
    private Response response;
    private RequestSpecification request;

    @Given("An endpoint exist {string}")
    public void anEndpointExist(String url) {
        endpoint = url;
        logger.info("URL is: " + endpoint);
    }

    @When("User set header content type as {string}")
    public void userSetHeaderContentTypeAs(String contentType) {
        if (contentType != null && !contentType.isEmpty()) {
            request = RestAssured.given().header("Content-Type", contentType);
            logger.info("User set header content type as: " + contentType);
        } else logger.info("Content type cannot be null or empty!");
    }

    @When("User submit the {string} request")
    public void userSubmitTheRequest(String requestMethod) {
        response = request.get(endpoint);
        logger.info("User send " + requestMethod + " method to URL: " + endpoint);
    }

    @Then("User should get a response code {string}")
    public void userShouldGetAResponseCode(String responseCode) {
        String statusCode = String.valueOf(response.getStatusCode());
        if (responseCode.equals(statusCode)) {
            Assert.assertEquals(responseCode, statusCode);
            logger.info("Status Code is: " + statusCode);
        } else {
            Assert.assertEquals(responseCode, statusCode);
            logger.info("Status Code is not as expected: " + statusCode);
        }
    }

    @And("List of rates should not be empty")
    public void listOfRatesShouldNotBeEmpty() {
        Assert.assertTrue("Response body is empty", !response.getBody().asString().isEmpty());
        logger.info("Rates list is not empty");
    }

    @Given("An URL {string} and Symbol {string} exists")
    public void anURLAndSymbolExists(String url, String symbol) {
        endpoint = url + symbol;
        logger.info("URL is: " + endpoint);
    }

    @And("User should get rate for {string}")
    public void userShouldGetRateFor(String symbol) {
        String bodyResponse = response.getBody().asString();
        Assert.assertTrue("Symbol: " + symbol + " doesn't exists in Body Response", bodyResponse.contains(symbol));
        logger.info("Body response contain symbol: " + symbol);

        JSONObject obj = new JSONObject(bodyResponse);
        double rateValue = obj.getJSONObject("rates").getDouble(symbol);
        Assert.assertTrue("Rate value is less than zero", rateValue > 0);
        logger.info("Rate for symbol: " + symbol + " is greater than 0");
    }
}
