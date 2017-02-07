/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.*;

/**
 *
 * @author mypc
 */
public class SearchYahoo {

    private final String appid = "dj0zaiZpPXpNM3JlSEN0NWNLcyZzPWNvbnN1bWVyc2VjcmV0Jng9OGY-";
    private final String yahooURL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch";

    //検索してそれぞれの値をSearchDateBeansにセットして返す
    public SearchDateBox SearchYahoo(String keyword) {
        SearchDateBeans sdb = null;
        SearchDateBox sdbBox = new SearchDateBox();
        
        try {
            
            URL testURL = new URL(yahooURL + "?appid=" + appid + "&query=" + keyword);// URLインスタンス作成
            URLConnection urlcon = testURL.openConnection();// URL接続
            
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbfactory.newDocumentBuilder();

            Document doc = builder.parse(urlcon.getInputStream());// xmlを取得

            // ルート要素のチェック
            Element root = doc.getDocumentElement();// ルート取得

            // ノードの個数チェック
            NodeList list = root.getElementsByTagName("Hit");// ノードリスト取得

            for (int i = 0; i < list.getLength(); i++) {
                Element e = (Element) list.item(i);
                sdb = new SearchDateBeans();
                sdb.setKeyword(keyword);
                sdb.setTotalHit(list.getLength());
                
                sdb.setSdbID(i);
                sdb.setCode(getChildren(e,"Code"));
                sdb.setName(getChildren(e, "Name"));
                sdb.setPrice(getChildren(e,"Price"));
                sdb.setDescription(getChildren(e,"Description"));
                //画像イメージの要素取得
                NodeList imageNL = e.getElementsByTagName("Image");
                Element imageEL = (Element)imageNL.item(0);
                sdb.setImage(getChildren(imageEL,"Small"));

                sdbBox.setSdbBox(i,sdb);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return sdbBox;
    }
    //エレメントから指定の子ノードのエレメントを得るメソッド

    public String getChildren(Element element, String tagName) {
        NodeList list = element.getElementsByTagName(tagName);
        Element cElement = (Element) list.item(0);
        return cElement.getFirstChild().getNodeValue();
    }
    //アイテムコードからsdb情報をとるメソッド
    /*エラーが多く非推奨
    public SearchDateBeans searchYahooSdb(String itemCode) {
        SearchDateBeans sdb = null;
        
        try {
            
            URL testURL = new URL(yahooURL + "?appid=" + appid + "&query=" + itemCode);// URLインスタンス作成
            URLConnection urlcon = testURL.openConnection();// URL接続
            
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbfactory.newDocumentBuilder();

            Document doc = builder.parse(urlcon.getInputStream());// xmlを取得

            // ルート要素のチェック
            Element root = doc.getDocumentElement();// ルート取得

            // ノードの個数チェック
            NodeList list = root.getElementsByTagName("Hit");// ノードリスト取得

            for (int i = 0; i < list.getLength(); i++) {
                Element e = (Element) list.item(i);
                sdb = new SearchDateBeans();
                
                sdb.setCode(getChildren(e,"Code"));
                sdb.setName(getChildren(e, "Name"));
                sdb.setPrice(getChildren(e,"Price"));
                sdb.setDescription(getChildren(e,"Description"));
                
                NodeList imageNL = e.getElementsByTagName("Image");
                Element imageEL = (Element)imageNL.item(0);
                sdb.setImage(getChildren(imageEL,"Small"));

            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return sdb;
    }
*/
}
