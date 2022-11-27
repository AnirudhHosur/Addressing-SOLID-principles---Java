package good;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlGood extends StaticFile implements IFileProcessor {
    private String filePath;

    public XmlGood(String filePath) {
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
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // safe xml parsing
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(getFilePath()));
            doc.getDocumentElement().normalize();
            System.out.println("Reading XML file");
            System.out.println("Root Element -->" + doc.getDocumentElement().getNodeName());
            NodeList list = doc.getElementsByTagName("person");
            for (int j = 0; j < list.getLength(); j++) {
                Node node = list.item(j);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Name " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Contact Number " + element.getElementsByTagName("contactNumber").item(0).getTextContent());
                    System.out.println("Address " + element.getElementsByTagName("address").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
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
        return "xmlFile.xml";
    }

    @Override
    public String typeOfStaticFile() {
        return "This is an XML type file";
    }
}
