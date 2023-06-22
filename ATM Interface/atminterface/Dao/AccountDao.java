/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atminterface.Dao;

import atminterface.Pojo.AccountsPojo;
import atminterface.dbutil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class AccountDao {
    public static boolean registrationNewUser(AccountsPojo ap) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into accounts values(?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, ap.getAcc_no());
        ps.setString(2, ap.getFirst_name());
        ps.setString(3, ap.getLast_name());
        ps.setDate(4, ap.getDob());
        ps.setString(5, ap.getMobile());
        ps.setString(6, ap.getAddress());
        ps.setString(7, ap.getOccupation());
        ps.setInt(8, ap.getBalance());
        ps.setString(9, ap.getPin());
        return ps.executeUpdate() == 1;
}
    public static String getAccountDetails(AccountsPojo pr) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select first_name from accounts where acc_no = ? and pin = ?");
         ps.setInt(1, pr.getAcc_no());
        ps.setString(2, pr.getPin());
        ResultSet rs = ps.executeQuery();
        String name = null;
        if (rs.next()) {
            name = rs.getString("first_name");
        }
        return name;
    }
    
     public static int getBalance(int acc_n) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from accounts where acc_no = ? ");
        ps.setInt(1, acc_n);
        ResultSet rs = ps.executeQuery();
        int bal = 0;
        if(rs.next()){
             System.out.println(rs.getInt("balance"));
        bal  = rs.getInt("balance");
        
        }
        
        return bal;
    }

    
    public static boolean depositeBalance(int acc_no, int newblance, int oldbalance) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update accounts set balance = ? where acc_no = ?");
        int bal = newblance+oldbalance;
        ps.setInt(1, bal);
        ps.setInt(2, acc_no);
        System.out.println(ps.executeUpdate()==1);
        return ps.executeUpdate() == 1;
        
    }
    
    public static boolean withdrawbalance(int acc_no, int withdrawmony, int oldBalance) throws SQLException{
         Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update accounts set balance = ? where acc_no = ?");
         ps.setInt(1, oldBalance - withdrawmony);
        ps.setInt(2, acc_no);
        return ps.executeUpdate() == 1;
    }
    
     public static boolean depositeBalanceByAnother(int acc_n, int new_balance, int oldBalance1) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update accounts set balance = ? where acc_no = ?");
        ps.setInt(1, oldBalance1 + new_balance);
        ps.setInt(2, acc_n);
        return ps.executeUpdate() == 1;
    }
     
     public static String getAccountDetailsByAccno(int acc_no) throws SQLException{
         Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("select first_name from accounts where acc_no = ?");
         ps.setInt(1, acc_no);
         ResultSet rs = ps.executeQuery();
         String name = null;
         if(rs.next()){
             name = rs.getString(1);
         }
         return name;
     }
}
