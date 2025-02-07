/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.OrderDao;
import in.gadgethub.dao.TransactionDao;
import in.gadgethub.dao.UserDao;
import in.gadgethub.dao.impl.OrderDaoImpl;
import in.gadgethub.dao.impl.TransactionDaoImpl;
import in.gadgethub.dao.impl.UserDaoImpl;
import in.gadgethub.pojo.OrderPojo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ShippedItemServlet extends HttpServlet {

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
        HttpSession session=request.getSession();
                String userName=(String)session.getAttribute("username");
                String password=(String)session.getAttribute("password");
                String userType=(String)session.getAttribute("usertype");
                String status = (String)request.getAttribute("message") ;
                if(userType==null || !userType.equalsIgnoreCase("admin")){
                    response.sendRedirect("login.jsp?message=Access denied ! Please login as admin");
                }else if(userName==null||password==null){
                    response.sendRedirect("login.jsp?message=Session expired ! Please login again");
                }
                OrderDao orderDao = new OrderDaoImpl() ;
                UserDao userDao = new UserDaoImpl() ;
                TransactionDao transDao = new TransactionDaoImpl() ;
                List<OrderPojo> orders = orderDao.getAllOrders() ;
                Map<String,String> emailAddr = new HashMap<>() ;
                Map<String,String> userAddress = new HashMap<>() ;
                
                for(OrderPojo ord : orders){
                String orderId = ord.getOrderId() ;        
                String email = transDao.getUserId(orderId) ;
                String address = userDao.getUserAddr(email) ;
                emailAddr.put(orderId, email) ;
                userAddress.put(email,address) ;    
            }
            RequestDispatcher rd = request.getRequestDispatcher("shippedItems.jsp") ;
            request.setAttribute("orders",orders);
            request.setAttribute("user_Id",emailAddr);
            request.setAttribute("user_address",userAddress);
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
