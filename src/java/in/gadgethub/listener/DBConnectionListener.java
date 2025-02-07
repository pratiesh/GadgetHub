/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.listener;

import in.gadgethub.utility.DBUtil;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Pratik
 */
@WebListener
public class DBConnectionListener implements ServletContextListener {
    
    public DBConnectionListener() {
        System.out.println("connection listner object created ...............");
    }
    @Override    
    public void contextInitialized(ServletContextEvent sce) {
        // Retrieve database connection details from web.xml or context params
        ServletContext ctxt=sce.getServletContext();
        String dbUrl=ctxt.getInitParameter("dbUrl");
        String username=ctxt.getInitParameter("username");
        String password=ctxt.getInitParameter("password");
        // Open the database connection
        DBUtil.openConnection(dbUrl,username,password);
        System.out.println("Connection stablished....listner");
        
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBUtil.closeConnection();  
    }

}