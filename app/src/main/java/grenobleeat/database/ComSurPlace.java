package grenobleeat.database;

import java.util.HashSet;
import java.util.Set;

public class ComSurPlace extends Table {
    private static String tableName = "ComSurPlace";
    private static String[] fields = {"idComSurPlace", "nbrPersonne", "heureArriveSurPlace"};
    private static Set<String> heurePossible = new HashSet<String>(){{
            add("midi");
            add("soir");
            add("midi/soir");
    }};

    /* Static pour ne pas avoir à créer d'instance
     * Après une commande, les valeurs peuvent être modifiée sans problèmes
     * car déjà insérées dans la BD */
    private static int nbPersonnes;
    private static int idComSurPlace;
    private static String heureArrivee;

    public ComSurPlace(){
        super(tableName, fields);
    }


    /**
     * Définir le nombre de places qu'il faut à l'utilisateur */
    public static void setCommandPlaces(int nbPlaces){
        if(nbPlaces > 0){
           nbPersonnes = nbPlaces;
        }
    }


    public static String getHeureArriveSurPlace(){
        return heureArrivee;
    }

    public static void setHeureArriveSurPlace(String heure){
        if(heurePossible.contains(heureArrivee)){
            heureArrivee = heure;
        }
    }

    public static int getNbPersonnes(){
        return nbPersonnes;
    }

    public static void setNbPersonnes(int nbPers){
        if(nbPersonnes > 0){
            nbPersonnes = nbPers;
        }
    }

    public static void setIdComSurPlace(int idSurPlace){
        idComSurPlace = idSurPlace;
    }

    public static void getNbPeopleFromUser(){
        String message = "\nVeuillez entrer le nombre de places requis\n";
        getUserChoice(message, "nbrPersonne");
    }

    public static void getHeureArriveeFromUser(){
        StringBuilder sb = new StringBuilder("\nVeuillez choisir une heure d'arrivée :\n");
        sb.append("1. midi\n");
        sb.append("2. soir\n");
        sb.append("3. peut-importe\n");
        sb.append("\nVotre choix : ");
        getUserChoice(sb.toString(), "heureArriveSurPlace");
    }

}
