/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mypc
 */
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            //アクセスルートセッション作成
            HttpSession session = request.getSession();
            session.setAttribute("ac",(int)(Math.random() * 1000));
            
            SearchYahoo sy = new SearchYahoo();
            SearchDateBox sdbBox = new SearchDateBox();
            try {
                //リクエストキーワードをとれればリクエスト、なければsessionキーワード
                if (request.getParameter("txtkeyword") != null && !request.getParameter("txtkeyword").equals("")){
                    sdbBox = sy.SearchYahoo(request.getParameter("txtkeyword"));
                    session.setAttribute("keyword", request.getParameter("txtkeyword"));
                }else if(session.getAttribute("keyword") != null){
                    sdbBox = sy.SearchYahoo((String)session.getAttribute("keyword"));
                }else{
                    //前回ページがサーチでなくキーワードがない場合エラーを返す
                    request.setAttribute("error", "値が未入力です");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }

                //セッションに商品データと閲覧ページ情報を挿入
                session.setAttribute("sdbBox", sdbBox);
                session.setAttribute("page", "Search");
                
                RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
