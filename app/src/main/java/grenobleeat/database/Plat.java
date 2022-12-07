package grenobleeat.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import grenobleeat.App;

public class Plat extends Table {

    private static String tableName = "Plat";
    private static String[] fields = {"idPlat", "nomPlat", "descPlat", "prixPlat", "allergene"};

    // TODO if user enters wrong format do not leave with the stack error

    private List<String> meals = new ArrayList<>();
    private Map<String, Integer> selectedMeals = new HashMap<>();
    private Map<String, List<String>> selectedMealsAllergenes = new HashMap<>();

    public Plat(){
       super(tableName, fields);
    }


    /** Récupère la liste des plats qui ont été choisi par l'utilisateur
     * ainsi que leur quantité */
    public Map<String, Integer> getSelectedMeals(){ return this.selectedMeals; }


    /** Format l'entrée de l'utilisateur pour pouvoir faire le stockage ensuite
     * dans la liste des plats qui ont été sélectionnés
     *
     * @param listePlats - entree de l'utilisateur (1:4, 2:6)
     * @param storageLocation - variable dans laquelle stockée les résultats */
    private void sortUserChoices(String listePlats, Map<String, Integer> storageLocation){
       listePlats = listePlats.strip();
       int number;
       int quantity;
       for(String choice: listePlats.split(",")){
           number = Integer.parseInt(choice.strip().split(":")[0].strip());
           quantity = Integer.parseInt(choice.strip().split(":")[1].strip());
           storageLocation.put(meals.get(number-1), quantity);
       }
    }


    private void sortUserChoices(String listePlats, List<String> storageLocation){
       listePlats = listePlats.strip();
       int number;
       for(String choice: listePlats.split(",")){
           number = Integer.parseInt(choice.strip());
           storageLocation.add(meals.get(number-1));
       }
    }


    /**
     * Récupérer tous les allergènes d'un plat
     *
     * @param meal - nom du plat
     *
     * @return - la liste des allergènes */
    private List<String> getAllallergenesOfaMeal(String meal){
        List<String> allergenes = new ArrayList<>();
        for(Map<String, String> line: this.getBdContents().values()){
            if(line.get("nomPlat").equals(meal)){
                allergenes.add(line.get("allergene"));
            }
        }
        return allergenes;
    }

    private void buildMealAllergenes(){
        for(String meal: this.selectedMeals.keySet()){
            this.selectedMealsAllergenes.put(meal, this.getAllallergenesOfaMeal(meal));
        }
    }

    private void printAllergenes(String meal){
        System.out.println(meal);
        System.out.println();
        for(String allergene: this.selectedMealsAllergenes.get(meal)){
            System.out.println(allergene);
        }
        System.out.println("----");
    }

    private void printMeals(){
        Set<String> plats = new HashSet<>();
        for(Integer lineNumber : this.getBdContents().keySet()){
            plats.add(this.getBdContents().get(lineNumber).get("nomPlat"));
        }

        int number = 1;
        for(String plat: plats){
            this.meals.add(plat);
            System.out.format("%d. %s\n", number, plat);
            ++number;
        }
    }




    /**
     * Affiche la liste des plats d'un restaurant selon la catégorie de plat choisie par l'utilisateur
     *
     * @param r - le restaurant */
    public void getMealList(Restaurant r) {
        String restId = r.getCurrentSelectedTable().get("idRest"); // en supposant que le premier élément est la clé primaire

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Plat.idPlat, Plat.nomPlat, Plat.descPlat, Plat.prixPlat, AllergenePlat.allergene FROM Plat, AllergenePlat, Restaurant WHERE Plat.idPlat = AllergenePlat.idPlat");
        sb.append(" AND Restaurant.idRest = ? AND Plat.idRest = Restaurant.idRest ORDER BY Plat.nomPlat;");
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, restId));
        printMeals();
    }



    /**
     * Demander à l'utilisateur de faire un choix parmis nos plats */
    public void selectMeal() {
        System.out.println("\nChoisissez vos plats\n");
        System.out.println("Tips: Ecrivez la liste des numéros séparés par des virgules\n");
        System.out.println("Exemple: 1:3, 2:1, 3:5\n");
        System.out.print("\n\nVos choix: ");
        App.sc = new Scanner(System.in);
        String listePlats = App.sc.nextLine();
        sortUserChoices(listePlats, this.selectedMeals);
    }



    /**
     * Afficher la liste des plats sélectionnés par l'utilisateur avec leur allergènes */
    public void printSelectedMealsAllergenes(){
        this.buildMealAllergenes();
        for(Map.Entry<String, List<String>> mealAller: this.selectedMealsAllergenes.entrySet()){
           this.printAllergenes(mealAller.getKey());
        }
    }


    /**
     * Demander à l'utilisateur s'il souhaite supprimer des plats dû à ses allergies */
    public void askForRemoval(){
        System.out.print("Voulez-vous supprimer des plats ? Oui(O) ou Non(N) :");
        App.sc = new Scanner(System.in);
        String choice = App.sc.next();
        if(choice.equals("O") || choice.equals("o")){
           removeMeal();
        }
    }

    private void removeMeal(){
        System.out.println("Entrez les numéros de plats séparés par des virgules\n");
        List<String> mealToremove = new ArrayList<>();
        App.sc = new Scanner(System.in);
        this.sortUserChoices(App.sc.nextLine(), mealToremove);

        for(String meal: mealToremove){
            this.selectedMeals.remove(meal);
        }
        printSelectedMealsAllergenes();
    }

    /**
     * Récupérer les données d'un plat
     *
     * @param mealName - nom du plat
     *
     * @return - les champs et leur valeur concernant ce plat */
    public Map<String, String> getMealFields(String mealName){
        for(Map<String, String> line: this.getBdContents().values()) {
            if(line.get("nomPlat").equals(mealName)){
                return line;
            }
        }
        return null;
    }


}
