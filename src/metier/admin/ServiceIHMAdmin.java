package metier.admin;

import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.TableauDeBord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ServiceIHMAdmin implements IServiceIHMAdmin{
    ServiceAdmin serviceAdmin ;


    @Override
    public int menuModification(Scanner clavier) {
        System.out.println("==**== menu modification ==**==");
        System.out.println("entre Nom pour modifier le nom ");
        System.out.println("entre Prenom pour modfier le prenom ");
        System.out.println("entre Tel pour modifier le numero le telephone ");
        System.out.println("entre Sexe pour modifier le sexe");
        System.out.println("entre Login pour modifier le login");
        System.out.println("entre Mot de passe pour modifier le mot de passe");


        String choix = clavier.next();

        serviceAdmin.modifierClient(choix,clavier);

        return 0;
    }

    @Override
    public int menuRecherche(Scanner clavier) {

        int choix = 0;
        System.out.println("***=== Menu de recherche ===***");
        System.out.println("entre 1 pour chercher un client par id ");
        System.out.println("entre 2 pour chercher un Client par nom");
        System.out.println("entre 3 pour chercher un client par prenom");
        System.out.println("entre 4 pour chercher un client par cin");
        System.out.println("entre 5 pour chercher un client par email");
        System.out.println("entre 6 pour chercher un compte par id ");
        System.out.println("entre 7 pour chercher un compte par solde ");
        System.out.println("entre 8 pour chercher un compte par date de creation");
        System.out.println("entre 9 pour chercher un compte par proprietaire");
        switch (choix)
        {
            case 1:{
                System.out.println("entre l id du client rechercher ");
                Client client = serviceAdmin.chercherClientParId(clavier.nextLong());
                if(client == null)
                    System.out.println("client introuvable ");
                else System.out.println(client);
            }
                break;
            case 2 : {
                System.out.println("entre l nom des client rechercher ");
                List<Client> clients = serviceAdmin.chercherClientParNom(clavier.next());
                if (clients == null)
                    System.out.println("client introuvable ");
                else System.out.println(clients);
            }break;
            case 3 : {
                System.out.println("entre le prenom du client rechercher");
                List<Client> clients = serviceAdmin.chercherClientParPrénom(clavier.next());
                if (clients == null)
                    System.out.println("client introuvable ");
                else System.out.println(clients);
            }
            break;
            case 4 : {
                System.out.println("entre le CIN du client rechercher ");
                Client client = serviceAdmin.chercherClientParCin(clavier.next());
                if(client == null)
                    System.out.println("client introuvable ");
                else System.out.println(client);
            }
            case 5 : {
                System.out.println("entre le l'email du client rechercher");
                Client client = serviceAdmin.chercherClientParEmail(clavier.next());
                if(client == null) System.out.println("client introuvable ");
                else  System.out.println(client);
            }
            case 6 :
            {
                System.out.println("entre l'id cu compte rechercher ");
                Compte compte = serviceAdmin.chercherCompteParId(clavier.nextLong());
                if (compte == null) System.out.println("Compte introuvable ");
                else System.out.println(compte);


            }
            case 7 :
            {
                System.out.println("entre le solde pour chercher les compte");
                List<Compte> comptes = serviceAdmin.chercherCompteParSolde(clavier.nextDouble());
                if(comptes == null)
                    System.out.println("compte introuvable");
                else System.out.println(comptes);
            }
            break;
            case 8 : {
                System.out.println("entre la date de creation du compte selon se format dd/mm/yyyy");

                List<Compte> comptes = serviceAdmin.chercherCompteParDateCreation(LocalDateTime.of(clavier.nextInt(), clavier.nextInt(), clavier.nextInt(), 0, 0));
                if(comptes == null) System.out.println("aucun compte");
                else System.out.println(comptes);
            }
                break;
            case 9 :
                System.out.println("entre l' id du proprietaire ");
                Client client = serviceAdmin.chercherClientParId(clavier.nextLong());

                List<Compte> comptes = serviceAdmin.chercherCompteParPropriétaire(client);
                if (comptes == null) System.out.println("aucun compte");
                else System.out.println(comptes);
        }
        return 0;

    }

    @Override
    public int menuInformations(Scanner clavier) {
        return 0;
    }

    @Override
    public int menuAjout(Scanner clavier) {
        int choix ;
        System.out.println("<===== menu ajout =====>");
        System.out.println("enter 1 pour ajouter un nouveau client");
        System.out.println("entre 2 pour ajouter un nouveau compte ");
        choix = clavier.nextInt();
        switch (choix)
        {
            case 1 : serviceAdmin.nouveauClient(clavier);
                break;
            case 2: serviceAdmin.nouveauCompteClientExistant(clavier);
            break;
        }
        return 0;
    }

    @Override
    public int menuSuppression(Scanner clavier) {
        int choix ;

        System.out.println("==== menu suppression ====");
        System.out.println("entre 1 pour supprimer un client ");
        choix = clavier.nextInt();
        switch (choix)
        {
            case 1 :
                System.out.println("entre l' id du client a supprimer ");
                    serviceAdmin.supprimerClient(clavier.nextLong());
                break;
        }
        return 0;
    }

    @Override
    public int tableauDeBord() {
        TableauDeBord tableauDeBord =  serviceAdmin.calculerEtAfficherStatistiques();
        System.out.println("========== tableau de bord =============");
        System.out.println("Total client"+tableauDeBord.getNombreTotaleClient());
        System.out.println("solde max"+tableauDeBord.getMaxSolde());
        System.out.println("solde min"+tableauDeBord.getMinSolde());
        System.out.println("client le plus riche "+tableauDeBord.getNomClientLePlusRiche());
        System.out.println("nbr client homme "+tableauDeBord.getTotaleClientsHomme());
        System.out.println("nbr client femme "+tableauDeBord.getTotalClientsFemme());
        return 0;
    }

    @Override
    public int menuTrie(Scanner clavier) {
        System.out.println("==== Menu de trie ====");
        System.out.println("entre 1 pour trier les clients par nom");
        System.out.println("entre 2 pour trier les clients par CIn");
        System.out.println("entre 3 pour trier les clients par email");
        System.out.println("entre 4 pour trier les client par solde de compte ");
        System.out.println("entre 5 pour trier les compte par solde ");
        System.out.println("entre 6 pour trier les compte par date de creation");
        System.out.println("entre 7 pour trier les compte par nom de propietaire ");


        return 0;
    }

    @Override
    public int menuComptabilité() {
        return 0;
    }

    @Override
    public int menuGlobal() {
        System.out.println("==== * menu global * ====");
        System.out.println("entre 1 pour le menu d'ajout");
        System.out.println("entre 2 pour le menu de recherche ");
        System.out.println("entre 3 pour le menu de trie ");
        System.out.println("entre 4 pour le menu de supression ");
        return 0;
    }
}
