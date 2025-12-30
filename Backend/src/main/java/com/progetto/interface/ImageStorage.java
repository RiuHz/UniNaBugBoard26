
public interface ImageStorage {
    byte[] retrieveImage(String url);
    String saveImage(byte[] imageData);
}
