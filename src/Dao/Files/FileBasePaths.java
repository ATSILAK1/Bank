package Dao.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public interface FileBasePaths {
    Path DB_FOLDER = Paths.get("myfilebase");
    Path LOGS_FOLDER = Paths.get(DB_FOLDER.toString(),"archives");
    Path CLIENT_TABLE = Paths.get(DB_FOLDER.toString(),"client.txt");
    Path ACCOUNT_TABLE = Paths.get(DB_FOLDER.toString(),"account.txt");
    Path AGENCY_TABLE = Paths.get(DB_FOLDER.toString() , "agency.txt");
    Path INDEX_CLIENT = Paths.get(DB_FOLDER.toString(),"clientsLastIndex.txt");

    Path INDEX_ACCOUNT = Paths.get(DB_FOLDER.toString() , "compteLastIndex.txt");
    private static void creeDossierOuFichier(Path path ,String header, boolean isfile)
    {
        if(isfile)
        {
            try {
            if(!path.toFile().exists())
                {
                    path.toFile().createNewFile();
                    Files.writeString(path,header);
                    System.out.println();
                }
            }catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {

                if (!path.toFile().exists())
                {
                    path.toFile().mkdir();
                    System.out.println("cree avec succes");
                }
                else System.out.println("echec de la creation");

        }
    }

    String CLIENT_TABLE_HEADER  =  "ID\t\t\tNOM\t\t\tPRENOM\t\t\tLOGIN\t\t\tMOT DE PASS\t\t\tCIN\t\t\tEMAIL\t\t\tTELEPHONE\t\t\tSEXE\t\t\tID_AGENCE\n";
    String ACCOUNT_TABLE_HEADER = "ID\t\t\tDATE_CREATION\t\t\tSOLDE\t\t\tID_CLIENT\n";
    String AGENCY_TABLE_HEADER  = "ID\t\t\tNOM\t\t\tEMAIL\t\t\tTELEPHONE\t\t\tADRESSE\n";
    String LOG_HEADER = "DATE\t\t\tTEMPS\t\t\tTYPE\t\t\tMESSAGE\n";
    static void creeDB(){
        creeDossierOuFichier(DB_FOLDER,"NULL",false);
        creeDossierOuFichier(LOGS_FOLDER,"NULL",false);
        creeDossierOuFichier(CLIENT_TABLE,CLIENT_TABLE_HEADER,true);
        creeDossierOuFichier(ACCOUNT_TABLE,ACCOUNT_TABLE_HEADER,true);
        creeDossierOuFichier(AGENCY_TABLE,AGENCY_TABLE_HEADER,true);
        creeDossierOuFichier(INDEX_CLIENT,null,true);
    }
    static void changeFile(Path path, String header) {

        if (path.toFile().exists()) {
            try {
                path.toFile().delete();
                path.toFile().createNewFile();
                Files.writeString(path, header);
                System.out.println(path.getFileName() + " a été changé avec succès");

            }       catch (IOException e) { e.printStackTrace();}
        }
        else            System.out.println(path.getFileName() + " n'existe pas");



    }
}
