package metier.admin;

import metier.authentification.IServiceIHM;

import java.util.Scanner;

public interface IServiceIHMAdmin extends IServiceIHM {

    int menuModification(Scanner clavier);
    int menuRecherche(Scanner clavier);
    int menuInformations(Scanner clavier);
    int menuAjout(Scanner clavier);
    int menuSuppression(Scanner clavier);
    int tableauDeBord();
    int menuTrie(Scanner clavier);
    int menuComptabilité();

}
