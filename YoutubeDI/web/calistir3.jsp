<%-- 
    Document   : calistir3
    Created on : Sep 17, 2013, 4:44:01 PM
    Author     : amungen
--%>

<%@page import="youtubetest1.GetVideoFromAuthor"%>
<%@page import="youtubetest1.GetWordsFromSearch"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            try {
youtubetest1.GetWordsFromSearch gg = new GetWordsFromSearch();
gg.maingibi();
GetVideoFromAuthor ag = new GetVideoFromAuthor();
ag.allislem();
 
            } catch (Exception e) {
            }
        %>
    </body>
</html>
