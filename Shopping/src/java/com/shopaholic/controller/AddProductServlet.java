/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.controller;

import com.shopaholic.dao.EmployeeDaoImpl;
import com.shopaholic.dao.ProductDaoImp;
import com.shopaholic.model.MyResponse;
import com.shopaholic.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try{
            Product product = mapper.readValue(request.getInputStream(), new TypeReference<Product>(){});
        //List list = mapper.readValue(request.getInputStream(), ArrayList.class);
        //System.out.println("List First - "+list.get(0).toString());   
        if(new EmployeeDaoImpl().addProduct(product)){
            myResponse.setData(product);
            myResponse.setMessage("Product added/updated to database");
            myResponse.setStatus(MyResponse.SUCCESS);
            
        }else{
            myResponse.setData(null);
            myResponse.setMessage("Operation failed!!");
            myResponse.setStatus(MyResponse.ERROR);
        }
        }catch(Exception e){
            System.err.println("com.shopaholic.controller.PlaceOrderServlet.processRequest()"+e.getLocalizedMessage());
            e.printStackTrace();
            myResponse.setData(null);
            myResponse.setMessage("Operation failed!!");
            myResponse.setStatus(MyResponse.ERROR);
        }finally{
            request.getInputStream().close();
        }
        
        PrintWriter out = response.getWriter();
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
