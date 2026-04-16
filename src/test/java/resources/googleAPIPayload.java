package resources;

import POJO.AddPlaceAPI;
import POJO.LocationAddPlaceAPI;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class googleAPIPayload {

    AddPlaceAPI payload = new AddPlaceAPI();
    private String response;

    public AddPlaceAPI payloadToPost(){

        payload.setAccuracy(50);
        payload.setAddress("29, side layout, cohen 09");
        payload.setLanguage("French-IN");
        payload.setName("Frontline house");
        payload.setPhone_number("(+91) 983 893 3937");
        payload.setWebsite("http://google.com");

        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");

        payload.setTypes(typeList);

        LocationAddPlaceAPI location = new LocationAddPlaceAPI();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        payload.setLocation(location);
        return payload;
    }

    public AddPlaceAPI payloadToPostee(String name, String lang, String address){

        payload.setAccuracy(50);
        payload.setAddress(address);
        payload.setLanguage(lang);
        payload.setName(name);
        payload.setPhone_number("(+91) 983 893 3937");
        payload.setWebsite("http://google.com");

        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");

        payload.setTypes(typeList);

        LocationAddPlaceAPI location = new LocationAddPlaceAPI();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        payload.setLocation(location);
        return payload;
    }

    public String payloadToDelete(String placeId){
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }

}
