/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.CartPojo;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface CartDao {
    public String addProductToCart(CartPojo cart) ;
    public String updateProductInCart(CartPojo cart) ;
    public List<CartPojo>getAllCartItems(String userId) ;
    public String removeProductfromCart(String userId,String prodId) ;
    public Boolean removeAProduct(String userId,String prodId) ;
    public int getCartItemCount(String userId,String itemId) ;
}
