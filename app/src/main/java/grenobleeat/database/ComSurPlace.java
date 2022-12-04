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

    private static int nbPersonnes;
    private static int idComSurPlace;
    private static String heureArrivee;

    public ComSurPlace(){
        super(tableName, fields);
    }


    /**
     * Définir le nombre de places qu'il faut pour à l'utilisateur */
    public void setCommandPlaces(int nbPlaces){
        if(nbPlaces > 0){
           nbPersonnes = nbPlaces;
        }
    }


    public void setHeureArriveSurPlace(String heureArrivee){
        if(heurePossible.contains(heureArrivee)){
            this.heureArrivee = heureArrivee;
        }
    }


    public void setNbPersonnes(int nbPersonnes){
        if(nbPersonnes > 0){
            this.nbPersonnes = nbPersonnes;
        }
    }


}
