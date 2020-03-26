package Authentification;

abstract class VerificateurMotDePasse extends ValidationLogin implements VerifLogin 
{
    protected String Username;
    protected String password;
    
    // Constructeurs
    public VerificateurMotDePasse (){
        Username = "inconnue";
        password = "inconnue";
    }
    public VerificateurMotDePasse (String log, String pwd){
        Username = log;
        password = pwd;
    }
    
    // Get et Set.
    public String getLogin(){
        return Username;
    }
    public void setLogin (String log){
        this.Username = log;
    }
    
    public String getPassword (){
        return password;
    }
    public void setPassword (String pwd){
        this.password = pwd;
    }
    
    abstract boolean findPwd (String log, String PasswordParam);
    
    @Override
    public boolean isOk(String log, String PasswordParam) {
        return findPwd(log, PasswordParam);
    }    
}