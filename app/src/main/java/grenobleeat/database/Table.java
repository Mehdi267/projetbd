package grenobleeat.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Table de la base de données
 */
public class Table {


    private String name;
    private String[] fields;
    private Map<Integer, Map<String, String>> bdContents; // le contenu de la table dans la base de données ligne par ligne

    private static int currentSelectedTable = -1; // représente le choix de l'utilisateur

    public Table(String name, String[] fields) {
        this.name = name;
        this.fields = fields;
        this.setBdContents();
    }

    /**
     * Contacter la base de données et récupérer toutes les données qui sont actuellement dans la bd pour la table courante */
    public void setBdContents(){
        Map<Integer, Map<String, String>> restList = JavaConnectorDB.fetchDataFromDB(this.name, this.fields);
        this.bdContents = restList;
    }

    public Map<Integer, Map<String, String>> getBdContents(){
        return this.bdContents;
    }


    /**
     * Affichage des valeurs contenues dans la bd pour le choix de l'utilisateur dans le menu */
    public void printTableValues(String fieldToPrintAsAchoice) {

        if(this.getBdContents().size() < 1){
           this.setBdContents();
        }

        for (Integer lineNumber : this.bdContents.keySet()) {
            System.out.println(String.format("%d. %s", lineNumber, this.getBdContents().get(lineNumber).get(fieldToPrintAsAchoice)));
        }

    }

    /**
     * Récupère la valeur d'un champ dans la base de données en fonction du choix de l'utilisateur
     * dans le menu contextuel.
     * On peut constater que le choix de l'utilisateur correspondant aux clés dans bdContents
     *
     * Parameters:
     * @param choice - choix de l'utilisateur dans le menu contextuelle
     * */
    public String getFieldValue(String field){
        return this.getBdContents().get(currentSelectedTable).get(field);
    }


    /**
     * Faire le choix d'une Restaurant depuis ceux qui ont été récupérés dans la base de données.
     *
     * Parameters:
     * @param choice - choix de l'utilisateur
     * */
    public void selectAvalue(int choice){
        if(choice > 0){
            currentSelectedTable = choice;
        }
    }

    /**
     * Vérifier si un choix a bien été fait */
    public boolean isChoiceSet(){

    }

}
