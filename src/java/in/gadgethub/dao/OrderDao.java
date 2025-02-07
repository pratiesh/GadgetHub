/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.OrderDetailsPojo;
import in.gadgethub.pojo.OrderPojo;
import in.gadgethub.pojo.TransactionPojo;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface OrderDao {
    public boolean addOrder(OrderPojo order) ;
    public boolean addTransaction(TransactionPojo transaction ) ;
    public List<OrderPojo> getAllOrders() ;
    public List<OrderDetailsPojo> getAllOrderDetails(String userEmailId) ;
    public String paymentSuccess(String username,double paidAmount) ;
    public String shipNow(String orderid,String prodid) ;
    public int getSoldQuantity(String prodId) ;
}
