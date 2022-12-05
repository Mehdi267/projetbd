package grenobleeat.database;

import java.sql.ResultSet;

/**
 * Table restaurant de la base de données
 */
public class Restaurant extends Table {

    private static String tableName = "Restaurant";
    private static String[] fields = { "idRest", "emailRest", "nomRest", "addrRest", "nbplaceRest",
            "textPresentaionRest", "horaireOuvertureRest" };

    private static String fieldToPrintAsName = "nomRest"; // le champ a afficher dans le menu comme choix pour l'utilisateur

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
        getUserChoice("\nVeuillez choisir un restaurant\n");
    }


    /**
     * Récupérer le nombre de places restant dans le restaurant qui a été sélectionné
     *
     * Parameters:
     * @param heureArrivee - heure d'arrivée sur place du client
     *
     * Return:
     * @return - le nombre de places
     * @return - -1 si impossible de récupérer le nombre de place */
    public int getPlacesLeft(String heureArrivee){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT placeRestante ");
        sb.append("FROM NbrPlaceRestante ");
        sb.append("WHERE idRest=? ");
        sb.append("AND DateCommande=CURDATE() ");
        sb.append("AND heureArriveSurPlace=?;");

        int nombreDePlaces = -1;
        String restId = Restaurant.getCurrentSelectedTable().get(fields[0]); // en supposant que le premier élément est la clé primaire
        ResultSet rs = JavaConnectorDB.executeCustomQuery(sb.toString(), restId, heureArrivee);
        try{
            System.out.println(nombreDePlaces);
            nombreDePlaces = rs.getInt("placeRestante");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Impossible de récupérer le nombre de places restantes dans ce restaurant");
            // TODO maybe exit the system ? or go back
        }
        return nombreDePlaces;
    }

}
