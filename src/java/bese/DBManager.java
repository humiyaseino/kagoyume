/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bese;

import java.sql.*;

/**
 *
 * @author mypc
 */
public class DBManager {

    public static Connection getConnectin() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","seino","seino");
            System.out.println("データベース接続成功");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("classエラー"+e.getMessage());
            throw new IllegalMonitorStateException();
        } catch (SQLException e) {
            System.out.println("SQLエラー"+e.getMessage());
            throw new IllegalMonitorStateException();
        }
    }
}
