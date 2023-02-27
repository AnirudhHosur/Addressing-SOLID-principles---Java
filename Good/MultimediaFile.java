package good;


import java.awt.*;

public abstract class MultimediaFile extends TypeOfFile {
    public abstract Image resizeImage(int width, int height);
    public abstract String typeOfMultimediaFile();
    public void superType() {
        System.out.println("This is a multimedia type file");
    }
}
