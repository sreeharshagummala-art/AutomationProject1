package steps;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Hooks {

    /*
    This is an example to demonstrate how Hooks works.
    @DeletePlace scenario depends on place_id value from @AddPlace scenario. @DeletePlace will work if @AddPlace is executed as well.
    But if we want to execute @DeletePlace alone, place_id will be null and scenario fails
    So we use @Before and see if place_id is null, then we add a place just as @AddPlace and get a place_id
     */
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        googleAPI_Steps stepdef = new googleAPI_Steps();

        if (googleAPI_Steps.placeId == null) {
            stepdef.userHasPayloadToPOSTWithAnd("Sree", "Telugu", "1111 Richardson Dr");
            stepdef.userMakeACallToThe("POST", "/maps/api/place/add/json");
            stepdef.validateTheResponseIs("status", "OK");
            stepdef.getTheFromResponse("place_id");
        }

    }
}
