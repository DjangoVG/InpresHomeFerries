package JavaBean;

import java.util.ArrayList;
 
import Traversees.Traversees;
 
public class PanierListingBean 
{
    private ArrayList <Traversees> liste_panier = new ArrayList<Traversees>();

    public void addTraversee(Traversees traversee) {
            liste_panier.add(traversee);
    }
    public ArrayList<Traversees> getPanier() {
            return liste_panier;
    }
    public void setPanier(ArrayList<Traversees> liste) {
            this.liste_panier = liste;
    }

    public void removeTraversee(Traversees traversee) {
            liste_panier.remove(traversee);
    }
    
    public void removeAllTraversee(ArrayList <Traversees> liste)
    {
        System.out.println("Passage dans RemoveListingBean");
        liste_panier = liste;
    }
    
    public void findTraversee (String IdTraversee)
    {
        Traversees traversee;
        for (int i=0; i< liste_panier.size(); i++)
        {
            traversee = liste_panier.get(i);
            if (traversee.getIdTraversees().equals(IdTraversee))
            {
                liste_panier.remove(i);   
                i = liste_panier.size();
            }
        }
    }
}
