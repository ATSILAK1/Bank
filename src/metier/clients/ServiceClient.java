package metier.clients;

import presentation.modele.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class ServiceClient implements IServiceClient{
    Client client ;
    Compte compte ;
    Banque banque ;

    public ServiceClient(Compte compte, Banque banque) {
        this.compte = compte;
        this.banque = banque;
    }

    @Override
    public boolean versement(Scanner clavier ) {
        System.out.println("entre le montant que vous voulez versé");
        Double montant = clavier.nextDouble();
        if(montant < 0 ) return false ;

        compte.setSolde(compte.getSolde()+montant);
        compte.setLog(TypeLog.VERSEMENT , "versement de "+montant);
        return true ;
    }

    @Override
    public boolean retrait(Scanner clavier) {
        System.out.println("entre le montant que vous voulez retirer");
        Double montant = clavier.nextDouble();
    if (montant< 0 || compte.getSolde()-montant < 0)
            return false;

    compte.setSolde(compte.getSolde()-montant);
    compte.setLog(TypeLog.RETRAIT,"retrait de "+ montant );
    return true ;

    }

    @Override
    public boolean retrait(int choixRetrait) {

        return false;
    }

    @Override
    public boolean virement(Scanner clavier) {
        System.out.println("entre le numero de compte vers le quel vous voulez faire votre virement");
        Compte compte = banque.getCompteParId(clavier.next());
        if (compte == null || this.compte == compte )
            return false;
        System.out.println("entre le montant du virement");
        double montant = clavier.nextDouble();
        if (montant < 0 && this.compte.getSolde() - montant < 0)
            return false ;

        this.compte.setSolde(this.compte.getSolde() - montant);
        this.compte.setLog(TypeLog.VERSEMENT , "virment effectue d'une valeur " + (-montant));
        compte.setSolde(compte.getSolde()+montant);
        compte.setLog(TypeLog.VERSEMENT , "virement recu " + montant);

        return true ;
    }

    @Override
    public boolean modifierProfile(int choixModification, Scanner clavier) {
        switch (choixModification) {
            case 1 -> {
                System.out.println("entre le nouveau mot de passe ");
                compte.getProprietaire().setMotDePasse(clavier.next());
            }
            case 2 -> {
                System.out.println("entre le nouveau login");
                compte.getProprietaire().setLogin(clavier.next());
            }
            case 3 -> {
                System.out.println("entre le nouveau nom  ");
                compte.getProprietaire().setNom(clavier.next());
                System.out.println("entre le nouveau prenom");
                compte.getProprietaire().setPrenom(clavier.next());
            }
            case 4 -> {
                System.out.println("entre le nouveau numero de telephone ");
                compte.getProprietaire().setTel(clavier.next());
            }
        }
        return true;
    }

    @Override
    public void dernièresOpérations() {
        System.out.println("======= operation effectue =======");
        System.out.println("=== date " + LocalDateTime.now());
        for (Log l : compte.getLogs())
        {
            System.out.println(l);
        }
        System.out.println("==========================");
    }

    @Override
    public Double afficherSolde() {

        return compte.getSolde();
    }

    @Override
    public Compte choisirCompte(String id) {

        for (Compte client1 : client.getComptesClient())
        {
            if (Objects.equals(client1.getNumeroCompte(), id))
                return client1;
        }

        return null ;
    }

    @Override
    public void afficherTicket() {

        System.out.println("======= operation effectue =======");
        System.out.println("=== date " + LocalDateTime.now());
        System.out.println(compte.getLogs().get(compte.getLogs().size()-1).toString());
        System.out.println("===================================");

    }
}
