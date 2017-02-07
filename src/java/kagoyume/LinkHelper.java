/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;


/**
 * 様々なページのリンクを表示するヘルパークラス
 * また表示を変えるメソッドも追加
 *
 * @author mypc
 */
public class LinkHelper {

    private final String topURL = "index.jsp";
    private final String loginURL = "login";
    private final String registrationURL = "Registration";
    private final String myDateURL = "Mydata";
    private final String cartURL = "Cart";
    
    public static LinkHelper getInstence(){
        return new LinkHelper();
    }
    //それぞれのリンクを返すメソッド
    public String topURL(){
        return topURL;
    }
    public String loginURL(){
        return loginURL;
    }
    
    public String registrationURL(){
        return registrationURL;
    }
    public String myDateURL(){
        return myDateURL;
    }
    public String cartURL(){
        return cartURL;
    }
    
    //ログインとログアウトを表示
    public String logchk(Object ub){
        String word;
        if (ub == null){
            word = "ログイン";    
        }else{
            word = "ログアウト";
        }
        return word;
    }
    //カートの数の表示
    public String cartSize(Object size){
        String word;
        if (size == null){
            word = "カートの中身(0)個";
        }else{
            int intsize = (Integer)size;
            word = "カートの中身("+intsize+")個";
        }
        return word;
    }
    //発送方法の表示
    public String typechk(Object type){
        int itype = (Integer)type;
        String word = "no meg";
        switch(itype){
            case 1:
                word = "通常便";
                break;
            case 2:
                word = "急便";
                break;    
        }
        return word;
    }
}
