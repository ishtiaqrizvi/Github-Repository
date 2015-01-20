
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="image.css"/>
        <title>Select Link</title>
    </head>
    
        <h1>Hello Professor</h1>
        <!bean to get the name from the login page>
        <%@page import="mybean.Bean" %>
        
        <%
            Bean bean=(Bean)request.getAttribute("bean");
        %>
        
       
        <%=bean.getName()%>
        
        <a href="diary.jsp">
            <button style="background-color:yellow">Click here to go to the diary page to insert student details</button>
        </a>
        <br>
        <br>
        <a href="view.jsp">
            <button  style="background-color:yellow">Click here to view Student Details</button>
        </a>
        
        
    

