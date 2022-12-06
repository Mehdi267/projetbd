package grenobleeat.database;

public class TypeCommandeRest extends Table{
    private static String tableName = "TypeCommandeRest";
    private static String[] fields = {"idRest", "type"};

    private static String fieldToPrintAsName = "type";

    public TypeCommandeRest(){
        super(tableName, fields);
    }

    public void getCommandTypesOfRestaurant(Restaurant r){
        String currentRestaurant = r.getCurrentSelectedTable().get("idRest");

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TypeCommandeRest");
        sb.append(" WHERE idRest = ?");

        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, currentRestaurant));
        printTableValues(fieldToPrintAsName);
    }

    public void getTypesOfCommandInThisRestaurant(){
        printTableValues(fieldToPrintAsName);
    }

    public void selectTypeOfCommand(){
        getUserChoice("\nVeuillez choisir le type de commande que vous souhaitez\n");
    }

    public void selectNbrPlaceCommande(){
        getUserChoice("\nCombien de places pour la réservation ?\n");
    }

}
