<%-- 
    Document   : raporlar
    Created on : Sep 12, 2013, 8:19:26 PM
    Author     : amungen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%
    try{
    Connection conn = null;
Statement st = null;
ResultSet rs = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
                String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
                conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
                st = conn.createStatement();
st = conn.createStatement();
rs = st.executeQuery("SELECT COUNT(id) FROM videos");
while(rs.next()) {
%>
<b>Video Sayisi :</b> <%= rs.getInt(1) %> <br>
<%}
if (rs != null) rs.close();
if (st != null) st.close();
if (conn != null) conn.close();

  } catch (Exception e) {
                e.printStackTrace();
            }

%>


<%
    try{
    Connection conn = null;
Statement st = null;
ResultSet rs = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
                String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
                conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
                st = conn.createStatement();
st = conn.createStatement();
rs = st.executeQuery("SELECT COUNT(id) FROM users");
while(rs.next()) {
%>
<b>Kullanici Sayisi :</b> <%= rs.getInt(1) %> <br>
<%}
if (rs != null) rs.close();
if (st != null) st.close();
if (conn != null) conn.close();

  } catch (Exception e) {
                e.printStackTrace();
            }

%>



<%
    try{
    Connection conn = null;
Statement st = null;
ResultSet rs = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
                String dbUrl = "jdbc:mysql://omitechnology.com/omitechn_youtubecraw?useUnicode=true&characterEncode=UTF-8";
                conn = DriverManager.getConnection(dbUrl, "omite_youtubecra", "ahmet");
                st = conn.createStatement();
st = conn.createStatement();
rs = st.executeQuery("SELECT COUNT(id) FROM comments");
while(rs.next()) {
%>
<b>Yorum Sayisi :</b> <%= rs.getInt(1) %> <br>
<%}
if (rs != null) rs.close();
if (st != null) st.close();
if (conn != null) conn.close();

  } catch (Exception e) {
                e.printStackTrace();
            }

%>

    </body>
</html>
