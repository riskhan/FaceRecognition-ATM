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
public class UsersDB {
    
    public int addCustomers(Users obj)
    {
        int row=0;
        try
        {
            String SQL="INSERT INTO customerdetails(CName,CAdd,Mobile)VALUES('"+obj.getName()+"','"+obj.getAddress()+"',"
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
    
    public int addAccountdet(Users obj)
    {
        int row=0;
        try
        {
            String SQL="INSERT INTO accountdetails VALUES(last_insert_id(),"+obj.getAccount()+","+obj.getDate()+""
                    + ","+obj.getAccountBal()+","+obj.getLastwidthdraw()+")";//insert the last insert id to the first parameter
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    public ResultSet getDetails(Users obj)
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
    
    public ResultSet getAccountdetails(Users obj)
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
    
    public int widthDrawmoney(Users obj)
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
    
    
}
