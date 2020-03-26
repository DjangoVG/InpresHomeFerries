/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBean.TraverseesListingBean;
import Outils.LibrairieJDBC;
import PoolDeThreads.ThreadClient;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import Traversees.Traversees;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Regis
 */
@WebServlet(name = "ListingTraverseesServlet", urlPatterns = {"/ListingTraverseesServlet"})
public class ListingTraverseesServlet extends HttpServlet implements Serializable {
    private Socket MaSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public ArrayList<Traversees> traversees = new ArrayList<Traversees>();
    private Properties propServeur;
    private LibrairieJDBC LibJDBC;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        System.out.println("Bonjour");
        ArrayList liste_traversee = new ArrayList<Traversees>();
        RequeteCINAP req = null;
        ReponseCINAP rep = null;
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        try 
        {
            MaSocket = new Socket(propServeur.getProperty("adresse"), Integer.valueOf((String) propServeur.getProperty("portEcoute"))); 

            this.oos = new ObjectOutputStream(MaSocket.getOutputStream());
            this.ois = new ObjectInputStream(MaSocket.getInputStream());
            if (request.getParameter("DateDepart") == null)
            {
                System.out.println("Icinon");
                req = new RequeteCINAP(8);
                oos.writeObject(req);
                rep = (ReponseCINAP)this.ois.readObject();
                if (rep.getCode() == ReponseCINAP.LISTETRAVERSEES_OK)
                    liste_traversee = rep.getArray();
                else
                    liste_traversee = rep.getArray();
                TraverseesListingBean TraverseesBean = new TraverseesListingBean();
                TraverseesBean.setListeTraversees(liste_traversee);
                request.setAttribute("liste_traversees", TraverseesBean);
                
                if (request.getParameter("param").equalsIgnoreCase("0"))
                    this.getServletContext().getRequestDispatcher("/NosTraversees.jsp").forward(request, response);
                else
                {
                    request.setAttribute("recherche", 1);
                    this.getServletContext().getRequestDispatcher("/AchatTickets.jsp").forward(request, response);
                }
            }
            else
            {
                System.out.println("Ici");
                request.setAttribute("recherche", 1);
                req = new RequeteCINAP(8, request.getParameter("DateDepart").toString(), request.getParameter("DateArrivee").toString());
                oos.writeObject(req);
                rep = (ReponseCINAP)this.ois.readObject();
                liste_traversee = rep.getArray();
                TraverseesListingBean TraverseesBean = new TraverseesListingBean();
                TraverseesBean.setListeTraversees(liste_traversee);
                request.setAttribute("liste_traversees", TraverseesBean);
                this.getServletContext().getRequestDispatcher("/AchatTickets.jsp").forward(request, response); 
            }
        }   
        catch (IOException | ClassNotFoundException ex) 
        {
            System.out.println("Erreur : " + ex);
        }
        finally{
            MaSocket.close();
            oos.close();
            ois.close();
        }
    }
}
