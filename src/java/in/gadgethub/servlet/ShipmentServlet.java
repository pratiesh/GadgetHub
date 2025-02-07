/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.OrderDao;
import in.gadgethub.dao.impl.OrderDaoImpl;
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
public class ShipmentServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session=request.getSession();
                String userName=(String)session.getAttribute("username");
                String password=(String)session.getAttribute("password");
                String userType=(String)session.getAttribute("usertype");
                if(userType==null || !userType.equalsIgnoreCase("admin")){
                    response.sendRedirect("login.jsp?message=Access denied ! Please login as admin");
                }else if(userName==null||password==null){
                    response.sendRedirect("login.jsp?message=Session expired ! Please login again");
                }
                
                String orderId = request.getParameter("orderid"); 
                String userid = request.getParameter("userid");
                String amountStr = request.getParameter("amount");  
                String prodid = request.getParameter("prodid");

// Null check and parsing amount safely
                double amount = 0.0;
                if (amountStr != null && !amountStr.isEmpty()) {
                    try {
                        amount = Double.parseDouble(amountStr);
                      } catch (NumberFormatException e) {
                            System.out.println("Invalid amount format: " + amountStr);
                        }
                }

                System.out.println("Order ID: " + orderId);
                System.out.println("User ID: " + userid);
                System.out.println("Amount: " + amount);
                System.out.println("Product ID: " + prodid);

if (orderId == null || prodid == null) {
    request.setAttribute("message", "Invalid order details!");
    request.getRequestDispatcher("unshippedItems.jsp").forward(request, response);
    return;
}

OrderDao orderDao = new OrderDaoImpl();
System.out.println("orderDao: " + orderDao);

// Calling shipNow() method
String status = orderDao.shipNow(orderId, prodid);

// Forwarding response
RequestDispatcher rd = request.getRequestDispatcher("./ShippedItemServlet");
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
