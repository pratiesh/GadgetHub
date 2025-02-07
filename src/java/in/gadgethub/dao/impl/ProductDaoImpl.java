/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.ProductDao;
import in.gadgethub.pojo.ProductPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.IDutil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class ProductDaoImpl implements ProductDao{

    @Override
    public String addProduct(ProductPojo product) { //ok
        String status ="Product Registration Failed" ;
        if(product.getProdId()==null) {
            product.setProdId(IDutil.generateProdId());
        }
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        try {
            ps = conn.prepareStatement("insert into products values(?,?,?,?,?,?,?,?)") ;
            ps.setString(1,product.getProdId());
            ps.setString(2,product.getProdName());
            ps.setString(3,product.getProdType());
            ps.setString(4,product.getProdInfo());
            ps.setDouble(5,product.getProdPrice());
            ps.setInt(6,product.getProdQuantity());
            ps.setBlob(7,product.getProdImage());
            ps.setString(8, "Y");
            int count = ps.executeUpdate() ;
            if (count==1) {
                status ="Product added successfully with Id :"+product.getProdId() ;
            }
        } catch (SQLException ex) {
            System.out.println("Error in addProduct :"+ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status ;      
    }
    @Override //ok
    public String updateProduct(ProductPojo prevProduct, ProductPojo updatedProduct) {
       String status="Updation Failed!";
        if(!prevProduct.getProdId().equals(updatedProduct.getProdId())){
            status="Product ID's Do Not Match. Updation Failed";
            return status;
        }
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("Update products set pname=?,ptype=?,pinfo=?,pprice=?,pquantity=?,image=? where pid=?");
            ps.setString(1,updatedProduct.getProdName());
            ps.setString(2,updatedProduct.getProdType());
            ps.setString(3,updatedProduct.getProdInfo());
            ps.setDouble(4,updatedProduct.getProdPrice());
            ps.setInt(5,updatedProduct.getProdQuantity());
            ps.setBlob(6,updatedProduct.getProdImage());
            ps.setString(7,updatedProduct.getProdId());
            int count=ps.executeUpdate();
            if(count==1){
                status="Product Updated Successfully";  
            }
        }catch(SQLException ex){
            System.out.println("Error in updateProduct:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeStatement(ps);
        return status;

    }

    @Override
    public String updateProductPrice(String prodId, double updatedPrice) {
        String status = "Updation Failed !" ;
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        try {
            ps = conn.prepareStatement("Update products set pprice=? where pid=?") ;
            ps.setDouble(1, updatedPrice);
            ps.setString(2, prodId);
            int count = ps.executeUpdate() ;
            if (count==1) {
                status = "Product Updated Successfully" ;
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in updateProductPrice : "+ex) ;
        }
       
        DBUtil.closeStatement(ps);
        return status ;
    }

    @Override
    public List<ProductPojo> getAllProducts(){
        List <ProductPojo> productList=new ArrayList<>();
        Connection conn=DBUtil.provideConnection();
        Statement st=null;
        ResultSet rs=null;
        try{
            st=conn.createStatement();
            rs=st.executeQuery("Select * from Products where available = 'Y'");
            while(rs.next()){
                ProductPojo product=new ProductPojo();
                product.setProdId(rs.getString("pid"));
                product.setProdName(rs.getString("pname"));
                product.setProdPrice(rs.getDouble("pprice"));
                product.setProdType(rs.getString("ptype"));
                product.setProdInfo(rs.getString("pinfo"));
                product.setProdQuantity(rs.getInt("pquantity"));
                product.setProdImage(rs.getAsciiStream("image"));
                productList.add(product);
                
            }
        }
        catch(SQLException ex){
            System.out.println("Error in getAllProducts:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return productList;
    } 
    @Override
    public List<ProductPojo> getAllProductsByType(String ptype) { //ok
       List<ProductPojo> productList = new ArrayList<>() ;
       Connection conn = DBUtil.provideConnection() ;
       PreparedStatement ps = null ; 
       ResultSet rs = null ;
       ptype = ptype.toLowerCase() ;
        try {
            ps = conn.prepareStatement("select * from products where ptype like ? and available='Y'") ;
            ps.setString(1, "%"+ptype+"%");
            rs = ps.executeQuery() ;
            while (rs.next()) {
                 ProductPojo product = new ProductPojo() ;
                 product.setProdId(rs.getString("pid"));
                 product.setProdName(rs.getString("pname"));
                 product.setProdPrice(rs.getDouble("pprice"));
                 product.setProdType(rs.getString("ptype"));
                 product.setProdInfo(rs.getString("pinfo"));
                 product.setProdQuantity(rs.getInt("pquantity"));
                 product.setProdImage(rs.getAsciiStream("image"));
                 productList.add(product) ;
            }
        } catch (SQLException ex) {
            System.out.println("Error in getAllProductsByType : "+ex);
            ex.printStackTrace();
        }
           DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return productList ;     
    }
    

    @Override
    public List<ProductPojo> searchAllProducts(String searchTerm) {
        List<ProductPojo> productList = new ArrayList<>() ;
        PreparedStatement ps = null ;
        Connection conn = DBUtil.provideConnection() ;
        ResultSet rs = null ;
        searchTerm = searchTerm.toLowerCase() ;
        try {
            ps = conn.prepareStatement("Select * from products where lower(ptype) like ? or lower(pname) like ? or lower(pinfo) like ? and available ='Y'") ;
            ps.setString(1, "%"+searchTerm+"%");
            ps.setString(2, "%"+searchTerm+"%");
            ps.setString(3, "%"+searchTerm+"%");
            rs = ps.executeQuery() ;
            while(rs.next()){
                ProductPojo product = new ProductPojo() ;
                product.setProdId(rs.getString("pid"));
                product.setProdName(rs.getString("pname"));
                product.setProdPrice(rs.getDouble("pprice"));
                product.setProdType(rs.getString("ptype"));
                product.setProdQuantity(rs.getInt("pquantity"));
                product.setProdInfo(rs.getString("pinfo"));
                product.setProdImage(rs.getAsciiStream("image"));
                productList.add(product) ;
            }    
        } catch (SQLException ex) {
            System.out.println("Error in searchAllProducts"+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return productList ; 
    }
    @Override
    public ProductPojo getProductDetails(String prodId) {
        ProductPojo product = null ;
        Connection conn  = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        ResultSet rs = null ;
        try {
            ps = conn.prepareStatement("Select * from products where pid = ?") ;
            ps.setString(1, prodId);
            rs = ps.executeQuery() ;
            if (rs.next()) {
                product = new ProductPojo() ;
                product.setProdId(rs.getString("pid"));
                product.setProdImage(rs.getAsciiStream("image"));
                product.setProdInfo(rs.getString("pinfo"));
                product.setProdName(rs.getString("pname"));
                product.setProdPrice(rs.getDouble("pprice"));
                product.setProdQuantity(rs.getInt("pquantity"));
                product.setProdType(rs.getString("ptype"));            
            }
        } catch (SQLException ex) {
            System.out.println("Error in getProductDetails : "+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return product ;      
    
    }

    @Override
    public int getProductQuantity(String prodId) {
        int quantity = 0  ;
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        ResultSet rs = null ;
        try {
            ps = conn.prepareStatement("select pquantity from products where pid=?") ;
            ps.setString(1, prodId);
            rs = ps.executeQuery() ;
            if (rs.next()) {
                quantity = rs.getInt(1) ;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in getPRoductQuantity : "+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return quantity ;    
    }

    @Override
    public String updateProductWithoutImage(String prevProductId, ProductPojo updatedProduct) {
        String status = "Updation Failed !" ;
        int prevQuantity = 0;
        if(!prevProductId.equals(updatedProduct.getProdId())){
            status = "Product ID's don't match. Updation failed " ;
            return status ;
        }
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        ResultSet rs = null ;
        try {
            prevQuantity = getProductQuantity(prevProductId) ;
            ps = conn.prepareStatement("Update products set pname=? ,ptype=?,pinfo=?,pprice=?,pquantity=? where pid=? ") ;
            ps.setString(1, updatedProduct.getProdName());
            ps.setString(2, updatedProduct.getProdType());
            ps.setString(3, updatedProduct.getProdInfo());
            ps.setDouble(4, updatedProduct.getProdPrice());
            ps.setInt(5, updatedProduct.getProdQuantity());
            ps.setString(6, updatedProduct.getProdId());
            int count = ps.executeUpdate() ;
            if (count==1 && prevQuantity<updatedProduct.getProdQuantity()) {
                status= "Product update successfully and mail sent" ;
                //code for sending mail
                
            }else if(count ==1) {
                status = "product updated successfully" ;
            }
        } catch (SQLException ex) {
         System.out.println("Error in updateProductWithoutImage : "+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return status ;   
    }

    @Override
    public double getProductPrice(String prodId) { //ok
        Double price = 0.0  ;
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        ResultSet rs = null ;
        try {
            ps = conn.prepareStatement("select pprice from products where pid=?") ;
            ps.setString(1, prodId);
            rs = ps.executeQuery() ;
            if (rs.next()) {
                price = rs.getDouble(1) ;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in getProductPrice : "+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return price ;
        
    }

    @Override
    public boolean sellNProduct(String prodId, int n) { 
        boolean result = false ;
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null ;
        ResultSet rs = null ;
        try {
            ps = conn.prepareStatement("update products set pquantity=(pquantity-?) where pid= ? and available ='Y'") ;
            ps.setInt(1, n);
            ps.setString(2, prodId);
            int count = ps.executeUpdate() ;
            if (count==1) {
                result = true ;
            }
        } catch (SQLException ex) {
               System.out.println("Error in sellNProducts : "+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
           DBUtil.closeStatement(ps);
           return result ;
    }

    @Override
  public List<String> getAllProductsType(){ //ok
      List <String> productTypeList=new ArrayList<>();
        Connection conn=DBUtil.provideConnection();
        Statement st=null;
        ResultSet rs=null;
         try{
            st=conn.createStatement();
            rs=st.executeQuery("Select distinct ptype from products where available='Y'");
            while(rs.next()){
                 productTypeList.add(rs.getString(1));                
            }
        }
        catch(SQLException ex){
            System.out.println("Error in getAllProductsType:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return productTypeList;
    } 
 public byte[] getImage(String prodId){ //ok
       byte[]arr=null;
       Connection conn=DBUtil.provideConnection();
       PreparedStatement ps=null;
       ResultSet rs=null;
        try{
            ps=conn.prepareStatement("Select image from products where pid=?");
            ps.setString(1,prodId);
            rs=ps.executeQuery();
            if(rs.next()){
               arr=rs.getBytes(1);
            }
        }catch(SQLException ex){
            System.out.println("Error in getImage:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return arr;
   }
 
    @Override
    public String removeProduct(String prodId) { //ok
        String status="Product Not Found!";
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        try{
            ps1=conn.prepareStatement("Update products set available='N' where pid=? and available= 'Y'");
            ps1.setString(1, prodId);
            int k=ps1.executeUpdate();
            if(k>0){
                status="Product Removed Successfully!";
                ps2=conn.prepareStatement("Delete from usercart where prodid=?");
                ps2.setString(1, prodId);
                k=ps2.executeUpdate();                
            }            
        }catch(SQLException ex){
            status="Error:"+ex.getMessage();
            System.out.println("Error in removeProduct:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeStatement(ps1);
        DBUtil.closeStatement(ps2);
        return status;
    }
    
}
