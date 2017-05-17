/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_ice_cream;

/**
 *
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String databasename="happycup";

    public long insertOrder(clsOrder object) throws Exception 
    {
        try{
            if (connect ==null) connecttoDB();
            
            preparedStatement = connect.prepareStatement("insert into "+ this.databasename +".order (orderid,orderdate,totalwithouttax,tax,totalwithtax) values(default,?,?,?,?)");
            preparedStatement.setInt(1,  (int)(new Date().getTime()/1000) );
            preparedStatement.setDouble(2, object.getTotalwithouttax());
            preparedStatement.setDouble(3, object.getTax());
            preparedStatement.setDouble(4, object.getTotalwithtax());
//            System.out.println(preparedStatement.toString());
            if (preparedStatement.executeUpdate()>0)
            {
                long newid =0;
                 preparedStatement = connect.prepareStatement("select max(orderid) as newid from "+ this.databasename +".order");
                 resultSet = preparedStatement.executeQuery();
                 resultSet.next();
                 newid = resultSet.getLong("newid");
                return newid;
            }
            else{
                return -1;
            }
            
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }

    public boolean updateOrder(clsOrder object) throws Exception 
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("update "+ this.databasename +".order set totalwithouttax= ?,tax = ?,totalwithtax=? where orderid=? ");
            preparedStatement.setDouble(1, object.getTotalwithouttax());
            preparedStatement.setDouble(2, object.getTax());
            preparedStatement.setDouble(3, object.getTotalwithtax());
            preparedStatement.setLong(4, object.getOrderid());
            if (preparedStatement.executeUpdate()>0)
                return true;
            else
                return false;
            
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    public boolean deleteOrder(long orderid)throws Exception 
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("delete from "+ this.databasename +".order where orderid=? ");
            preparedStatement.setLong(1, orderid);
            if (preparedStatement.execute())
                return true;
            else
                return false;
            
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    public ResultSet SelectOrder(long orderid) throws Exception
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("select * from "+ this.databasename +".order where orderid=?");
            preparedStatement.setLong(1, orderid);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    
    public boolean insertItem(clsOrderItem object) throws Exception 
    {
        try{
            if (connect ==null) connecttoDB();
            
            preparedStatement = connect.prepareStatement("insert into "+ this.databasename +".order_item (itemid,"
                    + "orderid,type,"
                    + "almonds,"
                    + "peanut,"
                    + "mint,"
                    + "peach,"
                    + "lemon,"
                    + "pumpkin,"
                    + "nutella,"
                    + "totalwithouttax,"
                    + "tax,"
                    + "totalwithtax) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, object.getItemId());
            preparedStatement.setLong(2, object.getOrderId());
            preparedStatement.setString(3, object.getType());
            preparedStatement.setInt(4, object.getAlmonds()?1:0);
            preparedStatement.setInt(5, object.getPeanut()?1:0);
            preparedStatement.setInt(6, object.getMint()?1:0);
            preparedStatement.setInt(7, object.getPeach()?1:0);
            preparedStatement.setInt(8, object.getLemon()?1:0);
            preparedStatement.setInt(9, object.getPumpkin()?1:0);
            preparedStatement.setInt(10, object.getNutella()?1:0);
            preparedStatement.setDouble(11, object.getTotalwithouttax());
            preparedStatement.setDouble(12, object.getTax());
            preparedStatement.setDouble(13, object.getTotalwithtax());
            if (preparedStatement.executeUpdate()>0)
                return true;
            else
                return false;
            
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    public boolean updateItem(clsOrderItem object) throws Exception 
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("update "+ this.databasename +".order_item set type = ?,"
                    + "almonds =?,"
                    + "peanut= ?,"
                    + "mint =?,"
                    + "peach =?,"
                    + "lemon =?,"
                    + "pumpkin =?,"
                    + "nutella =?,"
                    + "totalwithouttax= ?,"
                    + "tax = ?,"
                    + "totalwithtax=? "
                    + "where orderid=? and itemid=?");
            preparedStatement.setString(1, object.getType());
            preparedStatement.setInt(2, object.getAlmonds()?1:0);
            preparedStatement.setInt(3, object.getPeanut()?1:0);
            preparedStatement.setInt(4, object.getMint()?1:0);
            preparedStatement.setInt(5, object.getPeach()?1:0);
            preparedStatement.setInt(6, object.getLemon()?1:0);
            preparedStatement.setInt(7, object.getPumpkin()?1:0);
            preparedStatement.setInt(8, object.getNutella()?1:0);
            preparedStatement.setDouble(9, object.getTotalwithouttax());
            preparedStatement.setDouble(10, object.getTax());
            preparedStatement.setDouble(11, object.getTotalwithtax());
            preparedStatement.setLong(12, object.getOrderId());
            preparedStatement.setLong(13, object.getItemId());
            if (preparedStatement.executeUpdate()>0)
                return true;
            else
                return false;
            
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    
    public boolean deleteItem(long orderid,long itemid)throws Exception 
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("delete from "+ this.databasename +".order_item where orderid=? and itemid=? ");
            preparedStatement.setLong(1, orderid);
            preparedStatement.setLong(2, itemid);
            if (preparedStatement.execute())
                return true;
            else
                return false;
            
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    
    public ResultSet SelectOrderItems(long orderid) throws Exception
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("select * from "+ this.databasename +".order_item where orderid=?");
            preparedStatement.setLong(1, orderid);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch (Exception ex)
        {
            throw (ex);
        }
    }
    
    public ResultSet getTopSellingItem() throws SQLException
    {
        try{
            if (connect ==null) connecttoDB();
            preparedStatement = connect.prepareStatement("select type,count(itemid) as sellingcount from "+ this.databasename +".order_item group by type order by count(itemid) desc limit 1");
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        
    }
    private void connecttoDB() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/happycup?"
                            + "user=root&password=Abc123");
            
        }
        catch (Exception ex)
        {
            throw(ex);
        }
        
    }


}
