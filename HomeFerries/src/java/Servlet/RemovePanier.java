/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBean.PanierListingBean;
import Traversees.Traversees;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Regis
 */
@WebServlet(name = "RemovePanier", urlPatterns = {"/RemovePanier"})
public class RemovePanier extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PanierListingBean panier = (PanierListingBean)session.getAttribute("panier");
        String traversee = request.getParameter("IdTraversee");
        session.setAttribute("IdTraversee", null);
        panier.findTraversee(traversee);
        session.setAttribute("panier", panier);
        System.out.println("Traversée supprimé du panier");
        response.sendRedirect("MonPanier.jsp");
    }

}
