package RequeteReponseMultiThread;

import javax.swing.table.DefaultTableModel;

public interface Reponse {
    public int getCode();
    public Object getParams(int i);
    public DefaultTableModel getModele2();
}
