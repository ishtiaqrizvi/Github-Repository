<%-- 
    Document   : diary
    Created on : Nov 1, 2014, 12:28:13 AM
    Author     : Ishtiaq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="image.css"/>
        <title>Student Details Diary</title>
    </head>
    <body>
        <form action="insert" method="post">
        <h1 align="left">Insert Student Details</h1>
        <table border="1" align="left">
           <tr>
               <td>Student_ID:</td>
               <td><input type="text" name="Student_ID"/></td></tr>
           <tr>
               <td>Name:</td>
               <td><input type="text" name="Name"/></td></tr>
           <tr>
               <td>Assignment1:</td>
               <td><input type="text" name="Assignment1"/></td></tr>
           <tr>
               <td>Assignment2:</td>
               <td><input type="text" name="Assignment2"/></td></tr>
           <tr>
               <td>Assignment3:</td>
               <td><input type="text" name="Assignment3"/></td></tr>
           <tr>
               <td>Assignment4:</td>
               <td><input type="text" name="Assignment4"/></td></tr>
           <tr>
               <td>Assignment5:</td>
               <td><input type="text" name="Assignment5"/></td></tr>  
           <tr>
               <td><input type="submit" name= submittoinsert value="Submit"/>
           <a href="view.jsp">Click to go to the View Page</a></td>
           </tr>
            
        </table>
       
        </form>
        </br>
       
        <br>
        
    </body>
</html>
