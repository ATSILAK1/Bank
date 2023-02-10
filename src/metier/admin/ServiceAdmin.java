package metier.admin;

import presentation.modele.*;


import java.time.LocalDateTime;
import java.util.*;

public class ServiceAdmin implements IServiceAdmin {

    Banque banque;

    public ServiceAdmin() {
        this.banque = new Banque();
    }

    public ServiceAdmin(Banque banque) {
        this.banque = banque;
    }

    public Client nouveauClient(Scanner clavier) {
        String text = "";

        Client c = new Client();
        System.out.println("entre le nom => ");
        c.setNom(clavier.next());
        System.out.println("entre le prenom =>");
        c.setPrenom(clavier.next());
        System.out.println("entre le CIN du Client =>");
        c.setCin(clavier.next());
        System.out.println("entre l'email du client =>");
        c.setEmail(clavier.next());
        System.out.println("entre le telephone du client");
        c.setTel(clavier.next());
        while (true) {
            System.out.println("entre H pour Homme et F pour ");
            text = clavier.next();
            if (text.equalsIgnoreCase("h")) {
                c.setSexe(Sexe.HOMME);
                break;
            }
            if (text.equalsIgnoreCase("f")) {
                c.setSexe(Sexe.FEMME);
                break;
            }
        }
        banque.getClientsDeBanque().add(c);

        return c;
    }

    @Override
    public Compte nouveauCompteClientExistant(Scanner sc) {

        Compte c = new Compte();
        System.out.println("entre le solde");
        c.setSolde(sc.nextDouble());
        System.out.println("entre l'id du proprietaire");
        Client client = chercherClientParId(sc.nextLong());
        if (client != null)
            c.setPropriétaire(client);
        else
            System.out.println("client intouvable");

        return c;
    }

