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
public class AdminDB {
    
    public ResultSet login(Admin obj)//login method
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from loginDetails where userID='"+obj.getUsername()+"' and password='"+obj.getPassword()+"'";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public int addUser(Admin obj)
    {
        int row=0;
        try
        {
            String SQL="insert into loginDetails values('"+obj.getUsername()+"','"+obj.getPassword()+"')";
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
