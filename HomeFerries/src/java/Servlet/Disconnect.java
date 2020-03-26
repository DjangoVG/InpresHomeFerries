package Servlet;

import PoolDeThreads.ThreadClient;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utilities.SocketHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Disconnect", urlPatterns = {"/Disconnect"})
public class Disconnect extends HttpServlet 
{
    private Properties propServeur;
    private Socket MaSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Disconnect</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Disconnect at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try {
            RequeteCINAP req = null;
            ReponseCINAP rep = null;

            MaSocket = SocketHandler.getSocket();
            oos = SocketHandler.getOos();
            ois = SocketHandler.getOis();

            req = new RequeteCINAP(3);
            this.oos.writeObject(req);
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("home.jsp");
        }
        catch (IOException e)
        {
           Logger.getLogger(Disconnect.class.getName()).log(Level.SEVERE, null, e); 
        }
    }
}
