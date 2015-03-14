/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.lang.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;  //importing required JSON libraries
import org.json.JSONArray;
import org.json.JSONException ;
/**
 *
 * @author Ishtiaq
 */
@WebServlet(name = "fetchBooks",urlPatterns = {"/fetchBooks"})
public class fetchBooks extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet fetchBooks</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fetchBooks at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        //processRequest(request, response);
        try
         {
             
            System.out.println("Inside try 1");
             Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
             //setting up the connection
             String dbURL = "jdbc:derby://localhost:1527/autolib_db_test";
             Connection conn = DriverManager.getConnection(dbURL, "ishtiaq", "ishtiaq");
             Statement stmt = null;
             ResultSet rslt = null;
             
             if(conn == null){
                 System.out.println("Connection Failed");
             }
             
             stmt = conn.createStatement();
        
          String searchTopic =  request.getParameter("topic");
         
          //running a select query in the underlying database on table named books_tbl.
          System.out.println("SEARCH TOPIC   " + searchTopic);
          String sqlQry = "SELECT * FROM  books_tbl where topic = '" + searchTopic.toUpperCase() + "'" ;
          rslt=stmt.executeQuery(sqlQry);  //query is executed by the statement.
        
          JSONObject jObj = new JSONObject();   //new jsonobject is created


          JSONArray  bookArr = new JSONArray();  //new json array is created
          JSONObject book;

    try
        {
            System.out.println("Inside Try");


           while(rslt.next()){
                book = new JSONObject();
                book.put("id", rslt.getInt("id"));  //put the id of the book converted into Integer in "id"               
                book.put("name", rslt.getString("bookname"));  //put the bookname into "name"           
                bookArr.put(book);    //bookArr[] is inserted with book ID and bookname which are mandatory fields.
           }
           
            jObj.put("Books", bookArr);  //maps bookArr content to String name "Books"
        }
        catch (JSONException jse)
        { 

        }        
        response.setContentType("application/json");
        response.getWriter().write(jObj.toString());  //toString confirms to JSON syntax rules and converts the jObj content to json text.
        //System.out.println("after json");

        if (!stmt.isClosed())
                stmt.close();
                                    //closing connection
            if (!rslt.isClosed())
                rslt.close()  ;        
         }
         catch(Exception e)
         {
             //return null;
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
    }// </editor-fold>

}
