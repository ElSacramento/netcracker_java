package ru.ncedu.java.tasks;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by Sony on 13.07.2015.
 */

public class Main {
    public static void main(String[] args) {
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.txt"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String[] a = new RussianNumbers().createMillion();
            for (int i = 1; i < a.length; i++) {
                    String st = "<item>" + a[i] + "</item>" + "\n";
                    out.write(st);

            }
            //out.write(new RussianNumbers().toString(3720));
        }
        catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//        XPathCaller test = new XPathCallerImpl();
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        factory.setNamespaceAware(true); // never forget this!
//        DocumentBuilder builder = null;
//        Document doc = null;
//        String s;
//        try {
//            builder = factory.newDocumentBuilder();
//            doc = builder.parse("C:\\Users\\Sony\\Desktop\\netcracker\\XPathCaller\\emp-hier.xml");
//            //test.getOrdinaryEmployees(doc,"emp-hier");
//            test.getTopManagement(doc, "emp-hier");
//            //test.getEmployees(doc, "10", "emp");
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Transformer transformer = null;
//        try {
//            transformer = TransformerFactory.newInstance().newTransformer();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        }
//
//        StringWriter writer = new StringWriter();
//        try {
//            transformer.transform(new DOMSource(doc), new StreamResult(writer));
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
//        String output = writer.getBuffer().toString();

    }
}
