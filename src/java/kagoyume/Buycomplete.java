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
public class Buycomplete extends HttpServlet {

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
                if (accesschk == null || (Integer) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
                    throw new Exception("不正なアクセスです");
                }
                //購入処理
                UserData ud = (UserData)session.getAttribute("ud");
                UserDataDTO udd = new UserDataDTO();
                UserDataDAO dao = new UserDataDAO();
                ud.DTOMapping(udd);
                CartBox cb = (CartBox)session.getAttribute("cart");
                //データベースカートデータ削除
                    for (int i =0; i<cb.getCartBox().size();i++){
                    UserDataDAO.getInstance().cartDataDelete(udd, cb.getCartBoxSdb(i));
                    }
                
                for (int i = 0;i <cb.getCartBox().size();i++){
                    udd.setItemcode(cb.getCartBoxSdb(i).getCode());
                    udd.setType(Integer.parseInt(request.getParameter("Shipping"+i)));
                    udd.setBuyTotal(cb.getCartBoxSdb(i).getPrice());
                    dao.insertBuy(udd);
                }
                //session「cart」の削除
                session.removeAttribute("cart");
                //ユーザーデータ更新
                udd = dao.login(ud.getName(), ud.getPass());
                ud.UDMapping(udd);
                session.setAttribute("ud", ud);
                
                request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
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
