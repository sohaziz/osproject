/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_ice_cream;

import java.io.Serializable;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class clsOrder {

    private Long orderid;
    private int orderdate;
    private double totalwithouttax;
    private double tax;
    private double totalwithtax;
    private clsOrderItem[] clsOrderItemCollection;

    public clsOrder() {
    }

    public clsOrder(Long orderid) {
        this.orderid = orderid;
    }

    public clsOrder(Long orderid, int orderdate, double totalwithouttax, double tax, double totalwithtax) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.totalwithouttax = totalwithouttax;
        this.tax = tax;
        this.totalwithtax = totalwithtax;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public int getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(int orderdate) {
        this.orderdate = orderdate;
    }

    public double getTotalwithouttax() {
        return totalwithouttax;
    }

    public void setTotalwithouttax(double totalwithouttax) {
        this.totalwithouttax = totalwithouttax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalwithtax() {
        return totalwithtax;
    }

    public void setTotalwithtax(double totalwithtax) {
        this.totalwithtax = totalwithtax;
    }

    public clsOrderItem[] getClsOrderItemCollection() {
        return clsOrderItemCollection;
    }

    public void setClsOrderItemCollection(clsOrderItem[] clsOrderItemCollection) {
        this.clsOrderItemCollection = clsOrderItemCollection;
    }
    private void calculateTotals()
    {
        double totalwithtax=0;
        double tax=0;
        double totalwithouttax=0;
        if (this.clsOrderItemCollection.length>0)
        {
            for (clsOrderItem orderItem : clsOrderItemCollection) {
                totalwithouttax += orderItem.getTotalwithouttax();
                tax += orderItem.getTax();
                totalwithtax += orderItem.getTotalwithtax();
                
            }
        }
        this.totalwithouttax = totalwithouttax;
        this.tax = tax;
        this.totalwithtax = totalwithtax;
    }
    public boolean Save() {
        boolean saved;
        this.calculateTotals();
        if ((this.orderid == null) || (this.orderid == 0)) {
            saved = this.insert();
            if (saved)
            {
                for (int i=0;i<this.getClsOrderItemCollection().length;i++)
                {
                    clsOrderItem item = this.getClsOrderItemCollection()[i];
                    item.setOrderId(this.orderid);
                    item.Save();
                    item.setisEdit(true);
                }
            }
            return saved;
        } else {
            saved = this.update();
            if (saved)
            {
                for (int i=0;i<this.getClsOrderItemCollection().length;i++)
                {
                    clsOrderItem item = this.getClsOrderItemCollection()[i];
                    item.setOrderId(this.orderid);
                    item.Save();
                    item.setisEdit(true);
                }
            }
            return saved;
        }
    }

    private boolean insert() {
        MySQLAccess mysql = new MySQLAccess();
        try {
            long newid = mysql.insertOrder(this);
            if (newid==-1)
            {
                return false;
            }
            else
            {
                this.setOrderid(newid);
                return true;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

    }

    private boolean update() {
        MySQLAccess mysql = new MySQLAccess();
        try {
            return mysql.updateOrder(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    

}
