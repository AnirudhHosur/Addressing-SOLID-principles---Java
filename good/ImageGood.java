package good;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageGood extends MultimediaFile implements IFileProcessor {

    private String imagePath;

    public ImageGood(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void read() {
        try {
            BufferedImage image = ImageIO.read(new File(getImagePath()));
            System.out.println("Image successfully read " + image.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image resizeImage(int width, int height) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(getImagePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    @Override
    public String typeOfMultimediaFile() {
        return "This is an image type file";
    }

    @Override
    public int fileSize() {
        try {
            System.out.println("File size-->");
            return (int) Files.size(Path.of(getImagePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String fileName() {
        return "testImage.jpg";
    }

}
