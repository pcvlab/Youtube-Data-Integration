package youtubetest1;

import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import youtubetest1.EscapeChars;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author amungen
 */
public class GetAuthorinf {

    public static void main(String[] args) {
        GetAuthorinf a = new GetAuthorinf();
        String link = "turkcerock";
        a.getwithlink(link);
    }

    public void getwithlink(String username) {
        //BUYUK TRY
        String content = null;
    //    System.out.println("test1" + username);

        try {
            URLConnection connection = null;
            String link = "http://gdata.youtube.com/feeds/api/users/" + username;
            connection = new URL(link).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            //  System.out.println(content);
            Document xmlcomment = loadXMLFromString(content);
            NodeList nodes = xmlcomment.getElementsByTagName("entry");



            EscapeChars escapemethod = new EscapeChars();
            String channeltitle;
            String published;
            String youtubeuserid;
            String location;
            String subscriberCount;
            String totalUploadViews;

            // iterate the entry
            for (int i = 0; i < nodes.getLength(); i++) {
                 Element element = (Element) nodes.item(i);


                NodeList name = element.getElementsByTagName("published");
                Element line = (Element) name.item(0);
                published = getCharacterDataFromElement(line).substring(0, 10);
              //  System.out.println("published = " + published);



                name = element.getElementsByTagName("title");
                line = (Element) name.item(0);
                channeltitle = getCharacterDataFromElement(line).substring(0, 10);
            //    System.out.println("channeltitle = " + channeltitle);



                name = element.getElementsByTagName("yt:location");
                line = (Element) name.item(0);
                location = getCharacterDataFromElement(line);
            //    System.out.println("location = " + location);


                name = element.getElementsByTagName("yt:statistics");
                line = (Element) name.item(0);
                subscriberCount = line.getAttribute("subscriberCount");
           //     System.out.println("subscriberCount = " + subscriberCount);
                name = element.getElementsByTagName("yt:statistics");
                line = (Element) name.item(0);
                totalUploadViews = line.getAttribute("totalUploadViews");
           //     System.out.println("totalUploadViews = " + totalUploadViews);

                name = element.getElementsByTagName("id");
                line = (Element) name.item(0);
                youtubeuserid = getCharacterDataFromElement(line).substring(42);
           //     System.out.println("youtubeuserid = " + youtubeuserid);
                //     userlink = getCharacterDataFromElement(line).substring(41);
                //    insertnewcomment(videoid, publishtime, username, userlink, commendcontent, id);
           insertnewuser(username, published, channeltitle, location, subscriberCount, totalUploadViews, youtubeuserid);
            
            }


        } catch (Exception ex) {
      //      ex.printStackTrace();
            //ESCAPE
        }




    }

    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    public void insertnewuser(String userid, String published, String channeltitle, String location, String subscriberCount, String totalUploadViews,String youtubeuserid) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            String sqlquery = "INSERT INTO users ( username,  published,  channeltitle,  location,  subscriberCount,  totalUploadViews, youtubeuserid) VALUES"
                    + " ('" + userid + "','" + published + "','" + channeltitle + "','" + location + "','" + subscriberCount + "','"+ totalUploadViews + "','" + youtubeuserid + "')";
            st.execute(sqlquery);
            // System.out.println("sqlquery = " + sqlquery);

            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
            //ESCAPE
        }

    }
}
