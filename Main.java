import bad.Csv;
import bad.FileProcessor;
import bad.ImageFile;
import bad.Xml;
import good.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Violating SOLID principles code -->");
        Xml xmlBad = new Xml("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\xmlFile.xml");
        Csv csvBad = new Csv("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\csvFile.csv");
        ImageFile imageFileBad = new ImageFile("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\testImage.jpg");
        List<Object> filesBad = List.of(csvBad, imageFileBad, xmlBad);
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readMultipleFiles(filesBad);
        //fileProcessor.typeOfStaticFiles(filesBad); WILL THROW EXCEPTION
        fileProcessor.resizeImage(imageFileBad);
        System.out.println("-------------------------------------------------------------------");

        System.out.println("Adhering to SOLID principles code -->");
//        Liskov substitution: Entity can be substitutable with the base class
//        CsvGood csv = new CsvGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\csvFile.csv");
//        ImageGood image = new ImageGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\testImage.jpg");
//        XmlGood xml = new XmlGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\xmlFile.xml");
//        Txt txt = new Txt("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\textFile.txt");
        IFileProcessor csv = new CsvGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\csvFile.csv");
        IFileProcessor image = new ImageGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\testImage.jpg");
        IFileProcessor xml = new XmlGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\xmlFile.xml");
        IFileProcessor txt = new Txt("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\textFile.txt");
        List<IFileProcessor> files = List.of(csv, xml, image, txt);

        FileProcessorGood fileProcessorGood = new FileProcessorGood();
        fileProcessorGood.readMultipleFiles(files);

        System.out.println("STATIC FILE");
        // Replacing TypeOfFile with StaticFile works
        //StaticFile csvGoodInheritanceExample = new CsvGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\csvFile.csv");
        TypeOfFile csvGoodInheritanceExample = new CsvGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\csvFile.csv");
        System.out.println("Output from Inheritance class's object:");
        csvGoodInheritanceExample.superType();
        System.out.println(csvGoodInheritanceExample.fileName());
        System.out.println(csvGoodInheritanceExample.fileSize());
        CsvGood csvGood = new CsvGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\csvFile.csv");
        System.out.println("Output from entity's object:");
        System.out.println(csvGood.typeOfStaticFile());
        System.out.println(csvGood.fileName());
        System.out.println(csvGood.fileSize());
        csvGood.read();

        // The implementation for TXT and XML will be same as CSV. Therefore, not implemented here.

        System.out.println("MULTIMEDIA FILE");
        // Replacing TypeOfFile with MultimediaFile works
        //MultimediaFile imageGoodInheritanceExample = new ImageGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\testImage.jpg");
        TypeOfFile imageGoodInheritanceExample = new ImageGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\testImage.jpg");
        System.out.println("Output from Inheritance class's object:");
        imageGoodInheritanceExample.superType();
        System.out.println(imageGoodInheritanceExample.fileSize());
        System.out.println(imageGoodInheritanceExample.fileName());
        ImageGood imageGood = new ImageGood("C:\\Users\\aniru\\IdeaProjects\\A2\\src\\testImage.jpg");
        System.out.println("Output from entity's object:");
        System.out.println(imageGood.typeOfMultimediaFile());
        System.out.println(imageGood.fileSize());
        System.out.println(imageGood.fileName());
        System.out.println(imageGood.resizeImage(200, 100));
        imageGood.read();
    }
}