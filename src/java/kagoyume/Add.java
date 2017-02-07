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
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author mypc
 */
public class Add extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            try {
                //アクセスルートチェック
                String accesschk = request.getParameter("ac");
                if (accesschk == null || (int) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
                    throw new Exception("不正なアクセスです");
                }
                SearchDateBeans sdb = (SearchDateBeans)session.getAttribute("sdb");
                //カートに入れた時間を挿入
                sdb.setNewDate();
                //session[cartBox]を更新
                CartBox cb;
                if (session.getAttribute("cart") != null) {
                    cb = (CartBox) session.getAttribute("cart");
                } else {
                    cb = new CartBox();
                }
                //session[cart]に商品を追加
                cb.setCratBoxSdb(sdb);
                session.setAttribute("cart", cb);
                request.setAttribute("addCart", sdb);
                //カートに誰が何を入れたかの処理
                if (session.getAttribute("ud") != null){
                    UserData ud = (UserData)session.getAttribute("ud");
                    UserDataDTO udd = new UserDataDTO();
                    ud.DTOMapping(udd);
                    
                    UserDataDAO.getInstance().cartDataInsert(udd,sdb);
                }
                    
                //sessionの削除
                session.removeAttribute("sdb");

                request.getRequestDispatcher("/add.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
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
