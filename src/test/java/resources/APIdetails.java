package resources;

public enum APIdetails {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    UpdatePlaceAPI("/maps/api/place/update/json");

    private String resource;

    APIdetails(String resource){
       this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
