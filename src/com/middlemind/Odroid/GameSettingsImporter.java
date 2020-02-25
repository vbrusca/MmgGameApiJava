package com.middlemind.Odroid;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Victor G. Brusca
 */
public class GameSettingsImporter {

    protected Hashtable<String, DatConstantsEntry> values;
    protected String version;

    protected void RunImportGameSettings(String xmlFile) throws ParserConfigurationException, SAXException, IOException, Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Load the input XML document, parse it and return an instance of the
        // Document class.
        Document document = builder.parse(new File(xmlFile));
        values = new Hashtable();
        Node nodeMain = document.getElementsByTagName("engineconfig").item(0);//document.getDocumentElement().getChildNodes();
        version = nodeMain.getAttributes().getNamedItem("version").getNodeValue();
        NodeList nodeList = nodeMain.getChildNodes();

        if (version != null && version.equals("1.0") == true) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("entry")) {
                    Element elem = (Element) node;
                    String key = node.getAttributes().getNamedItem("key").getNodeValue().toUpperCase();
                    String val = node.getAttributes().getNamedItem("val").getNodeValue();
                    String type = "int";
                    String from = "DatConstants";
                    
                    if(node.getAttributes().getNamedItem("type") != null) {
                        type = node.getAttributes().getNamedItem("type").getNodeValue().toLowerCase();
                    }
                    
                    if(node.getAttributes().getNamedItem("from") != null) {
                        from = node.getAttributes().getNamedItem("from").getNodeValue();
                    }
                    
                    DatConstantsEntry ent = new DatConstantsEntry(key, val, type, from);
                    Helper.wr("Found Key: " + key + " Value: " + val + " Type: " + type + " From: " + from);
                    values.put(from + "." + key, ent);
                }
            }
        } else {
            throw new Exception("Currently only supports version 1.0 of engine configuration files.");
        }
    }

    public boolean ImportGameSettings(String xmlFile) {
        boolean res = false;
        try {
            RunImportGameSettings(xmlFile);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }

        return res;
    }

    public Hashtable<String, DatConstantsEntry> GetValues() {
        return values;
    }

    public void SetValues(Hashtable<String, DatConstantsEntry> v) {
        values = v;
    }

    public String GetVersion() {
        return version;
    }

    public void SetVersion(String v) {
        version = v;
    }

}
