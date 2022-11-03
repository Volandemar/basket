import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class Setting {
    String load;
    String loadFileName;
    String loadFileFormat;
    String save;
    String saveFileName;
    String saveFileFormat;
    String log;
    String logFileName;


    public void loadSetting() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("src/shop.xml"));
        XPath allXPath = XPathFactory.newInstance().newXPath();
        Node nodeLoadEnabled = (Node) allXPath.evaluate("/config/load/enabled", doc, XPathConstants.NODE);
        load = nodeLoadEnabled.getTextContent();
        Node nodeLoadFileName = (Node) allXPath.evaluate("/config/load/fileName", doc, XPathConstants.NODE);
        loadFileName = nodeLoadFileName.getTextContent();
        Node nodeLoadFileFormat = (Node) allXPath.evaluate("/config/load/format", doc, XPathConstants.NODE);
        loadFileFormat = nodeLoadFileFormat.getTextContent();

        Node nodeSaveEnabled = (Node) allXPath.evaluate("/config/save/enabled", doc, XPathConstants.NODE);
        save = nodeSaveEnabled.getTextContent();
        Node nodeSaveFileName = (Node) allXPath.evaluate("/config/save/fileName", doc, XPathConstants.NODE);
        saveFileName = nodeSaveFileName.getTextContent();
        Node nodeSaveFileFormat = (Node) allXPath.evaluate("/config/save/format", doc, XPathConstants.NODE);
        saveFileFormat = nodeSaveFileFormat.getTextContent();

        Node nodeLogEnabled = (Node) allXPath.evaluate("/config/log/enabled", doc, XPathConstants.NODE);
        log = nodeLogEnabled.getTextContent();
        Node nodeLogFileName = (Node) allXPath.evaluate("/config/log/fileName", doc, XPathConstants.NODE);
        logFileName = nodeLogFileName.getTextContent();



    }


}
