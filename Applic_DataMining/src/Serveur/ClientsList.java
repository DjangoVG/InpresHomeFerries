package Serveur;

import java.net.Socket;
import java.util.*;

public class ClientsList implements SourcesTaches
{
    public LinkedList<Socket> clients;
        
    public ClientsList()
    {
        this.setClients(new LinkedList<>());
    }
    
    public void setClients(LinkedList<Socket> clients) 
    { 
        this.clients = clients; 
    }
    
    public LinkedList<Socket> getClients() 
    {
        return this.clients;
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
}