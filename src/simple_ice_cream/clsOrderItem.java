/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_ice_cream;

import java.io.Serializable;
import java.sql.ResultSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class clsOrderItem {

    private long orderid;
    private long itemid;
    private String type;
    
    private boolean almonds;
    private boolean peanut;
    private boolean mint;
    private boolean peach;
    private boolean lemon;
    private boolean pumpkin;
    private boolean nutella;
    private double totalwithouttax;
    private double tax;
    private double totalwithtax;
    private boolean _isEdit;
    private clsOrder clsOrder;

    public clsOrderItem() {
    }
 public static ResultSet TopSellingItems()
    {
        
        try {
            MySQLAccess mysql = new MySQLAccess();
            return mysql.getTopSellingItem();
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    
   
    public clsOrderItem(long itemid, long orderid, String type, boolean almonds, boolean peanut, boolean mint, boolean peach, boolean lemon, boolean pumpkin, boolean nutella, double totalwithouttax, double tax, double totalwithtax) {
        this.itemid = itemid;
        this.orderid = orderid;
        this.type = type;
        this.almonds = almonds;
        this.peanut = peanut;
        this.mint = mint;
        this.peach = peach;
        this.lemon = lemon;
        this.pumpkin = pumpkin;
        this.nutella = nutella;
        this.totalwithouttax = totalwithouttax;
        this.tax = tax;
        this.totalwithtax = totalwithtax;
    }

    public long getItemId() {
        return itemid;
    }

    public void setItemId(long itemid) {
        this.itemid = itemid;
    }

    public long getOrderId() {
        return orderid;
    }

    public void setOrderId(long orderid) {
        this.orderid = orderid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public double getTypePrice (){
        //ENUM('CAPPUCCINO', 'LATTE', 'ESPRESSO', 'COFE MOCHA')
        if (this.type=="CAPPUCCINO")
        {
            return 8;
        }
        else if (this.type=="LATTE")
        {
            return 9;
        }
        else if (this.type=="ESPRESSO")
        {
            return 8;
        }
        else
        {
            return 10;
        }
        
    }
    public boolean getAlmonds() {
        return almonds;
    }

    public void setAlmonds(boolean almonds) {
        this.almonds = almonds;
    }

    public boolean getPeanut() {
        return peanut;
    }

    public void setPeanut(boolean peanut) {
        this.peanut = peanut;
    }

    public boolean getMint() {
        return mint;
    }

    public void setMint(boolean mint) {
        this.mint = mint;
    }

    public boolean getPeach() {
        return peach;
    }

    public void setPeach(boolean peach) {
        this.peach = peach;
    }

    public boolean getLemon() {
        return lemon;
    }

    public void setLemon(boolean lemon) {
        this.lemon = lemon;
    }

    public boolean getPumpkin() {
        return pumpkin;
    }

    public void setPumpkin(boolean pumpkin) {
        this.pumpkin = pumpkin;
    }

    public boolean getNutella() {
        return nutella;
    }

    public void setNutella(boolean nutella) {
        this.nutella = nutella;
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
    public void setisEdit(boolean isedit)
    {
        this._isEdit = isedit;
    }
    public clsOrder getClsOrder() {
        return clsOrder;
    }

    public void setClsOrder(clsOrder clsOrder) {
        this.clsOrder = clsOrder;
    }

    public boolean Save() {
        if (this._isEdit) {
            return this.update();

        } else {
            return this.insert();
        }
    }

    private boolean insert() {
        MySQLAccess mysql = new MySQLAccess();
        try {
            return mysql.insertItem(this);
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    private boolean update() {
        MySQLAccess mysql = new MySQLAccess();
        try {
           return mysql.updateItem(this);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
    @Override
    public String toString()
    {
        String ItemString = "Item Type :"+ String.format("%40s", this.getType())+ "  "+this.getTypePrice() + "\n";
        if (this.almonds)
            ItemString +=" + CUP CAKE:                   2.00"+"\n";
        if (this.peanut)
            ItemString +=" + DONUT:                      1.50"+"\n";
        if (this.mint)
            ItemString +=" + LOTUS:                      0.50"+"\n";
        if (this.peach)
            ItemString +=" + COOKIE:                     3.00"+"\n";
        if (this.lemon)
            ItemString +=" + MAVEN:                      2.00"+"\n";
        if (this.pumpkin)
            ItemString +=" + SOUFFLE:                    2.50"+"\n";
        if (this.nutella)
            ItemString +=" + BRAWNIE:                    4.00"+"\n";
            ItemString +="Total:                      " + this.getTotalwithouttax()+"\n";
            ItemString +="Tax:                        " + this.getTax()+"\n";
        ItemString +="Item Net Price:                    "+this.getTotalwithtax()+"\n";
        return ItemString;
    }
}
