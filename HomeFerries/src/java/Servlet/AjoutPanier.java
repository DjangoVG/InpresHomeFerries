
package Servlet;

import Traversees.Traversees;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import JavaBean.PanierListingBean;
import PoolDeThreads.ThreadClient;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import RequeteReponseEBOOP.ReponseEBOOP;
import RequeteReponseEBOOP.RequeteEBOOP;
import Utilities.SocketHandler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AjoutPanier", urlPatterns = {"/AjoutPanier"})
public class AjoutPanier extends HttpServlet 
{
    private PanierListingBean panier;
    private Socket MaSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String IdTraversee;
    private Date DateDepart;
    private String PortDepart;
    private String PortArrivee;
    private String Matricule;
    private String Prix;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String IdTraversee = request.getParameter("IdTraversee");
        //ThreadClient.Detection = 0;
        RequeteCINAP req = null;
        ReponseCINAP rep = null;
        
        try 
        {
            MaSocket = SocketHandler.getSocket(); 
            oos = SocketHandler.getOos();
            ois = SocketHandler.getOis();
            
            req = new RequeteCINAP(9, IdTraversee);
            oos.writeObject(req);
            rep = (ReponseCINAP)this.ois.readObject();
            
            if (rep.getCode() == ReponseCINAP.TRAVERSEEOK)
            {
                DateDepart = rep.getDateDepart();
                PortDepart = rep.getPortDepart();
                PortArrivee = rep.getPortArrivee();
                Matricule = rep.getMatricule();
                Prix = rep.getPrix();
            }
            
            HttpSession session = request.getSession();
            
            if (session.getAttribute("panier") == null)
            {
                panier = new PanierListingBean();     
                System.out.println("Nouveau panier créé");           
            }

            
            Traversees traversee = new Traversees();
            traversee.setIdTraversees(IdTraversee);
            traversee.setDateDepart(DateDepart);
            traversee.setPortDepart(PortDepart);
            traversee.setPortArrivee(PortArrivee);
            traversee.setMatricule(Integer.parseInt(Matricule));
            traversee.setPrix(Integer.parseInt(Prix));
            panier.addTraversee(traversee);
            session.setAttribute("panier", panier);
            System.out.println("Traversée ajouté dans panier");
            
            RequeteEBOOP reqPlace = new RequeteEBOOP(1, IdTraversee); System.out.println("1");
            oos.writeObject(reqPlace);System.out.println("2");
            ReponseEBOOP repPlace = (ReponseEBOOP)this.ois.readObject();System.out.println("3");
            if(repPlace.getCode() == ReponseEBOOP.VERIFFILE_OK)
            {
                System.out.println("VerifFileOK");
            }
            else
            {
                System.out.println("VerifFileKO");
            }
            
            response.sendRedirect("MonPanier.jsp#NosTraversees");
            
        }   
        catch (IOException | ClassNotFoundException ex) 
        {
            System.out.println("Erreur : " + ex);
        }
    }
}

