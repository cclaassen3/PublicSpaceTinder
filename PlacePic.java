public class PlacePic {
    
    private latitude;
    private longitude;
    private imageFile;

    public PlacePic(double latitude, double longitude, String imageFile) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageFile = imageFile;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImageFile(double imageFile) {
        this.imageFile = imageFile;
    }
}