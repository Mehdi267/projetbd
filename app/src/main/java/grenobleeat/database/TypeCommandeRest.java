package grenobleeat.database;

/**
 * Traiter les types de commandes possibles dans un restaurant
 * et permettre à l'utilisateur d'en choisir */
public class TypeCommandeRest extends Table{
    private static String tableName = "TypeCommandeRest";
    private static String[] fields = {"idRest", "type"};

    private static String fieldToPrintAsName = "type";

    public TypeCommandeRest(){
        super(tableName, fields);
    }

    /**
     * Récupérer tous les types de commandes possibles dans un restaurant
     *
     * @param r - le restaurant concerné */
    public void getCommandTypesOfRestaurant(Restaurant r){
        String currentRestaurant = r.getCurrentSelectedTable().get("idRest");

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TypeCommandeRest");
        sb.append(" WHERE idRest = ?");

        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, currentRestaurant));
        printTableValues(fieldToPrintAsName);
    }

    /**
     * Afficher les types de commandes possibles dans le restaurant */
    public void getTypesOfCommandInThisRestaurant(){
        printTableValues(fieldToPrintAsName);
    }

    /**
     * Demander à l'utilisateur le type de commande qu'il souhaite */
    public void selectTypeOfCommand(){
        getUserChoice("\nVeuillez choisir le type de commande que vous souhaitez\n");
    }

    /** Demander à l'utilisateur le nombre de places souhaité pour la commande */
    public void selectNbrPlaceCommande(){
        getUserChoice("\nCombien de places pour la réservation ?\n");
    }

}
