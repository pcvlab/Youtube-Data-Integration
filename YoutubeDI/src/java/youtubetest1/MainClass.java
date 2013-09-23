/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetest1;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author amungen
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    
     public static void main(String[] args) {
        MainClass a = new MainClass();
        System.out.println("basladi");
       
        a.psvmgibi();
        //a.getrelevant("http://www.youtube.com/watch?v=hbzB13qaM6c");
        System.out.println("bitti");
    }
    
    public void psvmgibi() {
        try {

            //     startmainpage();
            allsystem();
            //     allcomment();
            //   a.getPicturefromWeb("nN6VR92V70M");
        } catch (Exception e) {
              e.printStackTrace();
        }
    }
    
    
    public void updatele(String link) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            st.executeUpdate("UPDATE videos set control=1 WHERE link='" + link + "'");
            
            
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
    
    public void getPicturefromWeb(String videoid) {
        try {
            
            URL url = new URL("https://i1.ytimg.com/vi/" + videoid + "/hqdefault.jpg");
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            
            FileOutputStream fos = new FileOutputStream("/Users/amungen/Desktop/images/" + videoid + ".jpg");
            fos.write(response);
            fos.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void updatelMessagecontrol(String link) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            st.executeUpdate("UPDATE videos set controlm=1 WHERE link='" + link + "'");
            
            
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
    
   

    // public static void main(String[] args) {
    public void allsystem() {
        while (true) {
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                // omitechn_youtubecraw		omite_youtubecra	 ahmet

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
                conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
                st = conn.createStatement();
                rs = st.executeQuery("Select link from videos WHERE control=0");
                int sayac = 0;
                while (rs.next()) {
                    MainClass a = new MainClass();
                    a.getrelevant("http://www.youtube.com/watch?v=" + rs.getString("link"));
                    System.out.println("sayac++ = " + sayac++);
                    
                    a.updatele(rs.getString("link"));
                }
                
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





            // String test = "You a're not alone";
            System.out.println("bir Donus Bitti");
        }
    }
    
    public void allcomment() {
        while (true) {
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                // omitechn_youtubecraw		omite_youtubecra	 ahmet

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
                conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
                st = conn.createStatement();
                rs = st.executeQuery("Select link from videos WHERE controlm=0");
                int sayac = 0;
                while (rs.next()) {
                    MainClass a = new MainClass();
                    GetComment c = new GetComment();
                    c.getwithlink(rs.getString("link"));
                    // a.getrelevant("http://www.youtube.com/watch?v=" + rs.getString("link"));

                    System.out.println("mesaj sayac++ = " + sayac++);
                    
                    a.updatele(rs.getString("link"));
                }
                
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





            // String test = "You a're not alone";
            System.out.println("bir Donus Bitti");
        }
    }
    
    public void insertnewyoutubevideo(String videolink, String name, String dataname, String viewcount, String duration, String source) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet
            EscapeChars es = new EscapeChars();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            String sqlquery = "INSERT INTO videos (name,link,dataname,viewcount,duration,source) VALUES ('" + es.forHTML(name) + "','" + videolink + "','" + es.forHTML(dataname) + "','" + viewcount + "','" + duration + "','" + source + "')";
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
        //    e.printStackTrace();
        }
        
    }
    
    public boolean insertnewyoutubevideowithreturn(String videolink, String name, String dataname, String viewcount, String duration, String source) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet
            EscapeChars es = new EscapeChars();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            String sqlquery = "INSERT INTO videos (name,link,dataname,viewcount,duration,source) VALUES ('" + es.forHTML(name) + "','" + videolink + "','" + es.forHTML(dataname) + "','" + viewcount + "','" + duration + "','" + source + "')";
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
            return false;
        }
        return true;
    }
    
     public void getrelevant(String link) {
        //BUYUK TRY
        try {
            
            String content = null;
            URLConnection connection = null;
            try {
                connection = new URL(link).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content = scanner.next();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         //   System.out.println("content = " + content);
            GetAuthorinf authormethod = new GetAuthorinf();
//Diziyi video onerilerine gore ayirdim.
            String[] dizi = content.split("</span><span dir=\"ltr\" class=\"title\" title");
//degiskenleri tanimladim
            String[] dizilink = new String[dizi.length];
            String[] dizicount = new String[dizi.length];
            String[] dizidata = new String[dizi.length];
            String[] diziname = new String[dizi.length];
            String[] dizitime = new String[dizi.length];

//her video onerisi html seklini inceleyip icinde bilgiyi ayristirmak icin fora aldim
            System.out.println("dizi.length = " + dizi.length);
            for (int i = 1; i < dizi.length; i++) {
                //linki ayristirdim
                dizilink[i] = dizi[i].substring(dizi[i].indexOf("/watch?") + 9, dizi[i].indexOf("/watch?") + 20);

//eger linkde sorun varsa bos atadim ve listeye almadim
                if (dizilink[i].indexOf("video_id") > -1) {
                    dizilink[i] = "not";
                }

//videonun yukleyeni ayristirim
                if (dizi[i].indexOf("data-name=\"watch-vrec\">") > -1) {
                    dizidata[i] = dizi[i].substring(dizi[i].indexOf("\"data-name=\"watch-vrec\">") + 24, dizi[i].indexOf("</span></span><span class=\""));
                    if (dizidata[i].indexOf("by <b>") > -1) {
                        dizidata[i] = dizidata[i].replaceAll("by <b>", "");
                        dizidata[i] = dizidata[i].replaceAll("</b>", "");
                        //      System.out.println("girdi b ye");
                    }
                }


//video ismini ismini buluyorum
                diziname[i] = dizi[i].substring(2, dizi[i].indexOf("\">"));
                if (diziname[i].length() > 200) {
                    diziname[i].substring(0, 190);
                }
                //eger hatali veri varsa siliyor          


//video izlenme sayisini  buluyorum
                if (dizi[i].indexOf("views") > -1) {
                    if (dizi[i].indexOf("<span class=\"stat view-count\">") > -1) {
                        
                        dizicount[i] = dizi[i].substring(dizi[i].indexOf("<span class=\"stat view-count\">") + 30, dizi[i].indexOf("views"));
                        dizicount[i] = dizicount[i].replaceAll(",", "");
                    } else {
                        dizicount[i] = "not";
                        
                    }
                } else {
                }
                // System.out.println("bizim islem");
                // System.out.println("dizi[i] = " + dizi[i]);
                //  System.out.println(dizi[i].indexOf("<span class=\"video-time\">"));
                if (dizi[i].indexOf("<span class=\"video-time\">") > -1) {
                    //    System.out.println("girdi = ");
                    dizitime[i] = dizi[i].substring(dizi[i].indexOf("<span class=\"video-time\">") + 25);
                    //  System.out.println("dizitime[i] = " + dizitime[i]);
                    dizitime[i] = dizitime[i].substring(0, dizitime[i].indexOf("</span>"));
                    //  System.out.println("dizitime[i] = " + dizitime[i]);
                }
                
            }

            //  String s = Jsoup.parse("&lt;Fran&ccedil;ais&gt;").text();

            for (int i = 1; i < dizi.length; i++) {
                try {
                    
                    diziname[i] = Jsoup.parse(diziname[i]).text();
                    diziname[i] = diziname[i].replaceAll("\'", "\\'");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MainClass a = new MainClass();
                if (!dizilink[i].equals("not")) {
                    if (!dizicount[i].equals("not")) {
                        if (!dizidata[i].equals("null")) {

                            //       System.out.println("link = " + link);
                            diziname[i].replaceAll("\'", "/");
                            diziname[i].replaceAll("\"", "/");
                            a.insertnewyoutubevideo(dizilink[i], diziname[i], dizidata[i], dizicount[i], dizitime[i], link.substring(31));
                            getDatafromSinglePage(dizilink[i]);
                            
                            authormethod.getwithlink(dizidata[i]);
                            getPicturefromWeb(dizilink[i]);
                            
                        }
                    }
                }
            }
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void startmainpage() {
        //BUYUK TRY
        try {
            String link = "http://www.youtube.com";
            String content = null;
            URLConnection connection = null;
            try {
                connection = new URL(link).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content = scanner.next();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String[] dizi = content.split("/watch");
            for (int i = 1; i < dizi.length; i++) {
                dizi[i] = dizi[i].substring(3, 14);
                //     System.out.println("dizi[i] = " + dizi[i]);
            }
            
            for (int i = 1; i < dizi.length; i++) {
                if (dizi[i].indexOf("title") == -1) {
                    MainClass a = new MainClass();
                    if (dizi[i].indexOf("?") > -1 || dizi[i].indexOf("?vide") > -1) {
                    } else {
                        a.getrelevant("http://www.youtube.com/watch?v=" + dizi[i]);
                        System.out.println("mainsayac = " + i + "dizi+ " + dizi[i]);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void getDatafromSinglePage(String videoid) {
        String publishedtime;
        String category;
        String title;
        String authorname;
        String uploaderid;
        String keywords;
        String description;
        String numLike;
        String numDislike;
        String viewCount;
        String seconds;
        String content = null;
        
        try {
            URLConnection connection = null;
            String link = "http://gdata.youtube.com/feeds/api/videos/" + videoid + "?v=2";
            connection = new URL(link).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            Document xmlcomment = loadXMLFromString(content);
            NodeList nodes = xmlcomment.getElementsByTagName("entry");
            EscapeChars escapemethod = new EscapeChars();


            // iterate the entry
            for (int i = 0; i < nodes.getLength(); i++) {
                //metadata her biri icin
                Element element = (Element) nodes.item(i);
                
                NodeList name = element.getElementsByTagName("published");
                Element line = (Element) name.item(0);
                publishedtime = getCharacterDataFromElement(line);
                publishedtime = publishedtime.substring(0, 10);
                //     System.out.println("publishedtime = " + publishedtime);

                
                name = element.getElementsByTagName("media:category");
                line = (Element) name.item(0);
                category = getCharacterDataFromElement(line);
                //   System.out.println("media:category = " + category);

                name = element.getElementsByTagName("media:keywords");
                line = (Element) name.item(0);
                keywords = getCharacterDataFromElement(line);
                // System.out.println("media:keywords = " + keywords);

                
                
                name = element.getElementsByTagName("name");
                line = (Element) name.item(0);
                authorname = getCharacterDataFromElement(line);
                //            System.out.println("authorname = " + authorname);

                
                
                name = element.getElementsByTagName("yt:userId");
                line = (Element) name.item(0);
                uploaderid = getCharacterDataFromElement(line);
                //          System.out.println("authorname = " + uploaderid);

                
                name = element.getElementsByTagName("media:title");
                line = (Element) name.item(0);
                title = getCharacterDataFromElement(line);
                title = escapemethod.forHTML(title);
                if (title.length() > 195) {
                    title = title.substring(0, 190);
                }

                //  System.out.println("title = " + title);

                
                
                name = element.getElementsByTagName("media:description");
                line = (Element) name.item(0);
                description = getCharacterDataFromElement(line);
                if (description.indexOf("First Text, Gas Leak") > -1) {
                    //      System.out.println("description = " + description);
                }
                description = escapemethod.forHTML(description);
                if (description.length() > 995) {
                    description = description.substring(0, 990);
                }
                description = description.replaceAll("'", "/");
                description = description.replaceAll("\"", "/");
                //        System.out.println("media:description = " + description);

                
                name = element.getElementsByTagName("yt:statistics");
                line = (Element) name.item(0);
                viewCount = line.getAttribute("viewCount");
                //      System.out.println("viewcount = " + viewCount);

                
                
                name = element.getElementsByTagName("yt:rating");
                line = (Element) name.item(0);
                numLike = line.getAttribute("numLikes");
                //    System.out.println("viewcount = " + numLike);

                
                name = element.getElementsByTagName("yt:duration");
                line = (Element) name.item(0);
                seconds = line.getAttribute("seconds");
                //  System.out.println("seconds = " + seconds);

                name = element.getElementsByTagName("yt:rating");
                line = (Element) name.item(0);
                numDislike = line.getAttribute("numDislikes");
                //  System.out.println("viewcount = " + numDislike);

                //   System.out.println("test");
                updatefromsinglepagedata(videoid, publishedtime, category, title, authorname, uploaderid, keywords, description, numLike, numDislike, viewCount, seconds);
                
                
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void updatefromsinglepagedata(String link, String publishedtime, String category, String title, String authorname, String uploaderid, String keywords, String description, String numLike, String numDislike, String viewCount, String seconds) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // omitechn_youtubecraw		omite_youtubecra	 ahmet

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            String sqlquery = "UPDATE videos SET name='" + title + "',dataname='" + authorname + "',viewcount='" + viewCount + "', duration='" + seconds + "', publishedtime='" + publishedtime + "', category='" + category + "',uploaderid='" + uploaderid + "', description='" + description + "',numLike='" + numLike + "',numDislike='" + numDislike + "' WHERE link='" + link + "'";




            //   System.out.println("-------------------------------");
            //   System.out.println(sqlquery);
            st.execute(sqlquery);
            //   System.out.println("===============================");

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
}
