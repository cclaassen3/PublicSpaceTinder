public class Place {
    
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String city;
    private String filter; //the type of public space

    public Place(String name, String address, String latitude,
        String longitude, String city, String filter) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.filter = filter;
    }

    public void setName(String string) {
        this. = string;
    }

    public void setAddress(String string) {
        this.address = string;
    }

    public void setLatitude(double number) {
        this.latitude = number;
    }

    public void setLongitude(double number) {
        this.longitude = number;
    }

    public void setCity(String string) {
        this.city = string;
    }

    public void setFilter(String string) {
        this.filter = string;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String setCity() {
        return city;
    }

    public void getFilter() {
        return filter;
    }
}