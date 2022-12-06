package grenobleeat.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComSurPlace extends Table {
    private static String tableName = "ComSurPlace";
    private static String[] fields = {"idComSurPlace", "nbrPersonne", "heureArriveSurPlace"};

    private static boolean isCreated = false;

    public ComSurPlace(){
        super(tableName, fields);
    }

    public static boolean isCreated(){
       return isCreated;
    }

    /**
     * Set that the instance was created */
    public static void setIsCreated(boolean create){
        isCreated = create;
    }

    public String getHeureArriveSurPlace(){
        return this.getCurrentSelectedTable().get("heureArriveSurPlace");
    }


    public int getNbPersonnes(){
        return Integer.parseInt(this.getCurrentSelectedTable().get("nbrPersonne"));
    }


    public void getNbPeopleFromUser(){
        String message = "\nVeuillez entrer le nombre de places requises\n";
        getUserChoice(message, "nbrPersonne");
    }

    public void getHeureArriveeFromUser(Restaurant res){
        StringBuilder sb = new StringBuilder("\nVoici les horaires que propose votre restaurant :\n");
        String query = "SELECT horaireOuvertureRest FROM Restaurant WHERE idRest = ?";
        try{
            ResultSet rs = JavaConnectorDB.executeCustomQuery(query, res.getCurrentSelectedTable().get("idRest"));
            int number = 1;
            String restaurantHours;
            while(rs.next()){
                restaurantHours = rs.getString("horaireOuvertureRest");
                sb.append(number);
                sb.append(". ");
                sb.append(restaurantHours);
                sb.append("\n");
                ++number;
            }

        }catch(SQLException e){
            System.out.println("Impossible de récupérer les horaires de ce restaurant");
        }
        sb.append("\nEcrivez votre choix : "); // The user will write his choice because we want to store it for later use
        getUserChoice(sb.toString(), "heureArriveSurPlace");
    }

}
