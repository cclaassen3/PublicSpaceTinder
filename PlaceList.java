public class PlaceList {
    
    private double latitude;
    private double longitude;
    private String username;

    public PlaceList(double latitude, double longitude, String username) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}