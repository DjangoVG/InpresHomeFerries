/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBean.PanierListingBean;
import Outils.LibrairieJDBC;
import PoolDeThreads.ThreadClient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import RequeteReponseEBOOP.*;
import Traversees.Traversees;
import Utilities.SocketHandler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Payement", urlPatterns = {"/Payement"})
public class Payment extends HttpServlet {

    private PanierListingBean panier;
    private Socket MaSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String Res;
    private String numcarte,nomClient,prenomClient;
    private int prixpanier;
    private String idTraversee;
    private String NumClient;
    private int numcli ;
    private String emailcient;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1 avant chgmt detection");
        ArrayList <Traversees> liste_panier = new ArrayList<Traversees>();
        
        ReponseEBOOP rep = null;
        numcarte = request.getParameter("NumCarte");
        nomClient = request.getParameter("surname");
        prenomClient = request.getParameter("firstname");
        emailcient = request.getParameter("emailclient");
        prixpanier = 0;
        HttpSession session = request.getSession();
        NumClient = (String) session.getAttribute("NumClient");
        numcli = Integer.parseInt(NumClient);
        PanierListingBean panier = (PanierListingBean)session.getAttribute("panier");
        Iterator <Traversees> list = panier.getPanier().iterator();
        
        try 
        {
            System.out.println("1");

            MaSocket = SocketHandler.getSocket(); 
            oos = SocketHandler.getOos();
            ois = SocketHandler.getOis();
                    System.out.println("2");
            while(list.hasNext()){
                prixpanier = 0;
                idTraversee = null;
                Traversees traversee = list.next();
                prixpanier = traversee.getPrix();
                System.out.println("PrixPanier : " + prixpanier);
                idTraversee = traversee.getIdTraversees();
                RequeteEBOOP req = new RequeteEBOOP(0,numcarte,nomClient, prenomClient, prixpanier,idTraversee,numcli, emailcient);
                oos.writeObject(req);
                System.out.println("Envoie RequeteEBOOP Servlet Payment");
                rep = (ReponseEBOOP)this.ois.readObject();
                        System.out.println("3");
                System.out.println("lecture ReponseEBOOP Servlet Payment");
                if (rep.getCode() == ReponseEBOOP.PAYEMENTOK)
                {   
                    
                    System.out.println("Reservation final ok");
                    request.setAttribute("AchatConfirme", "0");
                    request.setAttribute("NumRes", rep.getResultat());
                    Res = rep.getResultat();
                    panier.removeAllTraversee(liste_panier); //Enlève toutes les traversées du panier une fois le payement effectué
                    
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                }
                if (rep.getCode() == ReponseEBOOP.PAYEMENTKO_CARTENOTFOUND)
                {
                    System.out.println("Carte not found");
                    request.setAttribute("AchatConfirme", "1");
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                }
                if (rep.getCode() == ReponseEBOOP.PAYEMENTKO_SOLDETOOLOW)
                {
                    System.out.println("Solde insuffisant");
                    request.setAttribute("AchatConfirme", "2");
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                }
                if (rep.getCode() == ReponseEBOOP.PAYEMENTKO_DEBITTOOMUCH)
                {
                    System.out.println("Débit trop élevé");
                    request.setAttribute("AchatConfirme", "3");
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                }
                if (rep.getCode() == ReponseEBOOP.PAYEMENTKO_CLIENTNOTFOUND)
                {
                    System.out.println("Client not found");
                    request.setAttribute("AchatConfirme", "4");
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                }
                
            }

        } 
        catch (Exception e) 
        {
        }
    }

}
