package metier.form;

import metier.Verifiable;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Utilisateur;

import java.util.HashMap;
import java.util.Map;

public class LoginFormValidator implements Verifiable {
    private static final String fld_login="login",fld_pass="password";
    private Map<String,String> errors ;
    private String connectionMsg;
    private Banque banque ;

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErros(String fieldName,String errorMsg) {
        this.errors.put(fieldName,errorMsg);
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getConnectionMsg() {
        return connectionMsg;
    }

    public void setConnectionMsg(String connectionMsg) {
        this.connectionMsg = connectionMsg;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public LoginFormValidator()
    {
        errors = new HashMap<>();
    }

    private void verifierLogin(String login) throws FormExecption {
        if(login!=null && login.trim().length()!=0){
            if(login.trim().length()<4){
                throw new FormExecption("Login must have more than 4 caracters !");
            }
        }
        else{
            throw new FormExecption("Login est un champ obligatoire !");
        }
    }
    private void verifierPass(String pass) throws FormExecption {
        if(pass!=null && pass.trim().length()!=0){
            if(pass.trim().length()<4){
                throw new FormExecption("Password must have more than 4 caracters !");
            }
        }
        else{
            throw new FormExecption("Password est un champ obligatoire !");
        }
    }

    public boolean validerLogin(String login){
        try {
            verifierLogin(login);
            return true ;
        } catch (FormExecption e) {
            setErros(fld_login,e.getMessage());
            return false;
        }
    }

    public boolean validerPass(String pass){
        try {
            verifierPass(pass);
            return true ;
        } catch (FormExecption e) {
            setErros(fld_pass,e.getMessage());
            return false ;
        }
    }

    public Utilisateur validerSession(String login,String pass){
        Utilisateur session = null ;
        validerLogin(login);
        validerPass(pass);
        if(getErrors().isEmpty())
        {

            if(isAdmin(login,pass)){
                session = Admin.getInstance();
                setConnectionMsg("Admin successfully logged ");
            }
            else if (isClient(login,pass) != null) {
                session = isClient(login, pass);
                setConnectionMsg("client successfully logged");
            }
            else
                setConnectionMsg("unsucessfull login try again ");
        }


        return session;
    }

}
