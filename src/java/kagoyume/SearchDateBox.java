/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 検索してでてきたSearchDateBeansを格納するbeans
 * @author mypc
 */
public class SearchDateBox implements Serializable{
    
    private ArrayList<SearchDateBeans> sdbBox = new ArrayList();
    
    public SearchDateBox(){}
    
    public void setSdbBox(int ID,SearchDateBeans sdb){
        this.sdbBox.add(new SearchDateBeans());
        this.sdbBox.set(ID, sdb);
    }
    public SearchDateBeans getSdb(int i){
        return sdbBox.get(i);
    }
    public ArrayList<SearchDateBeans> getSdbBox(){
        return sdbBox;
    }
    
}
