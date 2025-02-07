/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.DemandDao;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class DemandImpl implements DemandDao{

    @Override
    public List<DemandPojo> haveDemanded(String prodId) {
        List<DemandPojo>demandList = new ArrayList<>() ;
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps = null;
        ResultSet rs = null ;
        try {
            ps = conn.prepareStatement("select * form userdemand where prodid = ? ") ;
            ps.setString(1, prodId);
            rs = ps.executeQuery() ;
            while (rs.next()) {                
                DemandPojo demandPojo  = new DemandPojo() ;
                demandPojo.setUseremail("useremail");
                demandPojo.setProdId("prodid");
                demandPojo.setDemandQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in haveDemanded"+ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        DBUtil.closeResultSet(rs);
        return demandList ;
    }

    @Override
    public boolean addProduct(DemandPojo demandPojo) {
        boolean status = false ;
        String updateSQL = "update userdemand set quantity =? where useremail=? and prodid = ? " ;
        String insertSQL = "insert into userdemand values(?,?,?) " ;
        Connection conn = DBUtil.provideConnection() ;
        PreparedStatement ps1 = null ;
        PreparedStatement ps2 = null ;
        try {
            ps1 = conn.prepareStatement(updateSQL) ;
            ps1.setInt(1, demandPojo.getDemandQuantity());
            ps1.setString(2, demandPojo.getUseremail());
            ps1.setString(3, demandPojo.getProdId());
            int k = ps1.executeUpdate() ;
            if (k==0) {
            ps2 = conn.prepareStatement(insertSQL) ;
            ps2.setString(1, demandPojo.getUseremail());
            ps2.setString(2, demandPojo.getProdId());
            ps2.setInt(3, demandPojo.getDemandQuantity());
                
            }
            status = true ;
        } catch (SQLException ex) {
            System.out.println("Error in addProduct: "+ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps2);
        DBUtil.closeStatement(ps1);
        return status;
    }

    @Override
    public boolean removeProduct(String userId, String prodId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
