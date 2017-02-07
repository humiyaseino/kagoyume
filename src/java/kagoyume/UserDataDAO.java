/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import bese.DBManager;
import java.sql.*;
import kagoyume.SearchYahoo;

/**
 *
 * @author mypc
 */
public class UserDataDAO {

    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }

    //ユーザー情報新規登録
    public void insert(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("insert into user_t(name,password,mail,address,total,newDate,deleteFlg)values(?,?,?,?,0,?,0);");
            ps.setString(1, udd.getName());
            ps.setString(2, udd.getPassword());
            ps.setString(3, udd.getMail());
            ps.setString(4, udd.getAddress());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();

            System.out.println("insert完了");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    //ユーザー情報修正
    public void userUpdate(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("update user_t set name=?,password=?,mail=?,address=? where userID=?");
            ps.setString(1, udd.getName());
            ps.setString(2, udd.getPassword());
            ps.setString(3, udd.getMail());
            ps.setString(4, udd.getAddress());
            ps.setInt(5, udd.getUserID());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //ユーザーデータの削除フラグを1に
    public void userDelete(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("update user_t set deleteFlg=1 where userID=?");
            ps.setInt(1, udd.getUserID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //ログインしたときの個人情報取得
    public UserDataDTO login(String name, String pass) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDataDTO udd = new UserDataDTO();
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("select * from user_t Where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, pass);

            rs = ps.executeQuery();

            while (rs.next()) {
                udd.setUserID(rs.getInt("userID"));
                udd.setName(rs.getString("name"));
                udd.setMail(rs.getString("mail"));
                udd.setPassword(rs.getString("password"));
                udd.setAddress(rs.getString("address"));
                udd.setTotal(rs.getInt("total"));
                udd.setDate(rs.getDate("newDate"));
                udd.setDeleteFlg(rs.getInt("deleteFlg"));

            }
            //デリートフラグが１の場合、内部uddデータを消去する
            if (udd.getdeleteFlg()==1){
                udd = new UserDataDTO();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return udd;
    }

    //購入履歴登録
    public void insertBuy(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("insert into buy_t(userID,itemCode,type,buyDate) values(?,?,?,?)");
            ps.setInt(1, udd.getUserID());
            ps.setString(2, udd.getItemcode());
            ps.setInt(3, udd.getType());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        //ユーザーデータ総購入金額アップデート
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("update user_t set total=? where userID=?");
            ps.setInt(1, (udd.getTotal() + udd.getBuyTotal()));
            ps.setInt(2, udd.getUserID());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //購入履歴の検索
    public BuyLogBeans selectBuyData(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BuyLogBeans blb = new BuyLogBeans();

        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("select * from buy_t where userID=?");
            ps.setInt(1, udd.getUserID());

            rs = ps.executeQuery();

            while (rs.next()) {

                blb.setBuyIDList(rs.getInt("buyID"));
                blb.setItemCodeList(rs.getString("itemCode"));
                blb.setTypeList(rs.getInt("type"));
                blb.setBuyDateList(rs.getDate("buyDate"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return blb;
    }
    //カートに入れる商品データ挿入
    //全ての商品データを入れるように修正
    public void cartDataInsert(UserDataDTO udd,SearchDateBeans sdb)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("insert into cart_t_sdb(userID,name,image,price,description,newDate,itemCode)values(?,?,?,?,?,?,?)");
            ps.setInt(1, udd.getUserID());
            ps.setString(2, sdb.getName());
            ps.setString(3, sdb.getImage());
            ps.setInt(4, sdb.getPrice());
            ps.setString(5, sdb.getDescription());
            ps.setTimestamp(6, sdb.getNewDate());
            ps.setString(7, sdb.getCode());
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    //カートに入れたデータの再取得
    //データベースからすべての商品データを取り出す
    public CartBox cartDataSelect(UserDataDTO udd)throws SQLException{
        CartBox cb = new CartBox();
        SearchDateBeans sdb = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("select * from cart_t_sdb where userID=?");
            ps.setInt(1, udd.getUserID());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                sdb = new SearchDateBeans();
                sdb.setName(rs.getString("name"));
                sdb.setImage(rs.getString("image"));
                sdb.setPrice(rs.getInt("price"));
                sdb.setDescription(rs.getString("description"));
                sdb.setNewDate(rs.getTimestamp("newDate"));
                sdb.setCode(rs.getString("itemCode"));
                
                cb.setCratBoxSdb(sdb);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return cb;
    }
        //ユーザーカートの削除
    public void cartDataDelete(UserDataDTO udd,SearchDateBeans sdb)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnectin();
            ps = con.prepareStatement("delete from cart_t_sdb where userID=? and newDate=? ");
            ps.setInt(1, udd.getUserID());
            ps.setTimestamp(2, sdb.getNewDate());
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}
