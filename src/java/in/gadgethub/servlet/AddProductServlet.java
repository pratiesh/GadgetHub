/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.impl.ProductDaoImpl;
import in.gadgethub.pojo.ProductPojo;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 161777215) //used for accepting files as uploaded for JSP or HTML 
public class AddProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
                String userName=(String)session.getAttribute("username");
                String password=(String)session.getAttribute("password");
                String userType=(String)session.getAttribute("usertype");
                if(userType==null || !userType.equalsIgnoreCase("admin")){
                    response.sendRedirect("login.jsp?message=Access denied ! Please login as admin");
                }else if(userName==null||password==null){
                    response.sendRedirect("login.jsp?message=Session expired ! Please login again");
                }
                RequestDispatcher rd = null ;
                String status = "Product Registration failed" ;
                String prodName = request.getParameter("name") ;
                String prodType = request.getParameter("type") ;
                String prodInfo = request.getParameter("info") ;
                double prodPrice = 0.00 ;
                int prodQuantity = 0 ;
                String priceParam = request.getParameter("price") ;
                if (priceParam != null ) {
                    try {
                        prodPrice = Double.parseDouble(priceParam) ;
                    } catch (NumberFormatException ex) {
                        status = "Invalid unit price" ;
                        request.setAttribute("message",status);
                        rd = request.getRequestDispatcher("addProduct.jsp") ;
                        rd.forward(request, response);
                        return;
                    }
                }else{
                    status = "price cannot be left blank" ;
                    request.setAttribute("message", status) ;
                    rd = request.getRequestDispatcher("addProdut.jsp") ;
                    rd.forward(request, response);
                    return;
                
                }
                String qtyParam = request.getParameter("quantity") ;
                if (qtyParam != null ) {
                    try {
                        prodPrice = Integer.parseInt(priceParam) ;
                    } catch (NumberFormatException ex) {
                        status = "Invalid quantity" ;
                        request.setAttribute("message",status);
                        rd = request.getRequestDispatcher("addProduct.jsp") ;
                        rd.forward(request, response);
                        return;
                    }
                }else{
                    status = "quantity cannot be left blank" ;
                    request.setAttribute("message", status) ;
                    rd = request.getRequestDispatcher("addProduct.jsp") ;
                    rd.forward(request, response);
                    return;
                }
                //fetchnig the file 
                Part part = request.getPart("image") ;
                InputStream img = part.getInputStream() ;
                //send the product to DB 
                ProductDaoImpl productDao = new ProductDaoImpl() ;
                ProductPojo product = new ProductPojo(null,prodName,prodType,prodInfo,prodPrice,prodQuantity,img);  
                status = productDao.addProduct(product) ;
                request.setAttribute("message", status) ;
                rd = request.getRequestDispatcher("addProduct.jsp");
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
