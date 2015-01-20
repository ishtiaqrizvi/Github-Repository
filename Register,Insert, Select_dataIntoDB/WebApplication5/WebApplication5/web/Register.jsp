<%-- 
    Document   : Register
    Created on : Oct 31, 2014, 11:03:52 PM
    Author     : Ishtiaq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            body{
                background-image: url("DSC_0170.jpg");
                background-repeat: no-repeat;
                background-size: 1600px;
            }
            
        </style>
        <title>Register</title>
    </head>
    <body>
        <h2 align=center>Please register here</h2>
        <div>
        <form id="form" method="post">
         <table border="1" align="center">
           <tr>
               <td>Email:</td>
               <td><input type="text" id="username" name="username"/></td></tr>
           <tr>
               <td>Password:</td>
               <td><input type="password" name="password"/></td></tr>
           <tr>
               <td>Name:</td>
               <td><input type="text" name="name"/></td></tr>
           <tr>
               <td>Address:</td>
               <td><input type="text" name="address"/></td></tr>
           <tr>
               <td>City:</td>
               <td><input type="text" name="city"/></td></tr>
           <tr>
               <td>State:</td>
               <td><input type="text" name="state"/></td></tr>
           <tr>
               <td>Country:</td>
               <td><input type="text" name="country"/></td></tr>
           <tr>
               <td>Zip Code:</td>
               <td><input type="text" name="zipcode"/></td></tr>
           <tr>
               <td>Phone Number:</td>
               <td><input type="text" name="phonenumber"/></td></tr>
           <tr>
               <td><input type="submit" value="Submit"/>
                  </td>
            <a href="index.jsp">Go to Login Page</a></tr>
       </table>
            <!This is the javascript to validate the username/email address based on the pattern>
            <script>
                document.getElementById("form").onsubmit =function()
                {
                 var pattern = /^[a-z0-9._-]+@[a-z]+.[a-z.]{2,5}$/i;
                 var text = document.getElementById("username");
                 if(!pattern.test(text.value))
                   {
                     alert("Enter Correct Username");
                     text.focus();
                    }
                 else
                   {
                     document.getElementById("form").action="registerdetails";
                   }
                };  
            </script>
        </div>


    </body>
</html>
