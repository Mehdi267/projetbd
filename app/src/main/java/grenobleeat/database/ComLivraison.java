package grenobleeat.database;

import grenobleeat.session.Connexion;

public class ComLivraison extends Table {
    private static String tableName = "ComLivraison";
    private static String[] fields = {"idComLivraison", "textLivreur", "heureLivraison"};
    private String adresseUtilisateur;

    public ComLivraison(){
        super(tableName, fields);
        setAdresseUtilisateur();
    }

    public String getAdresseUtilisateur() {
        return adresseUtilisateur;
    }

    public void setAdresseUtilisateur() {
        this.adresseUtilisateur = JavaConnectorDB.getUserAdress(Connexion.getCurrentUserId()); 
    }
        

}
