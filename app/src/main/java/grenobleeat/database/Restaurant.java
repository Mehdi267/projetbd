package grenobleeat.database;


/**
 * Table restaurant de la base de données
 */
public class Restaurant extends Table {

    private static String tableName = "restaurant";
    private static String[] fields = { "idrest", "emailrest", "nomrest", "addrrest", "nbplacerest",
            "textpresentationrest", "horaireouverturerest" };

    private static String fieldToPrintAsName = "restname"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public Restaurant(){
       super(tableName, fields);
    }

    /**
     * Afficher dans le menu le choix d'un restaurant à l'utilisateur en affichant les noms des
     * restaurants comme choix dans le menu */
    public void getRestaurantList() {
        printTableValues(fieldToPrintAsName);
    }

    /**
     * Demander à l'utilisateur de faire un choix parmis nos restaurants */
    public void selectRestaurant() {
        System.out.println("");
    }

}
