package grenobleeat.database;


public class Commande extends Table {

    private static String tableName = "Commande";
    private static String[] fields = { "idCommande", "dateCommande", "heureCommande", "prixCommande", "statusCommande" };

    private static String fieldToPrintAsName = "dateCommande"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public Commande(){
       super(tableName, fields);
    }

    /**
     * Afficher dans le menu le choix d'un restaurant à l'utilisateur en affichant les noms des
     * commandes comme choix dans le menu */
    public void getCommands() {
        printTableValues(fieldToPrintAsName);
    }


}
