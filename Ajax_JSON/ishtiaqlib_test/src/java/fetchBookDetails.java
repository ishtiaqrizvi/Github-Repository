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

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException ;
/**
 *
 * @author Ishtiaq
 */
@WebServlet(name = "fetchBookDetails",urlPatterns = {"/fetchBookDetails"})
public class fetchBookDetails extends HttpServlet {

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
             //setting up connection
             String dbURL = "jdbc:derby://localhost:1527/autolib_db_test";
             Connection conn = DriverManager.getConnection(dbURL, "ishtiaq", "ishtiaq");
             Statement stmt = null;
             ResultSet rslt = null;
             
             if(conn == null){
                 System.out.println("Connection Failed");
             }
             
             stmt = conn.createStatement();
        
          String bookId =  request.getParameter("book");   // pass this book value from displayBookDetails function
          String buyBook =  request.getParameter("buy");   // pass this book value from displayBookDetails function
          

          
        String sqlQry = "" ;
         if (buyBook.equals("YES")) {     //if a book is selected to be bought
            Integer bookQty =  Integer.parseInt(request.getParameter("qty"));   // pass this book value from displayBookDetails function          
            sqlQry = "update books_tbl set qty = qty - " + bookQty.toString() + " where id = " + bookId + "" ;  //reducing quantity by bookQty which increments by 1 on every click 
            stmt.execute(sqlQry);    //executing the update query
         }
        System.out.println("SEARCH Book id   " + bookId);   
        //query to return book details by giving the ID value
        sqlQry = "SELECT * FROM  books_tbl where id = " + bookId + "" ;
        rslt=stmt.executeQuery(sqlQry);
        //creating JSON object, array object and a jason variable
        JSONObject jObj = new JSONObject();
        JSONArray  bookArr = new JSONArray();
        JSONObject bookData;

 try
        {
            System.out.println("Inside Try");

           //adding ID, name, author, price, topic, available and qty to bookData by using put
           while(rslt.next()){
                bookData = new JSONObject();
                bookData.put("id", rslt.getInt("id"));                 
                bookData.put("name", rslt.getString("bookname"));
                bookData.put("author", rslt.getString("author"));
                bookData.put("price", rslt.getInt("price"));
                bookData.put("topic", rslt.getString("topic"));
                bookData.put("available", rslt.getBoolean("available"));
                bookData.put("qty", rslt.getInt("qty"));
                
                bookArr.put(bookData);   //putting all bookdata in book array
           }
           
            jObj.put("Books", bookArr);   //adding book array content to string named "Books"
        }
        catch (JSONException jse)
        { 

        }        
        response.setContentType("application/json");
        response.getWriter().write(jObj.toString());
        
        //closing connection
        if (!stmt.isClosed())
                stmt.close();
            
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
