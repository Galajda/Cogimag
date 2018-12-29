/*
 * Copyright (C) 2018 MichalG HP Envy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cogimag.android.xml;


/**
 *
 * @author MichalG HP Envy
 * Need to install NetBeans Android plugin at 
 * http://plugins.netbeans.org/plugin/19545/nbandroid this looks like a scam
 * 
 * 
 */

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
//add android library from C:\Users\me\Downloads\Android\BareSDK\platforms\android-28\android.jar

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class XpathLayoutParser {
    
    private static final String LOG_TAG = "ByteStreamParser";
    private XPath xPath;
//    private InputSource inputSource;
    private final Context context;
    private final int resourceId;
    private final String xmlAsString;
    Document layoutDoc;

    public XpathLayoutParser(Context c,int layoutResourceId) {
        xPath = XPathFactory.newInstance().newXPath();
        context = c;
        resourceId = layoutResourceId;
        xmlAsString = getXmlAsString();
    }
    
    public Node getNodeFromId(int id) {
        InputSource source = getInputSource(); //may be null
        String selectorExpr = "//*[@id='@" + id + "']";
        Node theNode = null;
        try {
            theNode = (Node) xPath.evaluate(selectorExpr, source,XPathConstants.NODE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "cannot get node from id. msg: " + ex.getMessage());
        }
        return theNode;
    }
    public int getIdFromXPath(String path) {
        int id= -1;

        try {
            Node node = getNodeFromXPath(path);
//            NamedNodeMap attrList = node.getAttributes();
//            String idString = node.getAttributes().getNamedItem("id").getNodeValue().substring(1);
            id = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue().substring(1));
            /*for (int i =0; i < attrList.getLength(); i++) {
                if ("id".equals(attrList.item(i).getNodeName())) {
//                    Log.i(LOG_TAG, "second text view id: " + attrList.item(i).getNodeValue());
//                    id = Integer.parseInt(attrList.item(i).getNodeValue().substring(1));
                    id = Integer.parseInt(attrList.item(i).getNodeValue().substring(1));
                }
            }*/
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "cannot get id from xpath. msg: " + ex.getMessage());
        }
        return id;
    }
    public Node getNodeFromXPath(String path) {
        InputSource source = getInputSource();
        ;
//        selectorExpr = "//Button";
        Node theNode = null;
        try {

            theNode = (Node) xPath.evaluate(path, source,XPathConstants.NODE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "cannot get node from xpath. msg: " + ex.getMessage());
        }
        return theNode;
    }
    public ArrayList<Integer> getIdArrayFromXPath(String path) {
        ArrayList<Integer> idArray = new ArrayList<>();
        try {
            NodeList nodeList = getNodeListFromXPath(path);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                int id;
                Node idAttr = node.getAttributes().getNamedItem("id");

                id = (idAttr == null) ? -1 : Integer.parseInt(idAttr.getNodeValue().substring(1));

                idArray.add(id);
            }


        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "cannot get id array from xpath. msg: " + ex.getMessage());
        }
        return idArray;
    }
    public NodeList getNodeListFromXPath(String path) {
        InputSource source = getInputSource();
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) xPath.evaluate(path, source, XPathConstants.NODESET);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "cannot get node list from xpath. msg: " + ex.getMessage());
        }
        return nodeList;
    }

    private void testParser() {
        try {
            Log.i(LOG_TAG, "test parser");

            InputSource source = getInputSource(); //may be null
            String selectorExpr = "//TextView";
            NodeList nodes = (NodeList) xPath.evaluate(selectorExpr, source, XPathConstants.NODESET);
            for (int nodeListIndex = 0; nodeListIndex < nodes.getLength(); nodeListIndex++) {
                Node node = nodes.item(nodeListIndex);
                Log.i(LOG_TAG, "node #" + nodeListIndex + " name: " + node.getNodeName());
                NamedNodeMap attrList = node.getAttributes();
    //                    Log.i(LOG_TAG, "node #" + nodeListIndex + " attr list length: " + attrList.getLength());
                for (int attrListIndex = 0; attrListIndex < attrList.getLength(); attrListIndex++) {
                    String attrName = attrList.item(attrListIndex).getNodeName();
                    String attrVal = attrList.item(attrListIndex).getNodeValue();
                    Log.i(LOG_TAG, "attr #" + attrListIndex + " attr : " + attrName + " val: " + attrVal);
                }
        }
//        NodeList moreNodes = (NodeList) xPath.evaluate(selectorExpr, inputSource, XPathConstants.NODESET);
//                Log.i(LOG_TAG, "can get input stream from builder");
        }
            catch (Exception ex) {
                Log.e(LOG_TAG, "test parser error. msg: " + ex.getMessage());
            }

    }

    private InputSource getInputSource() {
        //it seems as though you have to refresh the input source for each query
//        InputSource source = null;
//        source = new InputSource(new ByteArrayInputStream(xmlAsString.getBytes()));
//        return source;
        return new InputSource(new ByteArrayInputStream(xmlAsString.getBytes()));
    }

    private String getXmlAsString() {
        String xmlStr = "";
        XmlResourceParser layoutXml = context.getResources().getLayout(resourceId);
        //make array of view elements?

        int traversalEventType = -1;
        StringBuilder builder = new StringBuilder(10000000);
        Document rootDocument;
        try {

        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "cannot make w3c document. msg: " + ex.getMessage());
        }

        try {
            layoutXml.next(); //must move forward one level at first

            rootDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            while ((traversalEventType != XmlResourceParser.END_DOCUMENT) && ((builder.length() + 50) < builder.capacity())) {
                traversalEventType=layoutXml.getEventType();
                switch (traversalEventType) {
                    case XmlResourceParser.START_DOCUMENT:
                        builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        rootDocument.setXmlVersion("1.0");
                        break;
                    case XmlResourceParser.START_TAG:
                        builder.append("\n<" + layoutXml.getName() + " ");
                        for (int attrIndex = 0; attrIndex < layoutXml.getAttributeCount(); attrIndex++ ) {
//                            builder.append(layoutXml.getAttributePrefix(attrIndex));//not supported
                            if ("id".equals(layoutXml.getAttributeName(attrIndex) )) {
//                                builder.append("id=");
//                                builder.append("\"" + layoutXml.getAttributeValue(attrIndex) + "\"" );
//                                builder.append("/>");
                            }
                            builder.append("\n" + layoutXml.getAttributeName(attrIndex) + "=");
//                            builder.append(layoutXml.getAttributeType(attrIndex));//invalid
                            builder.append("\"" + layoutXml.getAttributeValue(attrIndex) + "\"" );
                        }
                        if (layoutXml.isEmptyElementTag()) {
//                            builder.append("/>");
                        }
                        else {
                            builder.append(">");
                        }
//                        builder.append("<" + ">");
                        break;
                    case XmlResourceParser.TEXT:
                        builder.append("\n" + layoutXml.getText());
//                        builder.append("some text");
                        break;
                    case XmlResourceParser.END_TAG:
                        if (!layoutXml.isEmptyElementTag()) {
//                            builder.append("\n<" + layoutXml.getName() + "/>");
                        }

                        builder.append("</" + layoutXml.getName() + ">");
                        break;
                    case XmlResourceParser.END_DOCUMENT:
//                        builder.append("end doc");
                        break;
                    default:
                }
                if (traversalEventType != XmlResourceParser.END_DOCUMENT) {
                    layoutXml.next();
                }
                else {
//                    Log.i(LOG_TAG, "xml string\n" + builder.toString());
                }
            }
            if (builder.length()>0) {
                layoutDoc = DocumentBuilderFactory
                        .newInstance()
                        .newDocumentBuilder()
                        .parse(new ByteArrayInputStream(builder.toString().getBytes()));
                xmlStr = builder.toString();


            }

        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "traversal error. msg: " + ex.getMessage());
        }
        return xmlStr;
    }

    private void layoutInflaterMethod() {
        //junk
        //alternate way to extract xml from the layout
        //inflate layout method
        LayoutInflater inflater = LayoutInflater.from(context);

//        View layoutRoot = inflater.inflate(R.layout.content_menu_sky_color, null);
//need reference to R


        //use this to rebuild the view?
        //inflater.setFactory();

//        android.view.ViewTreeObserver observer = layoutRoot.getViewTreeObserver();
        //doc A view tree observer is used to register listeners that can be notified of global changes in the view tree.


    }
    
    
    
    
}
