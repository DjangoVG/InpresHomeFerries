package serveur_compagnie;

import java.net.Socket;
import java.util.*;
import javax.net.ssl.SSLSocket;

public class ClientsList implements SourcesTaches
{
    public LinkedList<Socket> clients;
    public LinkedList<SSLSocket> clientsSSL;
    
    public ClientsList()
    {
        this.setClients(new LinkedList<>());
        this.setClientsSSL(new LinkedList<>());
    }
    
    public void setClients(LinkedList<Socket> clients) 
    { 
        this.clients = clients; 
    }
    
    public void setClientsSSL(LinkedList<SSLSocket> clients) 
    { 
        this.clientsSSL = clients; 
    }
    
    public LinkedList<Socket> getClients() 
    {
        return this.clients;
    }
    
    public LinkedList<SSLSocket> getClientsSSL() 
    {
        return this.clientsSSL;
    }

    @Override
    public synchronized Socket getClient() throws InterruptedException
    {
        while (!this.existClient())
            this.wait();
        return this.getClients().remove();
    }
    
    @Override
    public synchronized void recordClient(Socket client)
    {
        this.getClients().addLast(client);
        this.notify();
    }
    
    @Override
    public synchronized boolean existClient()
    {
        return !this.getClients().isEmpty();
    }
    
    @Override
    public synchronized Socket getClientSSL() throws InterruptedException
    {
        while (!this.existClientSSL())
            this.wait();
        return this.getClientsSSL().remove();
    }
    
    @Override
    public synchronized void recordClientSSL(SSLSocket client)
    {
        this.getClientsSSL().addLast(client);
        this.notify();
    }
    
    @Override
    public synchronized boolean existClientSSL()
    {
        return !this.getClientsSSL().isEmpty();
    }
    
}