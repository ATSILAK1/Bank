package presentation.modele;

public class Admin  extends Utilisateur{

    private static  Admin ADMIN = new Admin();

    private Admin(){

        login       = "Atsilak";
        motDePasse  = "Atsilak";
        role        = "Admin";
        nom         =  "Admin";
        prenom      = "Lmadani";

    }


    public static Admin getInstance(){

        return ADMIN;
    }

}
