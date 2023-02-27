package good;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CsvGood extends StaticFile implements IFileProcessor {
    private String filePath;

    public CsvGood(String filePath) {
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
        Scanner sc = null;
        try {
            sc = new Scanner(new File(getFilePath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc.useDelimiter(",");
        System.out.println("Reading CSV File....");
        while (sc.hasNext()) {
            System.out.print(sc.next());
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
        return "csvFile.csv";
    }


    @Override
    public String typeOfStaticFile() {
        return "This is a CSV type file";
    }
}
