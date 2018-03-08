/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DBConnection {
    
    public Connection createConnection()//method to create connection
    {
        Connection con=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/atm","root","");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
    public int addValues(String SQL)//method to add values to add to database
    {
        int row=0;
        try
        {
            Connection con=createConnection();
            Statement stat=con.createStatement();
            row=stat.executeUpdate(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    public ResultSet getValues(String SQL)// method to retrieve values from the database
    {
        ResultSet rs=null;
        try
        {
            Connection con=createConnection();
            Statement stat=con.createStatement();
            rs=stat.executeQuery(SQL);
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        return rs;
    }
    
}
