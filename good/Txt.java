package good;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Txt extends StaticFile implements IFileProcessor {
    private String filePath;

    public Txt(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void read() {
        try {
            List<String> content = Files.readAllLines(Paths.get(getFilePath()));
            System.out.println("Reading text file");
            for(String s: content) {
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int fileSize() {
        try {
            System.out.println("File size-->");
            return (int) Files.size(Path.of(getFilePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String fileName() {
        return "textFile.txt";
    }

    @Override
    public String typeOfStaticFile() {
        return "This is text file file";
    }
}
