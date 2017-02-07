/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Item extends HttpServlet {

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
            //キーワードを受け取れない場合topへ送る
            if(request.getParameter("sdbID") == null && request.getParameter("cartID") == null){
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            
            //sessionからsdbbox,またはcartBoxからリクエストで特定のsdbを入手
            if (request.getParameter("sdbID") != null) {
                SearchDateBox sdbBox = (SearchDateBox) session.getAttribute("sdbBox");
                SearchDateBeans sdb = sdbBox.getSdb(Integer.parseInt(request.getParameter("sdbID")));
                session.setAttribute("sdb", sdb);
            } else if (request.getParameter("cartID") != null){
                CartBox cb = (CartBox)session.getAttribute("cart");
                SearchDateBeans sdb = cb.getCartBoxSdb(Integer.parseInt(request.getParameter("cartID")));
                session.setAttribute("sdb", sdb);
            }
            
            session.setAttribute("page", "Item");
            RequestDispatcher rd = request.getRequestDispatcher("/item.jsp");
            rd.forward(request, response);
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
