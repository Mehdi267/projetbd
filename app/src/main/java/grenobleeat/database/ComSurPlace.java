package grenobleeat.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import grenobleeat.App;

/**
 * Représente la commande sur place souhaitée par l'utilisateur */
public class ComSurPlace extends Table {
    private static String tableName = "ComSurPlace";
    private static String[] fields = {"idComSurPlace", "nbrPersonne", "heureArriveSurPlace"};

    public ComSurPlace(){
        super(tableName, fields);
    }

    /**
     * Récupérer l'heure d'arrivée souhaitée par l'utilisateur */
    public String getHeureArriveSurPlace(){
        return this.getCurrentSelectedTable().get("heureArriveSurPlace");
    }

    /**
     * Récupérer le nombre de personne souhaitée pour la réservation
     * que l'utilisateur a enregistré */
    public int getNbPersonnes(){
        return Integer.parseInt(this.getCurrentSelectedTable().get("nbrPersonne"));
    }


    /**
     * Demander à l'utilisateur le nombre de personnes souhaitée pour la réservation */
    public void getNbPeopleFromUser(){
        String message = "\nVeuillez entrer le nombre de places requises\n";
        getUserChoice(message, "nbrPersonne");
    }

    /** Demander à l'utilisateur l'heure d'arrivee dans le restaurant */
    public void getHeureArriveeFromUser(Restaurant res){
        StringBuilder sb = new StringBuilder("\nVoici les horaires que propose votre restaurant :\n");
        String query = "SELECT horaireOuvertureRest FROM Restaurant WHERE idRest = ?";
        try{
            ResultSet rs = JavaConnectorDB.executeCustomQuery(query, res.getCurrentSelectedTable().get("idRest"));
            int number = 1;
            String restaurantHours;
            Set<String> hours = new HashSet<>();
            while(rs.next()){
                restaurantHours = rs.getString("horaireOuvertureRest");
                String[] tokenisedVersion = restaurantHours.stripLeading().stripTrailing().split(" et ");
                hours.addAll(Arrays.asList(tokenisedVersion));
            }

            for(String hour: hours){
                sb.append(number);
                sb.append(". ");
                sb.append(hour.strip());
                sb.append("\n");
                ++number;
            }



            sb.append("\nEcrivez votre choix : ");
            System.out.println(sb.toString());

            App.sc = new Scanner(System.in);
            int userChoice = App.sc.nextInt();
            List<String> hoursList = new ArrayList<>(hours);
            if(this.getCurrentSelectedTable().containsKey("heureArriveSurPlace")){
                this.getCurrentSelectedTable().replace("heureArriveSurPlace", hoursList.get(userChoice-1));
            }else{
                this.getCurrentSelectedTable().put("heureArriveSurPlace", hoursList.get(userChoice-1));
            }
        }catch(SQLException e){
            System.out.println("Impossible de récupérer les horaires de ce restaurant");
        }

    }

}
