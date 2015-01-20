
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ishtiaq
 */
@WebServlet(urlPatterns = {"/insert"})
public class insert extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
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
        //Getting parameters from diary.jsp
        String Student_ID= request.getParameter("Student_ID");
        String Name= request.getParameter("Name");
        String Assignment1= request.getParameter("Assignment1");
        String Assignment2= request.getParameter("Assignment2");
        String Assignment3= request.getParameter("Assignment3");
        String Assignment4= request.getParameter("Assignment4");
        String Assignment5= request.getParameter("Assignment5");
        
        PrintWriter out=response.getWriter();
        
        
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
            //Connecting to database name ishtiaq
        connection= DriverManager.getConnection("jdbc:derby://localhost:1527/ishtiaq", "IS2560", "IS2560");
        
        //Executing insert query
        String query= "INSERT INTO Student (Student_ID, Name, Assignment1, Assignment2, Assignment3, Assignment4, Assignment5) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepare=connection.prepareStatement(query);
        //Bind parameter values from diary. jsp with database parameters
        prepare.setString(1, Student_ID);
        prepare.setString(2, Name);
        prepare.setString(3, Assignment1);
        prepare.setString(4, Assignment2);
        prepare.setString(5, Assignment3);
        prepare.setString(6, Assignment4);
        prepare.setString(7, Assignment5);
        prepare.executeUpdate();
        
        //Using session and request attribute
        HttpSession session=request.getSession();
        if(Student_ID !=""){
            session.setAttribute("Student_ID", Student_ID);
        }
        out.println("Requested Student Name= "+Name);
        out.println("Session Student_ID= "+ (String)session.getAttribute("Student_ID"));
            
        
        
        } catch(SQLException e){
            out.println("Can't connect to database");
            return;}
        //Display when insert query is succesful
            out.println("Student details are inserted");
            
            
            try{
                connection.close();
            } catch(SQLException e){
                
            }
    }
    
   /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

