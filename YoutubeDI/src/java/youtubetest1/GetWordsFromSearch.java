package youtubetest1;


import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import youtubetest1.EscapeChars;
import youtubetest1.GetComment;
import static youtubetest1.GetComment.getCharacterDataFromElement;
import static youtubetest1.GetComment.loadXMLFromString;
import youtubetest1.MainClass;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author amungen
 */
public class GetWordsFromSearch {

    public static void main(String[] args) {
        GetWordsFromSearch ge = new GetWordsFromSearch();
        ge.maingibi();
    }
    
    public void maingibi() {
        String[] dizi = null;
        String link = "http://www.englishclub.com/vocabulary/common-words-5000.htm";
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(link).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            //  System.out.println("content = " + content);
            dizi = content.split("<li>");
            //    System.out.println(dizi.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String searchword = null;

        //  System.out.println("dizi[0] = " + dizi[0]);
        for (int j = 3; j < 5014; j++) {
            dizi[j] = dizi[j].substring(0, dizi[j].indexOf("</li>"));
            //     System.out.println("dizi[i] = " + dizi[i]);
            searchword = dizi[j];
            System.out.println("searchword = " + searchword + "    dizi= " + j);
           int icsayac =0;
            for (int sayfasayisi = 10; sayfasayisi < 15; sayfasayisi++) {
                System.out.println("sayfasayisi = " + sayfasayisi);
                try {
                    link = "http://gdata.youtube.com/feeds/api/videos?q=" + searchword + "&start-index=" + sayfasayisi + "&max-results=50&v=2";
                    connection = new URL(link).openConnection();
                    Scanner scanner = new Scanner(connection.getInputStream());
                    scanner.useDelimiter("\\Z");
                    content = scanner.next();
                    System.out.println("alindi");
                    //System.out.println("content = " + content);
                    //    System.out.println(dizi.length);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }



                try {
                    Document xmlcomment = loadXMLFromString(content);
                    NodeList nodes = xmlcomment.getElementsByTagName("entry");

                    String username;
                    String userlink;
                    String id;
                    String videoid;
                    String publishtime;
                    String duration;
                    String commendcontent;
                    String viewCount;
                    String title;
                    EscapeChars escapemethod = new EscapeChars();

                    // iterate the entry
                    //for (int i = 0; i < nodes.getLength(); i++) {
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
                        //   System.out.println("publishtime = " + publishtime);
                        name = element.getElementsByTagName("id");
                        line = (Element) name.item(0);
                        id = getCharacterDataFromElement(line).substring(29);
                        //id = getCharacterDataFromElement(line);
                        //   System.out.println("id = " + id);
                        name = element.getElementsByTagName("name");
                        line = (Element) name.item(0);
                        username = getCharacterDataFromElement(line);
                        //   System.out.println("username = " + username);

                        name = element.getElementsByTagName("uri");
                        line = (Element) name.item(0);
                        userlink = getCharacterDataFromElement(line).substring(41);
                        //      System.out.println("userlink = " + userlink);

                        name = element.getElementsByTagName("yt:videoid");
                        line = (Element) name.item(0);
                        videoid = getCharacterDataFromElement(line);
                        //    System.out.println("videoid = " + videoid);




                        name = element.getElementsByTagName("title");
                        line = (Element) name.item(0);
                        title = getCharacterDataFromElement(line);
                        //      System.out.println("title = " + title);

                        name = element.getElementsByTagName("yt:statistics");
                        line = (Element) name.item(0);
                        viewCount = line.getAttribute("viewCount");
                        //     System.out.println("viewcount = " + viewCount);

                        name = element.getElementsByTagName("yt:duration");
                        line = (Element) name.item(0);
                        duration = line.getAttribute("seconds");
                        //   System.out.println("duration = " + duration);

                        System.out.println("xml cozuldu");
                        //yt:statistics
                        try {

                            MainClass getc = new MainClass();
                           boolean checkko= getc.insertnewyoutubevideowithreturn(videoid, title, username, viewCount, duration, searchword);
                            System.out.println("db ye eklendi");
                           //   getc.insertnewcomment(videoid, publishtime, username, userlink, commendcontent, id);
                            //   getc.insertnewcomment(videoid,   commendcontent, id);
if(checkko==true )icsayac++;
                        } catch (Exception e) {
                        e.printStackTrace();
                        }
                    }


                } catch (Exception ex) {
                      ex.printStackTrace();
                }
                
            }
            System.out.println("Basarili Insert = " + icsayac);
        }

    }
}
