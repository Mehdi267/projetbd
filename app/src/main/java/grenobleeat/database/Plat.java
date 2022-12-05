package grenobleeat.database;


public class Plat extends Table {

    private static String tableName = "Plat";
    private static String[] fields = { "idPlat", "idRest", "nomPlat", "descPlat", "prixPlat"};

    private static String fieldToPrintAsName = "nomPlat"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public Plat(){
       super(tableName, fields);
    }

    /* Afficher la liste des plats pour que l'utilisateur fasse son choix */
    public void getMealList() {
        String restId = Restaurant.getCurrentSelectedTable().get(fields[1]); // en supposant que le premier élément est la clé primaire

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(tableName);
        sb.append(" WHERE idRest=");
        sb.append(restId);
        JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields);
    }


    /**
     * Demander à l'utilisateur de faire un choix parmis nos plats */
    public void selectMeal() {
        getUserChoice("\nVeuillez choisir un plat\n");
    }

}
