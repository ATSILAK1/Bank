package metier.clients;

import presentation.modele.Compte;

import java.util.Scanner;

public interface IServiceClient {

        boolean versement(Scanner clavier );
        boolean retrait  (Scanner clavier);

        boolean retrait  (int choixRetrait);
        boolean virement (Scanner clavier);

        boolean modifierProfile(int choixModification, Scanner clavier);

        void dernièresOpérations();
        Double afficherSolde();
        Compte choisirCompte(String id);

        void afficherTicket();

}
