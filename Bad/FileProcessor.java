package bad;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Violating single responsibility principle as -> Two different functionalities
public class FileProcessor {

    public void readMultipleFiles(List<Object> files) throws IOException {
        String name = "", contactNumber = "", address = "";
        for (int i = 0; i < files.size(); i++) {
            Object file = files.get(i);
            if (file instanceof Csv) {
                Scanner sc = new Scanner(new File(((Csv) file).getFilePath()));
                sc.useDelimiter(",");
                System.out.println("From CSV File");
                while (sc.hasNext()) {
                    System.out.print(sc.next() + " ");
                }
            } else if (file instanceof Xml) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                try {
                    // safe xml parsing
                    dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(new File(((Xml) file).getFilePath()));
                    doc.getDocumentElement().normalize();
                    System.out.println("From XML File");
                    System.out.println("Root Element-" + doc.getDocumentElement().getNodeName());
                    NodeList list = doc.getElementsByTagName("person");
                    for (int j = 0; j < list.getLength(); j++) {
                        Node node = list.item(j);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            name = element.getElementsByTagName("name").item(0).getTextContent();
                            contactNumber = element.getElementsByTagName("contactNumber").item(0).getTextContent();
                            address = element.getElementsByTagName("address").item(0).getTextContent();
                        }
                    }
                    System.out.println("Name-" + name + " Contact number-" + contactNumber + " Address-" + address);
                } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
                } catch (SAXException e) {
                    throw new RuntimeException(e);
                }
            } else if (file instanceof ImageFile) {
                BufferedImage image = ImageIO.read(new File(((ImageFile) file).getFilePath()));
                System.out.println("Image successfully read" + image);
            }
        }
    }

    public String typeOfStaticFiles(List<Object> files) {
        for (int i = 0; i < files.size(); i++) {
            Object file = files.get(i);
            if (file instanceof Csv) {
                System.out.println("CSV file found!");
            } else if (file instanceof Xml) {
                System.out.println("XML file found!");
            }
            // Violation of liskov substitution
            else if (file instanceof ImageFile) {
                throw new IllegalStateException("Not a static file");
            } else {
                System.out.println("Other files found!");
            }
        }
        return "Files found";
    }

    public void resizeImage(ImageFile imageFile) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imageFile.getFilePath()));
            Image image = bufferedImage.getScaledInstance(800, 500, Image.SCALE_DEFAULT);
            System.out.println(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
