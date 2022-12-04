package grenobleeat.database;

public class TypeCommandeRest extends TableRelated{
    private static String tableName = "TypeCommandeRest";
    private static String[] fields = {"idRest", "type"};

    private static String fieldToPrintAsName = "type";

    public TypeCommandeRest(){
        super(tableName, fields);
    }

    public void getCommandTypesOfRestaurant(){
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
