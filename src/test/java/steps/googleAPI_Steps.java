package steps;

import POJO.AddPlaceAPI;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.Request;
import resources.APIdetails;
import resources.Utils.basicUtil;
import resources.googleAPIPayload;

import static io.restassured.RestAssured.given;

import resources.Utils.requestSpecificationUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

public class googleAPI_Steps {

    googleAPIPayload googleAPIPages = new googleAPIPayload();
    AddPlaceAPI payload = new AddPlaceAPI();
    private  String otherPayload;
    private String response;
    requestSpecificationUtil reqSpec = new requestSpecificationUtil();
    RequestSpecification request;
    basicUtil basicUtil = new basicUtil();
    static String placeId;
    /*Use static when you want this value extracted from one scenario and pass it to another scenario.
     So it remembers value from last scenario(When you execute multiple scenarios).
     If we use public, after a scenario is executed, the value will be reset to null
        */
    public googleAPI_Steps() throws FileNotFoundException {
    }

    @Given("^User has Payload to POST$")
    public void userPayloadToPOST() {
        payload = googleAPIPages.payloadToPost();
    }

    @When("User make a {string} call to the {string}")
    public void userMakeACallToThe(String arg0, String api) throws IOException {
        RestAssured.baseURI= "https://rahulshettyacademy.com/";
//        Response res = given().log().all()
//                .queryParam("key","qaclick123")
//                .body(payload)
//                .when()
//                .post(api)
//                .then().log().all()
//                .extract().response();

        //Can use util file here instead of above - src/test/java/Utils/requestSpecification.java

        Response res = given().spec(reqSpec.requestSpecification())
                .body(payload)
                .when()
                .post(api)
                .then().log().all()
                .extract().response();


        response = res.asString();
    }

    @When("User make a {string} call to the {string} using enum class")
    public void userMakeACallToTheUsingEnumClass(String call, String resource) throws IOException {
        APIdetails APIInfo = APIdetails.valueOf(resource);
        if (call.equalsIgnoreCase("POST") && !resource.contains("Delete")) {
            request = given().spec(reqSpec.requestSpecification()).body(payload);
            response = request.when().post(APIInfo.getResource()).then().extract().response().asString();
        } else if (call.equalsIgnoreCase("GET")) {
            request = given().spec(reqSpec.requestSpecification()).queryParam("place_id",placeId);
            response = request.when().get(APIInfo.getResource()).then().extract().response().asString();
        } else if (resource.contains("Delete")) {
            request = given().spec(reqSpec.requestSpecification()).body(otherPayload);
            response = request.when().post(APIInfo.getResource()).then().extract().response().asString();
        }
        System.out.println(call +" response: "+response);
    }

    @Then("Validate the {string} response is {string}")
    public void validateTheResponseIs(String param, String expected) {
//        JsonPath jp = new JsonPath(response);
//        Assert.assertEquals(jp.get(param),res);
        String actualResult = basicUtil.jsonPathResp(response,param);
        Assert.assertEquals(actualResult,expected);
    }

    @Given("User has Payload to POST with {string}, {string} and {string}")
    public void userHasPayloadToPOSTWithAnd(String name, String lang, String address) {
        payload = googleAPIPages.payloadToPostee(name,lang,address);
    }

    @Then("get the {string} from response")
    public void getTheFromResponse(String key) {
        placeId = basicUtil.jsonPathResp(response,key);
    }

    @And("User has Payload to Delete")
    public void userHasPayloadToDelete() {
        otherPayload = googleAPIPages.payloadToDelete(placeId);
        System.out.println("other payload: "+ otherPayload);
    }
}
