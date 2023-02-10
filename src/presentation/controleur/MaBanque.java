package presentation.controleur;

import metier.admin.ServiceAdmin;
import metier.authentification.IAuth;
import presentation.modele.Banque;

import java.util.Scanner;

public class MaBanque {
        public static IAuth loginService;

        public static void main(String[] args) {

                Banque maBanque
                        = new Banque(   "BP",
                                        "Hassan Rabat",
                                        "212535224433",
                                        "bp@banquePop.ma");


                Scanner clavier = new Scanner(System.in);
                ServiceAdmin serv = new ServiceAdmin(maBanque);
                System.out.println(serv.nouveauClient(clavier));
                System.out.println(serv.nouveauClient(clavier));
                System.out.println(serv.nouveauClient(clavier));
                System.out.println(serv.trierClientParCin());
                System.out.println(serv.trierClientParEmail());

                serv.modifierClient("Nom", clavier);
                System.out.println(serv.chercherClientParId(1L));
                //loginService = new ServiceAuth(maBanque, clavier);
                //loginService.seConnecter();
                clavier.close();


        }

}
