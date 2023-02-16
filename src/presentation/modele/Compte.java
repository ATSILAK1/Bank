package presentation.modele;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Compte {
    private static long          compteur = 1;
    private String          numeroCompte;
    private Double          solde;
    private LocalDateTime   dateCreation;
    private Client          proprietaire;
    private List<Log>       logs = new ArrayList<>();

    public void setDateCreation() { this.dateCreation = LocalDateTime.now(); }
    public void setPropriétaire(Client propriétaire) {
        this.proprietaire = propriétaire;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }
    public void setLog(TypeLog type, String msg) {

        Log log = new Log(LocalDate.now(), LocalTime.now(), type, msg);
        logs.add(log);
    }

    public Client           getProprietaire() {
        return proprietaire;
    }
    public Double           getSolde() {
        return solde;
    }
    public String getNumeroCompte() {
        return numeroCompte;
    }
    public void setNumeroCompte() {
        this.numeroCompte = String.valueOf(compteur++);
    }
    public LocalDateTime    getDateCreation() {
        return dateCreation;
    }
    public List<Log>        getLogs() {
        return logs;
    }

    public void setNumeroCompte(String id)
    {
        this.numeroCompte = id ;
    }
    public void setDateCreation(LocalDateTime date)
    {
        this.dateCreation = date ;
    }
    public Compte(){
        setNumeroCompte();
        setDateCreation();
        setSolde(0.0);
        setPropriétaire(null);
        setLog(TypeLog.CREATION,"Compte créé");
    }

    public Compte(String numeroCompte, Double solde, LocalDateTime dateCreation, Client proprietaire) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.proprietaire = proprietaire;
    }
    public Compte (String id,Double solde , Client proprietaire)
    {

        setDateCreation();
        this.numeroCompte = id ;
        this.solde = solde ;
        this.proprietaire = proprietaire;
        setLog(TypeLog.CREATION,"Compte cree");
    }
    @Override
    public String toString() {

        String      compteStr  = "------------------------------------------------------\n";
                    compteStr += "| N° de Compte            : "   + getNumeroCompte()   + "\n";
                    compteStr += "| Solde du Compte         : "   + getSolde()    + " Dh\n" ;
                    compteStr += "| Propriétaire du Compte  : "   + getProprietaire().getNomComplet() + "\n" ;
                    compteStr += "------------------------------------------------------\n";

        return compteStr;
    }

    public String getPropName()
    {
        return proprietaire.getNomComplet();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Compte c) {
            return this.numeroCompte.equals(c.getNumeroCompte());
        }
        return false;
    }
}


