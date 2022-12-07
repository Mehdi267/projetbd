/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package grenobleeat;

import java.util.Map;
import java.util.Scanner;

import grenobleeat.database.Categorie;
import grenobleeat.database.ComLivraison;
import grenobleeat.database.ComSurPlace;
import grenobleeat.database.JavaConnectorDB;
import grenobleeat.database.PasserCommande;
import grenobleeat.database.Plat;
import grenobleeat.database.Restaurant;
import grenobleeat.database.TypeCommandeRest;
import grenobleeat.session.Connexion;
import grenobleeat.session.DeleteAccount;


public class App {

    public static Scanner sc;
    private static int[] choices = new int[6];

    private static Categorie categorie;
    private static Restaurant restaurant;
    private static TypeCommandeRest typeCommandeRest;
    private static ComSurPlace comSurPlace;
    private static ComLivraison comLivraison;
    private static int nbPersonneProposition;
    private static Plat plats;
    private static boolean filtreOn = false;
    private static PasserCommande passerCommande = new PasserCommande();

    private static void depthZero() {

        StringBuilder sb = new StringBuilder().append("1. Afficher nos catégories de restaurants\n")
                .append("2. Afficher nos recommandations de catégories pour vous\n")
                .append("3. Un restaurant en tête ? Parcourir la liste de nos restaurants\n")
                .append("4. Supprimer votre compte");

        System.out.println(sb.toString());
        sc = new Scanner(System.in);
        String choix = sc.next();

        switch (choix) {
        case "1":
            checkfiler();
            categorie = new Categorie();
            categorie.getCategoryList();
            categorie.selectCategory();
            if (!filtreOn){choices[0] = 1;}
            else{choices[0] = 3;}
            depthOne();
            break;

        case "2":
            checkfiler();
            if (!filtreOn){choices[0] = 2;}
            else{choices[0] = 4;}
            depthOne();
            break;

        case "3":
            break;
        
        case "4":
            int deleteChoice = DeleteAccount.deleteAccount();
            if (deleteChoice == 1){depthZero();}
            if (deleteChoice == 0){System.exit(0);}
            break;

        default:
            depthZero();
            break;
        }

    }

    private static void checkfiler(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n Voulez filtrer les résultats par jour/horaire?     \n");  
        sb.append("1  oui\n");
        sb.append("2. non \n");
        System.out.println(sb.toString());
        while(true){
            try{
                sc = new Scanner(System.in);
                int entryuser = sc.nextInt(); 
                if (entryuser == 1){
                    filtreOn = true; break;}
                if (entryuser == 2){
                    filtreOn = false; break; }
                System.out.println("La entre doit être entre 1 et 2");
            }
            catch(Exception e){
                System.out.println("La entre doit être entre 1 et 2");
            }
        }
    }


    /**
     * Les fonctions qui sont exécutées en fonction du choix effectué au premier menu */
    private static void depthOne(){
      if(choices[0] == 1){
        choices[1] = 1;
        restaurant = new Restaurant();
        restaurant.getRestaurantList(categorie);
        restaurant.selectRestaurant();
        depthTwo();

      }else if(choices[0] == 2){
        choices[1] = 1;
        restaurant = new Restaurant();
        restaurant.getRecommendedRestaurents();
        restaurant.selectRestaurant();
        depthTwo();

      }else if(choices[0] == 3){
        choices[1] = 1;
        restaurant = new Restaurant();
        restaurant.getCategorieAuChoixRestaurentsFilter(categorie.getCurrentSelectedTable().get("categorie"));
        restaurant.selectRestaurant();
        depthTwo();

      }else if(choices[0] == 4){
        choices[1] = 1;
        restaurant = new Restaurant();
        restaurant.getRecommendedRestaurentsFilter();
        restaurant.selectRestaurant();
        depthTwo();

      }else if(choices[0] == 5){
        choices[1] = 1;
        restaurant = new Restaurant();
        restaurant.getPropositionRestaurant(nbPersonneProposition);
        restaurant.selectRestaurant();
        depthTwo();
      }else{
          depthZero();
      }
    }


    private static void depthTwo(){
        if(choices[1] == 1){
            choices[2] = 1;
            typeCommandeRest = new TypeCommandeRest();
            typeCommandeRest.getCommandTypesOfRestaurant(restaurant);
            typeCommandeRest.selectTypeOfCommand();
            depthThree();
        }else{
            depthOne();
        }
    }

