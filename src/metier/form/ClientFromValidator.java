package metier.form;
import java.util.HashMap;
import java.util.Map;

public class ClientFromValidator {

private static final String fld_login ="Login",
                            fld_Prenom = "Prenom",
                            fld_nom = "Nom",
                            fld_motDePasse = "Mot de passe",
                            fld_email = "Email",
                            fld_tel = "Tel",
                            fld_Sexe = "Sexe",
                            fld_cin = "Cin";
private Map<String , String> errors ;

public ClientFromValidator()
{
    errors = new HashMap<>();
}

    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(String fieldName , String errorMsg)
    {
        getErrors().put(fieldName,errorMsg);
    }



private void verifierLogin(String login) throws FormExecption
{
    if (login != null && login.trim().length() != 0) {
        if (login.trim().length() < 4)
            throw new FormExecption("Login field need at least 4 chars");
    }else
        throw new FormExecption("Login is required ");


}
public Boolean validerLogin(String login)
{
    try {
        verifierLogin(login);
        return true ;
    }catch (FormExecption e){
        setErrors(fld_login,e.getMessage());
        return false ;
    }
}
private void verifierPass(String motdepasse) throws FormExecption
    {
        if (motdepasse != null && motdepasse.trim().length() != 0) {
            if (motdepasse.trim().length() < 4)
                throw new FormExecption("Login field need at least 4 chars");
        }else
            throw new FormExecption("Login is required ");


    }
public Boolean validerPass(String pass)
{
    try
    {
        verifierPass(pass);
        return true ;
    }catch (FormExecption e)
    {
        setErrors(fld_motDePasse,e.getMessage());
        return false ;
    }
}

private void verifierPrenom (String prenom) throws FormExecption
    {
        if (prenom == null && prenom.trim().length() == 0)

            throw new FormExecption("prenom is required field");

    }
public Boolean validerPrenom(String Prenom)
{
    try
    {
        verifierPrenom(Prenom);
        return true ;
    }catch (FormExecption e)
    {
        setErrors(fld_Prenom,e.getMessage());
        return false;
    }
}

private  void verifierTel(String tel ) throws FormExecption
    {
        if (tel!= null && tel.trim().length() != 0) {
            if (!tel.matches("^(06|07)[0-9]{8}")) {
                throw new FormExecption("Format is not valid");
            }
        }
        else throw new FormExecption("Phone is required");
    }
public boolean validerTel(String tel)
{
    try{
        verifierTel(tel);
        return true ;
    }catch (FormExecption e)
    {
        setErrors(fld_tel,e.getMessage());
        return false ;
    }
}


private  void verifierEmail(String email ) throws FormExecption
    {
        if ( email!= null && email.trim().length() != 0) {
            if (!email.matches("(\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b)")) {
                throw new FormExecption("Format is not valid");
            }
        }
        else throw new FormExecption("Phone is required");
    }
public boolean validerEmail(String email)
{
    try{
        verifierEmail(email);
        return true ;
    }catch(FormExecption e)
    {
        setErrors(fld_email,e.getMessage());
        return false;
    }
}
private void verifierCin(String cin) throws FormExecption
{
        if(cin == null || cin.trim().length() == 0)
            throw new FormExecption("Field is empty");

        if (!cin.matches("^([A-Z]){1,3}[0-9]{6}")) {
            throw new FormExecption("Invalid Format");

        }


}
public boolean validerCin(String cin)
{
    try
    {
        verifierCin(cin);
        return true ;
    }catch (FormExecption e)
    {
        setErrors(fld_cin,e.getMessage());
        return false ;
    }
}

public boolean validerClient(String cin , String email , String pass , String tel , String nom , String prenom , String login){
    return validerCin(cin) &&
            validerEmail(email) &&
            validerLogin(login) &&
            validerPass(pass) &&
            validerPrenom(prenom) &&
            validerPrenom(nom) &&
            validerTel(tel);
}
}

