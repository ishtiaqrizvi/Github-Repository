
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishtiaq
 */
@WebServlet(urlPatterns = {"/select"})
public class select extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Getting parameter from view.jsp
        String Student_ID= request.getParameter("Student_ID");
        
        PrintWriter out=response.getWriter();
        
        try{
            //opening connection to derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch(ClassNotFoundException e){
            out.println("Can't load the database driver");
            return;
        }
        Connection connection=null;
        try{
            //Connecting to database name ishtiaq
        connection= DriverManager.getConnection("jdbc:derby://localhost:1527/ishtiaq", "IS2560", "IS2560");
        
        ResultSet r=null;
        //Executing select query for table Student using Student_ID to check if the details entered into view.jsp match with the data in the database
        PreparedStatement ps=connection.prepareStatement("SELECT * FROM Student WHERE Student_ID=?");
        //Bind values from view.jsp with the one in database
        ps.setString(1,Student_ID);
        r=ps.executeQuery();
        ResultSetMetaData metadata=r.getMetaData();
        //Extract data from ResultSet and ResultSetMetadata
        if(r.next()){
            
            out.println("<center><h1>Result:</h1></center>");
            out.println("<b>Student_ID:</b>"+Student_ID+"");
            out.println("<table width=20% border=1>");
            out.println("<tr>");
            //Extracting column name and value for the column name from the database
            out.println("<td>"+metadata.getColumnName(1)+"</td>");
            out.println("<td>"+r.getInt(1)+"</td></tr>");
            out.println("<tr><td>"+metadata.getColumnName(2)+"</td>");
            out.println("<td>"+r.getString(2)+"</td></tr>");
            out.println("<tr><td>"+metadata.getColumnName(3)+"</td>");
            out.println("<td>"+r.getInt(3)+"</td></tr>");
            out.println("<tr><td>"+metadata.getColumnName(4)+"</td>");
            out.println("<td>"+r.getInt(4)+"</td></tr>");
            out.println("<tr><td>"+metadata.getColumnName(5)+"</td>");
            out.println("<td>"+r.getInt(5)+"</td></tr>");
            out.println("<tr><td>"+metadata.getColumnName(6)+"</td>");
            out.println("<td>"+r.getInt(6)+"</td></tr>");
            out.println("<tr><td>"+metadata.getColumnName(7)+"</td>");
            out.println("<td>"+r.getInt(7)+"</td></tr>");
            out.println("</table>");
            
        }else{
          //If not inserted into the database previously then forward to diary.jsp to insert
           RequestDispatcher dispatcher=request.getRequestDispatcher("diary.jsp");
           dispatcher.forward(request,response);
           
        }
        } catch(SQLException e){
            out.println("Can't connect to database");
            return;}
            
            try{
                connection.close();
            } catch(SQLException e){
                //ignore this catch part
            }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
