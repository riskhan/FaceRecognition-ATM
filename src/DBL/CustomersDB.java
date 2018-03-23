/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBL;

import java.sql.ResultSet;


/**
 *
 * @author User
 */
public class CustomersDB {
    
    public int addCustomers(Customers obj)
    {
        int row=0;
        try
        {
            String SQL="INSERT INTO customerdetails VALUES(null,'"+obj.getName()+"','"+obj.getAddress()+"',"
                    + "'"+obj.getMobile()+"')";
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    public int addAccountdet(Customers obj)
    {
        int row=0;
        try
        {
            String SQL="INSERT INTO accountdetails VALUES((SELECT MAX(id) FROM customerdetails),"+obj.getAccount()+",1234,"+obj.getDate()+""
                    + ",00,00)";
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    public ResultSet getDetails(Customers obj)
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from customerdetails where ID="+obj.getID()+"";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getAccountdetails(Customers obj)
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from accountDetails where ID="+obj.getID()+"";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public int widthDrawmoney(Customers obj)
    {
        int row=0;
        try
        {
            String SQL="update accountdetails set accBalance=accBalance-"+obj.getAccountBal()+""
                    + ",lastWithdraw="+obj.getAccountBal()+"";
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    public ResultSet getAlldetails()
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from customerdetails";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    
}
