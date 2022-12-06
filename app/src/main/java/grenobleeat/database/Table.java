package grenobleeat.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import grenobleeat.App;

/**
 * Table de la base de données
 */
public class Table {

    private String name;
    private String[] fields; // on considère que le premier élément est la clé primaire
    private Map<Integer, Map<String, String>> bdContents; // le contenu de la table dans la base de données ligne par ligne

    private Map<String, String> currentSelectedTable = new HashMap<>(); // représente le choix de l'utilisateur

    public Table(String name, String[] fls) {
        this.name = name;
        this.fields = fls;
        this.setBdContents();
    }

    /**
     * Contacter la base de données et récupérer toutes les données qui sont actuellement dans la bd pour la table courante */
    protected void setBdContents(){
        Map<Integer, Map<String, String>> contents = JavaConnectorDB.fetchDataFromDB(this.name, this.fields);
        bdContents = contents;
    }


    protected void setBdContents(Map<Integer, Map<String, String>> contents){
        this.bdContents = contents;
    }

    protected Map<Integer, Map<String, String>> getBdContents(){
        return bdContents;
    }

    /**
     * Affichage des valeurs contenues dans la bd pour le choix de l'utilisateur dans le menu */
    protected void printTableValues(String fieldToPrintAsAchoice) {

        if(this.getBdContents().size() < 1){
           this.setBdContents();
        }

        for (Integer lineNumber : bdContents.keySet()) {
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
    protected String getFieldValue(String field){
        return currentSelectedTable.get(field);
    }


    /**
     * Faire le choix d'une Table depuis ceux qui ont été récupérés dans la base de données.
     *
     * Parameters:
     * @param choice - choix de l'utilisateur
     *
     * Return:
     * @return 0 - si le choix a pu être défini
     * @return -1 - si ce choix est invalide
     * */
    private int selectAvalue(int choice){

        Map<String, String> selectedTable = bdContents.get(choice);
        if(selectedTable != null){
           currentSelectedTable = selectedTable;
           return 0;
        }

        return -1;
    }

    /**
     * Récupère le choix de l'utilisateur et définit la l'instance de la classe avec les propriétés récupérées depuis la base de données
     *
     * Parameters:
     * @param promptMessage - Message à afficher à l'utilisateur
     * */
    protected void getUserChoice(String promptMessage){
        while(true){
            System.out.println(promptMessage);
            App.sc = new Scanner(System.in);
            int userChoice = App.sc.nextInt();
            int isChoiceSuccessfullySet = this.selectAvalue(userChoice);
            if(isChoiceSuccessfullySet == 0){
                break;
            }
            System.out.println("\nChoix incorrect\n");
        }
    }

    protected void getUserChoice(String promptMessage, String fieldToDefine){
        System.out.println(promptMessage);
        App.sc = new Scanner(System.in);
        String userChoice = App.sc.next();
        if(this.currentSelectedTable.containsKey(userChoice)){
            this.currentSelectedTable.replace(fieldToDefine, userChoice); // TODO check why we always enter in else part
        }else{
            this.currentSelectedTable.put(fieldToDefine, userChoice);
        }
    }

    public Map<String, String> getCurrentSelectedTable(){
        return this.currentSelectedTable;
    }


}