    private static void depthThree(){
        if(choices[2] == 1){
            Map<String, String> selectedType = typeCommandeRest.getCurrentSelectedTable();
            if(selectedType.get("type").equals("surPlace")){
                choices[3] = 1;
                comSurPlace = new ComSurPlace();
                comSurPlace.getNbPeopleFromUser();
                comSurPlace.getHeureArriveeFromUser(restaurant);
                int nombreDePlaceRest = restaurant.getPlacesLeft(comSurPlace.getHeureArriveSurPlace());
                if(nombreDePlaceRest > comSurPlace.getNbPersonnes()){
                    System.out.println("\n Pas assez de place pour faire la commande  ?\n");
                    System.out.println("\n Voici les restaurants pouvant accueillir\n");
                    depthFour();
                }else{
                    nbPersonneProposition = comSurPlace.getNbPersonnes();
                    choices[0] = 5;
                    depthOne();
                }
            }else if(selectedType.get("type").equals("livraison")){
                choices[3] = 2;
                comLivraison = new ComLivraison();
                depthFour();
            }else if(selectedType.get("type").equals("emporte")){
                choices[3] = 3;
                depthFour();

            }
        }else{
            depthTwo();
        }
    }

 
    private static void depthFour(){
        choices[4] = choices [3];
        plats = new Plat();
        plats.getMealList(restaurant);
        plats.selectMeal();
        plats.printSelectedMealsAllergenes();
        plats.askForRemoval();
        depthFive();

    }

    private static void depthFive(){
        System.out.println("\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("Confirmation commande \n");  
        sb.append("1. Confirmer la commande ?\n");
        sb.append("2. Retour au menu principal \n");
        System.out.println(sb.toString());
        while(true){
            try{
                sc = new Scanner(System.in);
                int entryuser = sc.nextInt(); 
                if (entryuser == 1){break;}
                if (entryuser == 2){depthZero(); return;}
            }
            catch(Exception e){
                System.out.println("La entre doit être entre 1 et 2");
            }
        }
        if(choices[4] == 1){
            choices[5] = 1;
            passerCommande.passerCommandeSurPlace(typeCommandeRest, restaurant, comSurPlace, plats);
            depthSix();
        }
        if(choices[4] == 2){
            choices[5] = 1;
            passerCommande.passerCommandeLivraison(typeCommandeRest, restaurant, comLivraison, plats);
            depthSix();
        }
        if(choices[4] == 3){
            choices[5] = 1;
            passerCommande.passerCommandeEmporter(typeCommandeRest, restaurant, plats);
            depthSix();
        }else{
            depthFour();
        }
    }

    private static void depthSix(){
        if(choices[5] == 1){
            System.out.println("\nVoulez-vous noter cette commande ?\n");
            StringBuilder sb = new StringBuilder();
            sb.append("Evaluation\n");  
            sb.append("1. Faire une evaluation ?\n");
            sb.append("2. Retour au menu principale \n");
            System.out.println(sb.toString());
            while(true){
                try{
                    sc = new Scanner(System.in);
                    int entryuser = sc.nextInt(); 
                    if (entryuser == 1){break;}
                    if (entryuser == 2){depthZero();return;}
                }
                catch(Exception e){
                    System.out.println("La entre doit être entre 1 et 2");
                }
            }
            System.out.print("Veuillez entrer votre note : ");
            sc = new Scanner(System.in);
            int note = sc.nextInt();
            System.out.println("Veuillez entrer une description: ");
            sc = new Scanner(System.in);
            String desc = sc.nextLine();
            passerCommande.evaluateCommande(restaurant, Integer.toString(note), desc);
        }else{
            depthFour();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n Faire une autre commande ?\n");  
        sb.append("1. Retour au menu principal ?\n");
        sb.append("2. Quitter l'application \n");
        System.out.println(sb.toString());
        while(true){
            try{
                sc = new Scanner(System.in);
                int entryuser = sc.nextInt(); 
                if (entryuser == 1){ depthZero(); return;}
                if (entryuser == 2){ JavaConnectorDB.closeConnection(); System.exit(0);}
            }
            catch(Exception e){
                System.out.println("La entre doit être entre 1 et 2");
            }
        }

    }


    public static void main(String[] args) {
        try {
            System.out.println("Connexion à la base de données en cours...");
            JavaConnectorDB.initConnection(args);
            

            //JavaConnectorDB.setUpCategorie();
            int codeRetournConnexion = Connexion.connexion();

            if (codeRetournConnexion == 0) {
                depthZero();

            } else {
                System.out.println("Erreur du système, vérifier votre connexion à internet");
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("Echec pendant le fonctionnement de l'application");
            System.exit(1);
        }

    }
}
