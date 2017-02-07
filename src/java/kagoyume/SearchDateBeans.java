/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author mypc
 */
/*詳細データを入出力するbeans
 *
 */
public class SearchDateBeans implements Serializable {
    
    private int sdbID;
    private int totalHit;
    private String keyword;//検索キーワード
    private String itemCode;//アイテムコード
    private String image;//画像
    private String name;
    private int price;//値段
    private String description;//詳細説明
    private Timestamp newDate;//カートに入れた日時
    
   
    
    public SearchDateBeans() {
        
    }
    public int getSdbID(){
        return sdbID;
    }
    public void setSdbID(int sdbID){
        this.sdbID = sdbID;
    }
    public int getTotalHit(){
        return totalHit;
    }
    public void setTotalHit(int totalHit){
        this.totalHit = totalHit;
    }
    
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getCode(){
        return itemCode;
    }
    public void setCode(String code){
        this.itemCode = code;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    public int getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price =Integer.parseInt(price);
    }
    public void setPrice(int price){
        this.price = price;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
    public Timestamp getNewDate(){
        return newDate;
    }
    public void setNewDate(){
        this.newDate = new Timestamp(System.currentTimeMillis());
    }
    public void setNewDate(Timestamp newDate){
        this.newDate = newDate;
    }
}