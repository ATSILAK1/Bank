package presentation.modele;

import java.util.*;

public class Banque {

    private static long          compteur = 1;
    private Long                 idBanque;
    private String              nomBanque;
    private String              adresseBanque;
    private String              telBanque;
    private String              emailBanque;
    private List<Client>        clientsDeBanque = new ArrayList<>();

    public Banque()
                    {
                        idBanque = compteur++;
                    }
    public Banque(String nom, String adresse, String tel, String mail)
                    {
                        idBanque        = compteur++;
                        nomBanque       = nom;
                        telBanque       = tel;
                        adresseBanque   = adresse;
                        emailBanque     = mail;
                    }

    public Long             getIdBanque() {
        return idBanque;
    }
    public String           getNomBanque() {
        return nomBanque;
    }
    public String           getEmailBanque() {
        return emailBanque;
    }
    public String           getTelBanque() {
        return telBanque;
    }
    public String           getAdresseBanque() {
        return adresseBanque;
    }
    public List<Client>     getClientsDeBanque() {
        return clientsDeBanque;
    }

    public void             setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }
    public void             setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
    public void             setEmailBanque(String emailBanque) {
        this.emailBanque = emailBanque;
    }
    public void             setAdresseBanque(String adresseBanque) {
        this.adresseBanque = adresseBanque;
    }
    public void             setTelBanque(String telBanque) {
        this.telBanque = telBanque;
    }
    public void             setClientsDeBanque(List<Client> clientsDeBanque) {
        this.clientsDeBanque = clientsDeBanque;
    }
    public void             setClientDeBanque(Client c){this.clientsDeBanque.add(c);}
    public List<Compte>toutLesComptesDeLaBanque()
    {
        List<Compte> m = new ArrayList<>();
        for(Client cli : clientsDeBanque)
        {
            for(Compte compte : cli.getComptesClient() )
            {
                m.add(compte);
            }
        }
        return m;
    }

    public Compte getCompteParId(String id)
    {
        for (Compte compte : toutLesComptesDeLaBanque())
        {
            if(compte.getNumeroCompte() == id )
                return compte;
        }
        return null ;
    }

}
