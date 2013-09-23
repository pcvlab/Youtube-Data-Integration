/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetest1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author amungen
 */
public class GetVideoFromAuthor {

    public static void main(String[] args) {
        GetVideoFromAuthor j = new GetVideoFromAuthor();
        j.allislem();
    }

    public void allislem() {
        String content;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String authorname;
        try {

            URLConnection connection = null;

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
            conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT Distinct dataname FROM `videos` ORDER BY id DESC");
            int sayac = 0;
            while (rs.next()) {
                authorname = rs.getString("dataname");
                sayac++;

                try {


                    for (int i = 1; i < 3; i++) {
                        String link = "http://gdata.youtube.com/feeds/api/users/" + authorname + "/uploads?v=2&alt=jsonc&start-index=" + i + "&max-results=50";
                        //     System.out.println("link = " + link);
                        connection = new URL(link).openConnection();
                        Scanner scanner = new Scanner(connection.getInputStream());
                        scanner.useDelimiter("\\Z");
                        content = scanner.next();

                        parse(content, authorname);
                    }

                } catch (Exception e) {
                }
                System.out.println("\n Kacinci Yazar = " + sayac);

            }
            conn.close();
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }





        //ADIM @
        // SELECT DISTINCT username FROM `users` 
        boolean islem2 = false;
        if (islem2 == true) {
            
        System.out.println("ISLEM 2");
            try {

                URLConnection connection = null;

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
                conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
                st = conn.createStatement();
                rs = st.executeQuery("SELECT DISTINCT username FROM `users`");
                int sayac = 0;
                while (rs.next()) {
                    authorname = rs.getString("username");
                    sayac++;

                    try {
                        for (int i = 1; i < 2; i++) {
                            String link = "http://gdata.youtube.com/feeds/api/users/" + authorname + "/uploads?v=2&alt=jsonc&start-index=" + i + "&max-results=50";
                            //     System.out.println("link = " + link);
                            connection = new URL(link).openConnection();
                            Scanner scanner = new Scanner(connection.getInputStream());
                            scanner.useDelimiter("\\Z");
                            content = scanner.next();

                            parse(content, authorname);
                        }

                    } catch (Exception e) {
                    }
                    System.out.println("\n Kacinci Yazar2 = " + sayac);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        


    }

    public void parse(String jsonLine, String authorname) {
        int sayac = 0;
        MainClass mainmetod = new MainClass();
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("data");
        JsonArray jarray = jobject.getAsJsonArray("items");
        for (int i = 0; i < jarray.size(); i++) {
            try {
                jobject = jarray.get(i).getAsJsonObject();
                String id = jobject.get("id").toString().substring(1, jobject.get("id").toString().length() - 1);
                //    System.out.println("id = " + id);
                String duration = jobject.get("duration").toString();
                //   System.out.println("duration = " + duration);
                String viewCount = jobject.get("viewCount").toString();
                //   System.out.println("viewCount = " + viewCount);
                String uploaded = jobject.get("uploaded").toString().substring(1, 11);
                //  System.out.println("uploaded = " + uploaded);
                String title = jobject.get("title").toString().substring(1, jobject.get("title").toString().length() - 1);
                //  System.out.println("title = " + title);
                JsonObject jobject2 = jobject.get("player").getAsJsonObject();
                String link = jobject2.get("default").toString().substring(32, 43);

                //  System.out.println("link = " + link);
                String source = "AuthorList";
                String datename = authorname;


                boolean sonuc = mainmetod.insertnewyoutubevideowithreturn(link, datename, datename, viewCount, duration, source);
                if (sonuc == true) {
                    sayac++;
                }
            } catch (Exception e) {
            }

        }
        System.out.println("basarili insert sayisi= " + sayac);
    }
}
