<%-- 
    Document   : index
    Created on : Sep 12, 2013, 6:31:24 PM
    Author     : amungen
--%>

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
        youtubetest1.MainClass main = new youtubetest1.MainClass();
        //  <meta http-equiv="refresh" content="600"/>
        out.println("Program Calistirildi");
        
              main.psvmgibi();
              out.println("Program BITTI");
       //     main.allsystem();
       //     main.allcomment();
        
        %>
    </body>
</html>
