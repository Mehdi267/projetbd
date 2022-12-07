package grenobleeat.database;


public class Commande extends Table {

    private static String tableName = "Commande";
    private static String[] fields = { "idCommande", "dateCommande", "heureCommande", "prixCommande", "statusCommande" };

    private static String fieldToPrintAsName = "dateCommande"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public Commande(){
       super(tableName, fields);
    }

    public void getCommands() {
        printTableValues(fieldToPrintAsName);
    }


}
