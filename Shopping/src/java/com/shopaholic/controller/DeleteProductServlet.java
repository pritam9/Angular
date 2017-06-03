/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.controller;

import com.shopaholic.dao.EmployeeDaoImpl;
import com.shopaholic.model.MyResponse;
import com.shopaholic.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Pritam
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

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
        MyResponse myResponse=new MyResponse();
        response.setContentType("application/json");
        
        String productId = request.getParameter("productId");
        System.out.println("Delete Prod"+productId);
        if(new EmployeeDaoImpl().deleteProduct(productId)){
            myResponse.setData(productId);
            myResponse.setMessage("Product deleted from database");
            myResponse.setStatus(MyResponse.SUCCESS);
            
        }else{
            myResponse.setData(null);
            myResponse.setMessage("Cannot Delete Product!!");
            myResponse.setStatus(MyResponse.ERROR);
        }
        
        
        PrintWriter out = response.getWriter();
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
