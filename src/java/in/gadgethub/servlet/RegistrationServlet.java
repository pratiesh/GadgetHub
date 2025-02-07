/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.impl.UserDaoImpl;
import in.gadgethub.pojo.UserPojo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hp
 */
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username") ;
        String userEmail = request.getParameter("useremail") ;
        String address = request.getParameter("address") ;
        String moblieNo = request.getParameter("mobile");
        String pincode = request.getParameter("pincode");
        String password = request.getParameter("password") ;
        String cpassword = request.getParameter("cpassword") ;        
        String userType = request.getParameter("usertype") ; 
        String status = null ;
        
        if (!cpassword.equals(password)) {
            status = "Passwords doesn't match, enter again ;" ;
            return;   
        }
        UserDaoImpl userDao = new UserDaoImpl() ;
        UserPojo user = new UserPojo(userName, userEmail, moblieNo, address, pincode, password,userType) ;
        status = userDao.registerUser(user) ;
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp") ;
        request.setAttribute("message", status);
        rd.forward(request, response);
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
