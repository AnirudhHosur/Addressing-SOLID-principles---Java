package bad;

// Liskov substitution:
public class ImageFile {
    private String filePath;

    public ImageFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
