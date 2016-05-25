package ru.ncedu.java.tasks;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sony on 10.11.2015.
 */
public class XPathCallerImpl implements XPathCaller {
    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {
//        if (docType == "emp") {
//            Schema schema = null;
//            try {
//                schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema();
//            } catch (SAXException e) {
//                e.printStackTrace();
//            }
//            Validator validator = schema.newValidator();
//            try {
//                validator.validate(new DOMSource(src));
//            } catch (SAXException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        List<Element> res = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("//employee[@deptno="+deptno+"]");
         //   XPathExpression expr = xpath.compile("employee[@deptno="+deptno+"]");
            Object result = expr.evaluate(src, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for(int i = 0; i < nodes.getLength(); i++) {
                Node t = nodes.item(i);
                res.add((Element) t);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element[] res_el = new Element[res.size()];
        for (int i = 0; i < res.size(); i++)
            res_el[i] = res.get(i);
        return res_el;
    }

    @Override
    public String getHighestPayed(Document src, String docType) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        double res = 0;
        Node res_node = null;
        try {
            XPathExpression expr = xpath.compile("//employee/sal");
            Object result = expr.evaluate(src, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for(int i = 0; i < nodes.getLength(); i++) {
                Node t = nodes.item(i);
                String temp = t.getFirstChild().getNodeValue();
                double temp_int = Double.parseDouble(temp);
                if (temp_int > res){
                    res = temp_int;
                    res_node = t.getParentNode();
                }

            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
       // NodeList temp = res_node.getChildNodes();
       // int j = 1;
        String lastname = "";
        lastname = res_node.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
//        while(j != temp.getLength()){
//            if (temp.item(j).getLocalName() == "ename"){
//                lastname = temp.item(j).getFirstChild().getNodeValue();
//                j = temp.getLength();
//            }
//            else j++;
//        }
        return lastname;
    }

    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        double res = 0;
        Node res_node = null;
        try {
            XPathExpression expr = xpath.compile("//employee[@deptno="+deptno+"]/sal");
            Object result = expr.evaluate(src, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for(int i = 0; i < nodes.getLength(); i++) {
                Node t = nodes.item(i);
                String temp = t.getFirstChild().getNodeValue();
                double temp_int = Double.parseDouble(temp);
                if (temp_int > res){
                    res = temp_int;
                    res_node = t.getParentNode();
                }

            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        //NodeList temp = res_node.getChildNodes();
        //int j = 1;
        String lastname = "";
        lastname = res_node.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
//        while(j != temp.getLength()){
//            if (temp.item(j).getLocalName() == "ename"){
//                lastname = temp.item(j).getFirstChild().getNodeValue();
//                j = temp.getLength();
//            }
//            else j++;
//        }
        return lastname;
    }

    @Override
    public Element[] getTopManagement(Document src, String docType) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        List<Element> res = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("employee[job!='MANAGER']/employee[job='MANAGER']");
            Object result = expr.evaluate(src, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for(int i = 0; i < nodes.getLength(); i++) {
                Node t = nodes.item(i);
                res.add((Element)t);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element[] res_el = new Element[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res_el[i] = res.get(i);
            System.out.println(res_el[i].getFirstChild().getNextSibling().getFirstChild().getNodeValue());
        }
        return res_el;
    }

    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        List<Element> res = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("//employee[not(employee)]");
            Object result = expr.evaluate(src, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for(int i = 0; i < nodes.getLength(); i++) {
                Node t = nodes.item(i);
                res.add((Element) t);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element[] res_el = new Element[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res_el[i] = res.get(i);
           // System.out.println(res_el[i].getFirstChild().getNextSibling().getFirstChild().getNodeValue());
        }
        return res_el;
    }

    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        List<Element> res = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("//employee[@empno="+empno+"]/../employee[@empno!="+empno+"]");
            Object result = expr.evaluate(src, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for(int i = 0; i < nodes.getLength(); i++) {
                Node t = nodes.item(i);

                res.add((Element) t);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Element[] res_el = new Element[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res_el[i] = res.get(i);
         //   System.out.println(res_el[i].getFirstChild().getNextSibling().getFirstChild().getNodeValue());
        }
        return res_el;
    }
}
