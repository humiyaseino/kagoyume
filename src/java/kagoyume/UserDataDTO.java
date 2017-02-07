/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.Date;

/**
 * Databaseと連携してUserDataを格納するbeans 前回、カートに入れた商品のアイテムコードも格納
 *
 * @author mypc
 */
public class UserDataDTO implements Serializable {

    //ユーザーデータ
    private int UserID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;//いままでの購入総額
    private Date Date;//登録日時
    private int deleteFlg;//0=初期値 1=削除フラグtrue

    //購入データ
    private int buyID;
    private String itemCode;
    private int type;//発送方法
    private Date buyDate;//購入日時

    private int buyTotal;//購入総額

    public UserDataDTO() {
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int ID) {
        this.UserID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getdeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int flg) {
        this.deleteFlg = flg;
    }

    //購入データ(userIDは兼用)
    public int getBuyID() {
        return buyID;
    }

    public void setBuyID(int buyID) {
        this.buyID = buyID;
    }

    public String getItemcode() {
        return itemCode;
    }

    public void setItemcode(String itemcode) {
        this.itemCode = itemcode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public int getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(int buyTotal) {
        this.buyTotal = buyTotal;
    }
}
