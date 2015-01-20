<%-- 
    Document   : index
    Created on : Oct 31, 2014, 10:41:41 PM
    Author     : Ishtiaq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            body{
                background-image: url("Optimized-Oakland.jpg");
                background-repeat: no-repeat;
                background-size: 1600px;
            }
            
        </style>
        <title>Index</title>
    </head>
    <body>
        <h1 align="center">Welcome!</h1>
        <form align="center" action="Login" method="post">
            <table border="1" align="center">
                <tr>
                    <td>Username</td> 
                    <td><input type="text" name="UserName" /></td>
                </tr>
            
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="Password" /></td>
                </tr>
          
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="Name" /></td>
                </tr>
                <tr>
                <td colspan="2"> <input type="submit" name="Log IN" value="LOG IN"/>
                <a href="Register.jsp">New user register</a></td>
                </tr>
            </table>
            
           
</center>

            
        </form>
    </body>
</html>
