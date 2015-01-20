
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishtiaq
 */
@WebServlet(urlPatterns = {"/registerdetails"})
public class registerdetails extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Getting parameters from Register.jsp
        String username= request.getParameter("username");
        String password= request.getParameter("password");
        String name= request.getParameter("name");
        String address= request.getParameter("address");
        String city= request.getParameter("city");
        String state= request.getParameter("state");
        String country= request.getParameter("country");
        String zipcode= request.getParameter("zipcode");
        String phonenumber= request.getParameter("phonenumber"); 
        
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
        
        //Execute insert query to enter new registeration details
        String query= "INSERT INTO Login (UserName, Password, Name, Address, PhoneNumber, City, State, ZipCode, Country) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepare=connection.prepareStatement(query);
        //Bind values from Register.jsp with the database parameters
        prepare.setString(1, username);
        prepare.setString(2, password);
        prepare.setString(3, name);
        prepare.setString(4, address);
        prepare.setString(5, phonenumber);
        prepare.setString(6, city);
        prepare.setString(7, state);
        prepare.setString(8, zipcode);
        prepare.setString(9, country);
        
        prepare.executeUpdate();
        } catch(SQLException e){
            //Displays when not connected to databse
            out.println("Can't connect to database. Go back to the registration page and try again");
            return;}
        //Diplays when connected to database and succesfully entered new details into the database
        out.println("You are connected to the database. Go back to the index page through registration page");
        try{
                connection.close();
            } catch(SQLException e){
                //ignore this part in catch
            }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
