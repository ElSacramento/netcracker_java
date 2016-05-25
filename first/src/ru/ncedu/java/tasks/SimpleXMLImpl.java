package ru.ncedu.java.tasks;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by Sony on 10.11.2015.
 */
public class SimpleXMLImpl implements SimpleXML {
    @Override
    public String createXML(String tagName, String textNode) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(tagName);
        rootElement.appendChild(doc.createTextNode(textNode));
        doc.appendChild(rootElement);



        // write the content into xml file
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        StringWriter writer = new StringWriter();
        try {
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        String output = writer.getBuffer().toString();

        return output;
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        final String[] result = {""};
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler(){
                String root = "";
                boolean flag = false;

                public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
                    if (!name.equals("") && !flag) {
                        root = name;
                        flag = true;
                    }
                }

                public void endDocument() throws SAXException{
                    result[0] = root;
                }
            };

            Reader reader = new InputStreamReader(xmlStream);
            InputSource is = new InputSource(reader);
            saxParser.parse(is, handler);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            throw new SAXException();
        }
        return result[0];
    }
}
