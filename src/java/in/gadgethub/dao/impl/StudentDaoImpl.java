/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.StudentDao;
import in.gadgethub.pojo.StudentPojo;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class StudentDaoImpl implements StudentDao{

    @Override
    public List<StudentPojo> getAllStudents() {
        List<StudentPojo> students = new ArrayList<>() ;
        Connection conn = DBUtil.provideConnection() ;
        Statement stmt = null ;
        ResultSet rs = null ;
        if (conn == null) {
            throw new IllegalStateException("Database connection is null.");
        }
        try {
            stmt = conn.createStatement() ;
            rs = stmt.executeQuery("select * from student") ;
            while(rs.next()) {
            StudentPojo std = new StudentPojo();
            std.setId(rs.getInt("id"));
            std.setName(rs.getString("name"));
            std.setEmail(rs.getString("email"));
            std.setCourse(rs.getString("course"));
            students.add(std) ;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in StudentDaoImpl");
            ex.printStackTrace();  
        }
        return students ;
    }
    
    
}
