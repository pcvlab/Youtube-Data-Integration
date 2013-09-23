import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class HTTPXMLTest
{
    public static void main(String[] args) 
    {
        try {
            new HTTPXMLTest().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws Exception
    {
        URL url = new URL("http://jameszaman.podomatic.com/rss2.xml");
        URLConnection connection = url.openConnection();
        Document doc = parseXML(connection.getInputStream());
        NodeList descNodes = doc.getElementsByTagName("description");
        System.out.println(descNodes.getLength());
        for(int i=0; i<descNodes.getLength();i++)
        {
            System.out.println(descNodes.item(i).getTextContent());
        }
    }

    private Document parseXML(InputStream stream)
    throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }       

        return doc;
    }
    }