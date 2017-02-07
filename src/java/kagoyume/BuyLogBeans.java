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
 * 購入履歴用のbeans
 *
 * @author mypc
 */
public class BuyLogBeans implements Serializable {

    private ArrayList<Integer> buyIDList = new ArrayList<>();
    private ArrayList<String> itemNameList = new ArrayList<>();
    private ArrayList<Integer> itemPriceList = new ArrayList<>();
    private ArrayList<Integer> typeList = new ArrayList<>();
    private ArrayList<Date> buyDateList = new ArrayList<>();
    private ArrayList<String> itemCodeList = new ArrayList<>();

    public BuyLogBeans() {};

    
    
    public int getBuyID(int num) {
        return buyIDList.get(num);
    }
    public ArrayList<Integer> getBuyIDList(){
        return buyIDList;
    }

    public void setBuyIDList(int buyID) {
        this.buyIDList.add(buyID);
    }

    public String getItemNameList(int num) {
        return itemNameList.get(num);
    }

    public void setItemNameList(String ItemName) {
        this.itemNameList.add(ItemName);
    }

    public int getItemPriceList(int num) {
        return itemPriceList.get(num);
    }

    public void setItemPriceList(int ItemPrice) {
        this.itemPriceList.add(ItemPrice);
    }

    public int getTypeList(int num) {
        return typeList.get(num);
    }

    public void setTypeList(int type) {
        this.typeList.add(type);
    }

    public Date getBuyDateList(int num) {
        return buyDateList.get(num);
    }

    public void setBuyDateList(Date buyDate) {
        this.buyDateList.add(buyDate);
    }
    public String getItemCodeList(int num){
        return itemCodeList.get(num);
    }
    public void setItemCodeList(String itemCode){
        this.itemCodeList.add(itemCode);
    }
}