    @Override
    public Client chercherClientParId(Long id) {
        for (Client c : banque.getClientsDeBanque()) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    @Override
    public List<Client> chercherClientParNom(String nom) {
        List<Client> m = new ArrayList<>();

        for (Client c : banque.getClientsDeBanque())
            if (c.getNom() == nom)
                m.add(c);

        return m;
    }

    @Override
    public List<Client> chercherClientParPrénom(String prenom) {
        List<Client> m = new ArrayList<>();

        for (Client c : banque.getClientsDeBanque())
            if (c.getNom() == prenom)
                m.add(c);

        return m;
    }

    @Override
    public Client chercherClientParCin(String cin) {
        for (Client c : banque.getClientsDeBanque())
            if (c.getCin() == cin)
                return c;

        return null;
    }

    @Override
    public Client chercherClientParEmail(String email) {
        for (Client c : banque.getClientsDeBanque())
            if (c.getEmail() == email)
                return c;

        return null;
    }


    @Override
    public Compte chercherCompteParId(Long idCompte) {
        for (Client c : banque.getClientsDeBanque())
            for (Compte co : c.getComptesClient())
                if (co.getNumeroCompte() == idCompte.toString())
                    return co;

        return null;
    }

    @Override
    public List<Compte> chercherCompteParSolde(double solde) {

        List<Compte> m = new ArrayList<>();
        for (Client c : banque.getClientsDeBanque())
            for (Compte co : c.getComptesClient())
                if (co.getSolde() == solde)
                    m.add(co);

        return m;

    }

    @Override
    public List<Compte> chercherCompteParDateCreation(LocalDateTime date) {
        List<Compte> m = new ArrayList<>();
        for (Client c : banque.getClientsDeBanque())
            for (Compte co : c.getComptesClient())
                if (co.getDateCreation() == date)
                    m.add(co);

        return m;


    }

    @Override
    public List<Compte> chercherCompteParPropriétaire(Client propriétaire) {
        List<Compte> m = new ArrayList<>();
        for (Client c : banque.getClientsDeBanque())
            for (Compte co : c.getComptesClient())
                if (co.getProprietaire() == propriétaire)
                    m.add(co);

        return m;
    }

    @Override
    public Client modifierClient(String filtre,  Scanner sc) {
        Client client ;
        while (true) {
            System.out.println("entre l'id du client ");
            long id = sc.nextLong();
             client = chercherClientParId(id);
            if (client == null) {
                System.out.println("id incorecte");
            }
            else break;
        }
        switch (filtre) {
            case "Nom" -> {
                System.out.println("entre nouveau nom");
                client.setNom(sc.next());
            }
            case "Prenom" -> {
                System.out.println("entre le nouveau prenom");
                client.setPrenom(sc.next());
            }
            case "Email" -> {
                System.out.println("entre le nouvel email");
                client.setEmail(sc.next());
            }
            case "tel" -> {
                System.out.println("entre le nouveau telephone");
                client.setTel(sc.next());
            }
            case "Sexe" -> {
                String m;
                while (true) {
                    System.out.println("entre le h pour homme entre f pour femme");
                    m = sc.next();
                    if (m.equalsIgnoreCase("h")) {
                        client.setSexe(Sexe.HOMME);
                        break;
                    }
                    if (m.equalsIgnoreCase("f")) {
                        client.setSexe(Sexe.FEMME);
                        break;
                    }
                }
            }
            case "Login" -> {
                System.out.println("entre le nouveau login");
                client.setLogin(sc.next());
            }
            case "Mot de passe" -> {
                System.out.println("entre le nouveau mot de passe ");
                client.setMotDePasse(sc.next());
            }
        }

        return client;
    }

    @Override
    public boolean supprimerClient(Long id) {

        for (Client c : banque.getClientsDeBanque())
            if (Objects.equals(c.getId(), id)) {
                banque.getClientsDeBanque().remove(c);
                return true;
            }
        return false;
    }

    @Override
    public TableauDeBord calculerEtAfficherStatistiques() {

        TableauDeBord tableauDeBord = new TableauDeBord();
        tableauDeBord.setNombreTotaleClient((long) banque.getClientsDeBanque().size());
        tableauDeBord.setNombreTotaleCompte((long)banque.toutLesComptesDeLaBanque().size());
        tableauDeBord.setMaxSolde(avoirMaxSolde());
        tableauDeBord.setMinSolde(avoirMinSolde());
        tableauDeBord.setNomClientLePlusRiche(avoirClientPlusRiche());
        tableauDeBord.setTotalClientsFemme((long)nombreFemme());
        tableauDeBord.setTotalClientsFemme((long)nombreHomme());

        return tableauDeBord;
    }

    @Override
    public List<Client> trierClientParNom() {
        banque.getClientsDeBanque().sort(Comparator.comparing(Client::getNom));
        return banque.getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParCin() {
        banque.getClientsDeBanque().sort(Comparator.comparing(Client::getCin));
        return banque.getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParEmail() {
        Collections.sort(banque.getClientsDeBanque(), Comparator.comparing(Client::getEmail));
        return banque.getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParAdresse() {
        return null;

    }

    @Override
    public List<Client> trierClientParSoldeCompte() {
        Collections.sort(banque.getClientsDeBanque(), Comparator.comparing(Client::getSolde));
        return banque.getClientsDeBanque();
    }

    @Override
    public List<Compte> trierComptesParSolde() {
        List<Compte> p = new ArrayList<>();
        for (Client c : banque.getClientsDeBanque()) {
            for (Compte cp : c.getComptesClient()) {
                p.add(cp);
            }

        }
        Collections.sort(p, Comparator.comparing(Compte::getSolde));
        return p;


    }

    @Override
    public List<Compte> trierComptesParDateDeCreation() {

        List<Compte> m = new ArrayList<>();
        m = banque.toutLesComptesDeLaBanque();
        Collections.sort(m, Comparator.comparing(Compte::getDateCreation));
        return m;
    }

    @Override
    public List<Compte> trierComptesParNomPropriétaire() {

        List<Compte> m = new ArrayList<>();
        m = banque.toutLesComptesDeLaBanque();
        Collections.sort(m, Comparator.comparing(Compte::getPropName));
        return m;
    }

    public double avoirMaxSolde()
    {
        double max = 0  ;
        for(Compte c : banque.toutLesComptesDeLaBanque())
        {
            if (c.getSolde() > max)
                max = c.getSolde();
        }
        return max ;
    }
    public double avoirMinSolde()
    {
        double min = banque.toutLesComptesDeLaBanque().get(0).getSolde()  ;
        for(Compte c : banque.toutLesComptesDeLaBanque())
        {
            if (c.getSolde() < min)
                min = c.getSolde();
        }
        return min ;
    }
    public String avoirClientPlusRiche()
    {
        double max = 0 ;
        Client c = null ;
        for(Client client : banque.getClientsDeBanque())
        {
            if(client.getSolde() > max)
            {
                max = client.getSolde();
                c = client;
            }
        }
        return c.getNom();
    }

    int nombreFemme()
    {
        int m = 0 ;
        for(Client client : banque.getClientsDeBanque())
        {
            if( client.getSexe() == Sexe.FEMME )
            {
                m++;
            }
        }
        return m;
    }
    int nombreHomme()
    {
        int m = 0 ;
        for(Client client : banque.getClientsDeBanque())
        {
            if( client.getSexe() == Sexe.HOMME )
            {
                m++;
            }
        }
        return m;
    }
}
