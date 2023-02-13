package Dao.Files;

import Dao.IDao;
import presentation.modele.Compte;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CompteDao implements IDao<Compte,String> {


    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(FileBasePaths.ACCOUNT_TABLE);
            lines.remove(0);
            if(!lines.isEmpty())
            {
                ClientDao dao = new ClientDao();

                comptes = lines.stream().map(line ->{
                    Compte com = null;
                    StringTokenizer st = new StringTokenizer(line,"\t");
                    String id = st.nextToken();
                    LocalDateTime date = LocalDateTime.parse(st.nextToken());
                    Double Solde  = Double.parseDouble(st.nextToken());

                    long id_client = Long.parseLong(st.nextToken());

                   com = new Compte(id,Solde,date,dao.findById(id_client));
                   return com;

                }).collect(Collectors.toList());
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    return comptes ;
    }

    @Override
    public Compte findById(String id) {
        return findAll().stream().filter(compte -> Objects.equals(compte.getNumeroCompte(), id)).findFirst().orElse(null);
    }
    public List<Compte> findByIdProp(long id)
    {
        return findAll().stream().filter(compte -> compte.getProprietaire().getId() == id).collect(Collectors.toList());
    }
    @Override
    public Compte save(Compte compte) {

        String accountString= compte.getNumeroCompte()+
                "\t\t\t"+compte.getDateCreation().toString()+
                "\t\t\t"+compte.getSolde()
                +"\t\t\t"+compte.getProprietaire().getId()+"\n";
        try {


            Files.writeString(FileBasePaths.ACCOUNT_TABLE,accountString, StandardOpenOption.APPEND);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    private long getIncrementedIdByIndexFile(Path path){
        String idStr = null;
        Long id = 1L;
        try {
            idStr =  Files.readString(path);
            id = Long.parseLong(idStr);
            id ++;
        } catch (IOException e) {id = 1L;}

        try {
            Files.writeString(path, id.toString());
        }
        catch (IOException e) { e.printStackTrace();}

        return id;
    }
    @Override
    public List<Compte> saveAll(List<Compte> liste) {
        return
        liste
                .stream()
                .map(this::save)
                .collect(Collectors.toList());

    }

    @Override
    public Compte update(Compte compte) {
        List<Compte> compteList = findAll().stream().map(compte1 -> {
            if (Objects.equals(compte1.getNumeroCompte(), compte.getNumeroCompte()))
                return compte;
            else
                return compte1;


        }).collect(Collectors.toList());
      /*   try{
            Files.deleteIfExists(FileBasePaths.INDEX_ACCOUNT);
        }catch (IOException e){
            e.printStackTrace();
        }*/
        FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE,FileBasePaths.ACCOUNT_TABLE_HEADER);
        saveAll(compteList);
        return compte;
    }

    @Override
    public Boolean delete(Compte compte) {

       var comptes = findAll();
       boolean deleted = comptes.remove(compte);
           if (deleted)
           {
               FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE,FileBasePaths.ACCOUNT_TABLE_HEADER);
               saveAll(comptes);
           }
        return deleted;
    }

    @Override
    public Boolean deleteById(String id) {

        var comptes = findAll();
        boolean deleted =comptes.remove(findById(id));
        if (deleted)
        {
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE,FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAll(comptes);
        }
        return deleted;
    }
}
