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

/**
 *
 * @author mypc
 */
public class Myupdateresult extends HttpServlet {

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
            try {
                String accesschk = request.getParameter("ac");
                if (accesschk == null || (Integer) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
                    throw new Exception("不正なアクセスです");
                }

                UserData ud = (UserData) session.getAttribute("ud");
                UserDataDTO udd = new UserDataDTO();
                //ユーザーIDをセット
                udd.setUserID(ud.getUserID());
                //各パラメータセット
                ud.setName(request.getParameter("txtName"));
                ud.setPass(request.getParameter("txtPass"));
                ud.setMail(request.getParameter("txtMail"));
                ud.setAddress(request.getParameter("txtAddress"));
                //nullがある場合、前ページへ戻す
                if (ud.nullChk().size() != 0) {
                    request.setAttribute("error", "未記入の項目があります。もう一度確認をお願いします。");
                    request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
                } else {
                    //ユーザーデータ更新
                    ud.DTOMapping(udd);
                    UserDataDAO.getInstance().userUpdate(udd);
                    //更新したユーザーデータをsessionに挿入
                    udd = UserDataDAO.getInstance().login(ud.getName(), ud.getPass());
                    ud.UDMapping(udd);
                    session.setAttribute("ud", ud);
                }
                request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
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
