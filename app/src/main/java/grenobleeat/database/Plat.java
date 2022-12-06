package grenobleeat.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import grenobleeat.App;

public class Plat extends Table {

    private static String tableName = "Plat";
    private static String[] fields = {"idPlat", "nomPlat", "descPlat", "prixPlat", "allergene"};

    private static String fieldToPrintAsName = "nomPlat"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    private List<Map<String, String>> selectedMeals = new ArrayList<>();
    private Map<String, List<String>> selectedMealsAllergenes = new HashMap<>();

    public Plat(){
       super(tableName, fields);
    }

    /**
     * Affiche la liste des plats d'un restaurant selon la catégorie de plat choisie par l'utilisateur */
    public void getMealList(Restaurant r) {
        String restId = r.getCurrentSelectedTable().get("idRest"); // en supposant que le premier élément est la clé primaire

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Plat.idPlat, Plat.nomPlat, Plat.descPlat, Plat.prixPlat, AllergenePlat.allergene FROM Plat, AllergenePlat, Restaurant WHERE Plat.idPlat = AllergenePlat.idPlat");
        sb.append(" AND Restaurant.idRest = ? AND Plat.idRest = Restaurant.idRest ORDER BY Plat.nomPlat;");
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, restId));
        printTableValues(fieldToPrintAsName);
    }


    private void printMeals(){
    }


    /**
     * Demander à l'utilisateur de faire un choix parmis nos plats */
    public void selectMeal() {
        System.out.println("\nChoisissez vos plats\n");
        System.out.println("Tips: Ecrivez la liste des numéros séparés par des virgules\n");
        System.out.println("Exemple: 1, 2, 3\n");
        System.out.print("\n\nVos choix: ");
        App.sc = new Scanner(System.in);
        String listePlats = App.sc.nextLine();
        sortUserChoices(listePlats);
    }

    private void sortUserChoices(String listePlats){
       listePlats = listePlats.strip();
       int number;
       for(String choiceNumber: listePlats.split(",")){
           number = Integer.parseInt(choiceNumber.strip());
           this.selectedMeals.add(this.getBdContents().get(number));
       }
    }

    public void printSelectedMealsAllergenes(){
        for(Map<String, String> meal : selectedMeals){
            System.out.println(meal.get("nomPlat"));
            System.out.println(meal.get("allergene"));
        }
    }



}
