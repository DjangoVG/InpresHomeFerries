package JavaBean;

import java.util.ArrayList;
 
import Traversees.Traversees;
 
public class TraverseesListingBean {
 
	private Traversees traversee = new Traversees();
	private ArrayList <Traversees> liste = new ArrayList<Traversees>();
 
	public Traversees getTraversee() {
		return traversee;
	}
	public void setTraversee(Traversees traversee) {
		this.traversee = traversee;
	}
	public ArrayList<Traversees> getListeTraversees() {
		return liste;
	}
	public void setListeTraversees(ArrayList<Traversees> liste) {
		this.liste = liste;
	}
}
