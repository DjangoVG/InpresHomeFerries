/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

public class UserBean
{
    private String UsernameUser = null;
    private String PasswordUser = null;
    private int NumReponse;

    public void setPasswordUser(String PasswordUser) {
        this.PasswordUser = PasswordUser;
    }
    
    public void setUsernameUser(String UsernameUser) {
        this.UsernameUser = UsernameUser;
    }

    public String getUsernameUser() {
        return UsernameUser;
    }

    public String getPasswordUser() {
        return PasswordUser;
    }

    public void setNumReponse(int NumReponse) {
        this.NumReponse = NumReponse;
    }

    public int getNumReponse() {
        return NumReponse;
    }
    
    
}
