/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mypc
 */
public class loginForm extends HttpServlet {

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

            HttpSession session = request.getSession();
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if (accesschk == null || (Integer) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
                throw new Exception("不正なアクセスです");
            }
            //ログインチェック
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPass");
            //データベース処理
            UserDataDTO udd = UserDataDAO.getInstance().login(name, password);
            UserData ud = new UserData();
            //uddデータをudにマッピング
            ud.UDMapping(udd);
            //ログイン成功ならセッションにユーザーデータを挿入
            if (ud.getName() != null) {
                session.setAttribute("ud", ud);
                //前回カートに入れた情報取得
                ud.DTOMapping(udd);
                CartBox cb = UserDataDAO.getInstance().cartDataSelect(udd);
                //カートに情報を追加
                if (session.getAttribute("cart") == null && cb.getCartBox().size() != 0) {
                    session.setAttribute("cart", cb);
                } else if (session.getAttribute("cart") != null && cb != null) {
                    //ゲスト時に入れた商品の呼び出し
                    CartBox gestCB = (CartBox) session.getAttribute("cart");
                    for (int i = 0; i < gestCB.getCartBox().size(); i++) {
                        cb.setCratBoxSdb(gestCB.getCartBoxSdb(i));
                    }
                    session.setAttribute("cart", cb);
                }

                //ログイン失敗ならloginページに戻る
            } else {
                request.setAttribute("error", "お名前かパスワードが間違っています。\n確認をお願いします");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

            //前回ページへ飛ぶ
            if (session.getAttribute("page") != null){
            String backURL = (String) session.getAttribute("page");
            request.getRequestDispatcher(backURL).forward(request, response);
            }else{
               request.getRequestDispatcher("/index.jsp").forward(request, response); 
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
