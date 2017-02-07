/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * カートの中にsdb形式で商品データをいれるbeans 合計金額を割り出すメソッドなどもある
 *
 * @author mypc
 */
public class CartBox implements Serializable {

    private int total;
    private ArrayList<SearchDateBeans> cartBox = new ArrayList<>();

    public CartBox() {
    }

    public SearchDateBeans getCartBoxSdb(int i) {
        return cartBox.get(i);
    }

    public void setCratBoxSdb(SearchDateBeans sdb) {
        this.cartBox.add(sdb);
    }

    public ArrayList<SearchDateBeans> getCartBox() {
        return cartBox;
    }

    public int getTotal() {
        total = 0;
        for (int i = 0; i < this.cartBox.size(); i++) {
            this.total += this.cartBox.get(i).getPrice();
        }
        return total;
    }

    public void setTotal(CartBox cb) {
        this.total = cb.total;
    }
    
    //カートに入れた時間で並び替えできるメソッド
    public CartBox bubbleSortCartBox(CartBox cb) {
        ArrayList<SearchDateBeans> al = new ArrayList<>();
        for (int i = 0; i < cb.getCartBox().size(); i++) {
            al.add(cb.getCartBoxSdb(i));
        }
        for (int i = 0; i < al.size() - 1; i++) {
            for (int j = 0; j < al.size() - (1 + i); j++) {
                if (al.get(i).getNewDate().before(al.get(i + 1).getNewDate())) {
                    SearchDateBeans sdb1 = al.get(i);
                    SearchDateBeans sdb2 = al.get(i+1);
                    al.set(i, sdb2);
                    al.set((i+1), sdb1);
                }
            }
        }
        return cb;
    }
    //特定のカート情報の削除
    public void cartDelete(int num){
        this.cartBox.remove(num);
    }
}
