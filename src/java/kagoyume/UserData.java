/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * フォームからのデータを入出力するbeans、フォームのnullチェック、またUserDataDTOからの変換、挿入させるメソッドをもつ
 *
 * @author mypc
 */
public class UserData implements Serializable {

    public UserData() {
    }
    private int userID;
    private String name;
    private String pass;
    private String mail;
    private String address;
    private int total;//購入総額
    private Date newDate;//登録日時
    private int deleteFlg;//0=初期値 1=削除フラグtrue

    //購入データ
    private int buyID;
    private String itemCode;
    private int type;//発送方法
    private Date buyDate;//購入日時
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public Date getNewData() {
        return newDate;
    }

    public void setNewData(Date newDate) {
        this.newDate = newDate;
    }

    public int getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

        //購入データ(userIDは兼用)
    public int getBuyID(){
        return buyID;
    }
    public void setBuyID(int buyID){
        this.buyID = buyID;
    }
    public String getItemcode(){
        return itemCode;
    }
    public void setItemcode(String itemcode){
        this.itemCode = itemcode;
    }
    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    public Date getBuyDate(){
        return buyDate;
    }
    public void setBuyDate(Date buyDate){
        this.buyDate = buyDate;
    }
    //フォームのnullの項目をarrayList<String>で返すメソッド
    public ArrayList<String> nullChk() {
        ArrayList<String> chkList = new ArrayList<>();
        if (this.name.equals("")) {
            chkList.add("name");
        }
        if (this.pass.equals("")) {
            chkList.add("pass");
        }
        if (this.mail.equals("")) {
            chkList.add("mail");
        }
        if (this.address.equals("")) {
            chkList.add("address");
        }
        return chkList;
    }

    //UserDataの情報をUserDataDTOへマッピング
    public void DTOMapping(UserDataDTO udd) {
        //新規登録用フォーム
        udd.setName(this.name);
        udd.setPassword(this.pass);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        //購入データ用
        udd.setUserID(this.userID);
        udd.setItemcode(this.itemCode);
        udd.setType(this.type);
        udd.setTotal(this.total);
    }
    

    //UserDataDTOの情報をUserDataへマッピング
    public void UDMapping(UserDataDTO udd) {
        //ユーザーデータの取り出し
        this.userID = udd.getUserID();
        this.name = udd.getName();
        this.pass = udd.getPassword();
        this.mail = udd.getMail();
        this.address = udd.getAddress();
        this.total = udd.getTotal();
        this.newDate = udd.getDate();
        this.deleteFlg = udd.getdeleteFlg();
        //購入データの取り出し
        this.buyID = udd.getBuyID();
        this.itemCode = udd.getItemcode();
        this.type = udd.getType();
        this.buyDate = udd.getBuyDate();
    }
}
