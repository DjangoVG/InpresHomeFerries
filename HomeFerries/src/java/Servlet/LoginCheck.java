/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import JavaBean.UserBean;
import Login.ConnexionForm;

/**
 *
 * @author Regis
 */
@WebServlet(name = "LoginCheck", urlPatterns = {"/LoginCheck"})
public class LoginCheck extends HttpServlet 
{
    public static final String VUE_LOGINHOME    = "/LoginHome.jsp";
    public static final String VUE_EXISTCLIENT  = "/ExistClient.jsp";
    public static final String VUE_NEWCLIENT    = "/NewClient.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher("/LoginHome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        ConnexionForm form = new ConnexionForm();
        UserBean user = form.connecterUtilisateur(request);
        if ( form.getErreurs().isEmpty() ) 
        {
            System.out.println("Aucune erreur");
            HttpSession session = request.getSession();
            session.setAttribute("NumClient", user.getUsernameUser());         
            
            switch (user.getNumReponse())
            {
                case 1:
                    request.setAttribute("NewClient", "0");
                    request.setAttribute("NumClient", user.getUsernameUser());
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                    break;
                case 2:
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                    break;
            }   
        } 
        else 
        {
            System.out.println("Des erreurs");
            this.getServletContext().getRequestDispatcher("/LoginHome.jsp").forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
