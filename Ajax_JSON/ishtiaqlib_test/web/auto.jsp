
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="includes/json2.js" type="text/javascript"></script>
        <script src="includes/books.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Search books and buy</title>
        
    </head>
    
    <body>
        <div class="container"> 
         
                <h3> Enter book type, select the book and buy it </h3>
         
                <div class="wrapper1">
            
                    <form name="frmAuto" id="frmAuto">
            
                    <table align="center">
                
                    <tr><td>
                    <input type="text" id="topic" name="topic" class="form-control spacer" placeholder="Enter type: JAVA, PHP"/>
                    <input class="btn btn-info" type="button" name="find" id="find" value="Find" onclick="findBooks();" /> 
                    </td><td>&nbsp; </td></tr>
                    <tr><td>
                    <select class="form-control spacer" style="width:200px;height:300px" id="lstBook" multiple  onclick="checkSelected(this);" onchange="bookDetails(0);">
                
                    </select></td>
                    <td>
                    <div class="spacer1">

                    <div id="details"></div> <br/><input class="btn btn-danger" style="visibility:hidden" type="button" id="buyButton" value="Buy" onclick="bookDetails(1);"/>
                
                    </div>
                    </td>
                </tr>
                    

            </table>
        </form>
        </div>
        </div>
        
    </body>
</html>
