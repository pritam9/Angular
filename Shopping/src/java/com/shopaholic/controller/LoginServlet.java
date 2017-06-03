
package com.shopaholic.controller;

import com.shopaholic.dao.CustomerDaoImpl;
import com.shopaholic.model.Customer;
import com.shopaholic.model.MyResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Pritam
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        MyResponse myResponse=new MyResponse();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Customer customer = new CustomerDaoImpl().getCustomerByEmailPassword(email, password);
        if(customer!=null){
            myResponse.setData(customer);
            myResponse.setMessage("Login Successful");
            myResponse.setStatus(MyResponse.SUCCESS);
            request.getSession().setAttribute("email", customer.getEmail());
        }else{
            myResponse.setData(null);
            myResponse.setMessage("Invalid username/password");
            myResponse.setStatus(MyResponse.ERROR);
        }
        
        ObjectMapper mapper = new ObjectMapper();
    String responseJson = mapper.writeValueAsString(myResponse);
        out.print(responseJson);
        out.flush();
        
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
        processRequest(request, response);
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
