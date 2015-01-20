
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mybean.Bean;

/**
 *
 * @author Ishtiaq
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        
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
        response.setContentType("text/html;charset=UTF-8");
        
        // Database credentials to log in. Parameters called from index.jsp
        String un=request.getParameter("UserName");
        String p=request.getParameter("Password");
        String Name=request.getParameter("Name");
        
        PrintWriter out=(PrintWriter) response.getWriter();
        
        Boolean creds=false;
        try{
            //opening connection to derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch(ClassNotFoundException e){
            //Print below statement when connection to derby database failed.
            out.println("Can't load the database driver");
            return;
        }
        Connection connection=null;
        try{
            //Connecting to databse name ishtiaq
        connection= DriverManager.getConnection("jdbc:derby://localhost:1527/ishtiaq", "IS2560", "IS2560");
        } catch(SQLException e){
            //Print below statement when connection to ishtiaq failed
            out.println("Can't connect to database");
            return;}
            out.println("Connected to database");
            
            try{
            ResultSet r=null;
            Statement s=connection.createStatement();
            //Executig select query
            r=s.executeQuery("SELECT * FROM Login");
            //Extracting data from ResultSet
            while(r.next()){
                //Retrieve by column name
                String user=r.getString("UserName");
                String pwd=r.getString("Password");
                //Check parameters match with the values in database
                creds = user.equals(un) & pwd.equals(p);
            }
            //if matched
            if(creds){
                //Use of bean to get the Name of the user on selectlink.jsp
                Bean bean=new Bean();
                bean.setName(Name);
                request.setAttribute("bean", bean);
                //Requesting to forward to selectlink.jsp when the parameters match
                RequestDispatcher requestd=request.getRequestDispatcher("selectlink.jsp");
                requestd.forward(request, response);
            } 
            //if failed
            else{
                //Requesting to forward to Register.jsp when the parameters match
                RequestDispatcher requestd=request.getRequestDispatcher("Register.jsp");
                requestd.forward(request, response);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
