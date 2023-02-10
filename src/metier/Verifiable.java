package metier;

import Dao.Files.ClientDao;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;

public interface Verifiable {

    default boolean isNumeric(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    default boolean isDecimal(String value){
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    default boolean isAdmin(String login,String mdp){
        Admin admin = Admin.getInstance();
        return login.equals(admin.getLogin()) && mdp.equals(admin.getMotDePasse());
    }

    default Client isClient(String login, String mdp){
        ClientDao clientDao = new ClientDao();

        return clientDao.findAll()
                .stream()
                .filter(client -> {return client.getLogin().equals(login) && client.getMotDePasse().equals(mdp);})
                .findFirst()
                .orElse(null);
    }
}
