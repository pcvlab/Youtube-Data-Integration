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
public class GetComment {

    public static void main(String[] args) {
        GetComment a = new GetComment();
        //   String link = "MrFTh1NVsvw";
//        a.getwithlink(link);
    }

    public void getwithlink(String videoid) {
        //BUYUK TRY
        String content = null;

        try {
            URLConnection connection = null;
            String link = "http://gdata.youtube.com/feeds/api/videos/" + videoid + "/comments";
            connection = new URL(link).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            //  System.out.println(content);
            Document xmlcomment = loadXMLFromString(content);
            NodeList nodes = xmlcomment.getElementsByTagName("entry");


            String username;
            String userlink;
            String id;
            String publishtime;
            String commendcontent;
            EscapeChars escapemethod = new EscapeChars();


            // iterate the entry
            for (int i = 0; i < nodes.getLength(); i++) {
                //yorumlarin her biri icin
                Element element = (Element) nodes.item(i);

                NodeList name = element.getElementsByTagName("content");
                Element line = (Element) name.item(0);
                commendcontent = getCharacterDataFromElement(line);
                commendcontent = escapemethod.forHTML(commendcontent);

                name = element.getElementsByTagName("published");
                line = (Element) name.item(0);
                publishtime = getCharacterDataFromElement(line).substring(0, 10);

                name = element.getElementsByTagName("id");
                line = (Element) name.item(0);
                id = getCharacterDataFromElement(line).substring(63);

                name = element.getElementsByTagName("name");
                line = (Element) name.item(0);
                username = getCharacterDataFromElement(line);

                name = element.getElementsByTagName("uri");
                line = (Element) name.item(0);
                userlink = getCharacterDataFromElement(line).substring(41);
                insertnewcomment(videoid, publishtime, username, userlink, commendcontent, id);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
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

    public void insertnewcomment(String videoid, String publishtime, String commentadorname, String commentadorlink, String content, String commentid) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            String sqlquery = "INSERT INTO comments (videoid,  publishtime,  commentadorname,  commentadorlink, content, commentid) VALUES ('" + videoid + "','" + publishtime + "','" + commentadorname + "','" + commentadorlink + "','" + content + "','" + commentid + "')";
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
        }

    }
}
