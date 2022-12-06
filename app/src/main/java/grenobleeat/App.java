/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package grenobleeat;

import java.util.Map;
import java.util.Scanner;

import grenobleeat.database.Categorie;
import grenobleeat.database.ComSurPlace;
import grenobleeat.database.JavaConnectorDB;
import grenobleeat.database.Restaurant;
import grenobleeat.database.TypeCommandeRest;
import grenobleeat.session.Connexion;
import grenobleeat.session.DeleteAccount;


public class App {

    public static Scanner sc;
    private static int[] choices = new int[5];

    private static Categorie categorie;
    private static Restaurant restaurant;
    private static TypeCommandeRest typeCommandeRest;
    private static ComSurPlace comSurPlace;

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
            choices[0] = 1;
            categorie = new Categorie();
            categorie.getCategoryList();
            categorie.selectCategory();
            depthOne();
            break;

        case "2":
            choices[0] = 2;
            depthOne();
            break;

        case "3":
            break;
        
        case "4":
            int deleteChoice = DeleteAccount.deleteAccount();
            if (deleteChoice == 1){depthZero();}
            if (deleteChoice == 0){System.exit(0);}
            break;
        }

    }


    /**
     * Les fonctions qui sont exécutées en fonction du choix effectué au premier menu */
    private static void depthOne(){
      if(choices[0] == 1){
        choices[1] = 1;
        restaurant = new Restaurant();
        restaurant.getRestaurantList(categorie);
        //ourRestaurants.getCategorieAuChoixRestaurentsFilter("");
        restaurant.selectRestaurant();
        depthTwo();

      }else if(choices[0] == 2){
        restaurant = new Restaurant();
        restaurant.getRecommendedRestaurents();
        //restaurant.getPropositionRestaurant(30);
        //restaurant.getRecommendedRestaurentsFilter();
        restaurant.selectRestaurant();

      }else if(choices[0] == 3){

        }else if(choices[0] == 4){

      }
    }


    private static void depthTwo(){
        if(choices[1] == 1){
            choices[2] = 1;
            typeCommandeRest = new TypeCommandeRest();
            typeCommandeRest.getCommandTypesOfRestaurant(restaurant);
            typeCommandeRest.selectTypeOfCommand();
            depthThree();

        }else if(choices[1] == 2){

        }else if(choices[1] == 3){

        }else if(choices[1] == 4){

        }
    }

    private static void depthThree(){
        if(choices[2] == 1){
            Map<String, String> selectedType = typeCommandeRest.getCurrentSelectedTable();
            comSurPlace = new ComSurPlace();
            if(selectedType.get("type").equals("surPlace")){
                comSurPlace.getNbPeopleFromUser();
                comSurPlace.getHeureArriveeFromUser(restaurant);
                int nombreDePlaceRest = restaurant.getPlacesLeft(comSurPlace.getHeureArriveSurPlace());
                if(nombreDePlaceRest > comSurPlace.getNbPersonnes()){
                    depthFour();
                }else{

                }
            }else if(selectedType.get("type").equals("livraison")){

            }else if(selectedType.get("type").equals("emporte")){

            }
        }else if(choices[2] == 2){

        }else if(choices[2] == 3){

        }else if(choices[2] == 4){

        }
    }


 
    private static void depthFour(){
        if(choices[2] == 1){

        }else if(choices[2] == 2){

        }else if(choices[3] == 3){

        }
    }


    public static void main(String[] args) {
        try {
            System.out.println("Connexion à la base de données en cours...");
            JavaConnectorDB.initConnection();
            
            //This function is only called when we add new restaurent and we did not math them to all 
            //the right categories
            
            //JavaConnectorDB.setUpCategorie();
            
            // TODO Draw something cool

            int codeRetournConnexion = Connexion.connexion();

            if (codeRetournConnexion == 0) {
                /*
                 * TODO 1. 2. Après avoir choisi le restaurant Demander le type de commande
                 * souhaité (en fonction du restaurant choisi) Si surplace demander le nombre de
                 * places (continuer si le restaurant a assez de place, sinon recommander
                 * d'autres) Demander la période de la journée. (Midi, soir, ou peut importe
                 * pour afficher midi et soir) Afficher la liste des plats pour qu'il puisse
                 * faire un choix (garder quelque part les prix des plats pour le calcul du prix
                 * total) Recommandations ou découverte
                 *
                 * 3. Demander le type de commande souhaité Demander la période de la journée
                 * (Midi, soir ou peut importe) Afficher les restaurants qui font ce type de
                 * commande et qui ont sont disponibles dans cette période Si type de commande
                 * surplace demander le nombre de place afin d'afficher que les restaurants qui
                 * ont assez de place. Afficher la liste des plats (selon recommandations ou
                 * découvrir)
                 */

                /* commande - type de commande - plats - */
                /*
                 * si typecommande surplace - utiliser vue nbplacerestante pour voir s'il y a assez de place dans le restaurant
                 * ajouter l'id de l'utilisateur connecté ajouter une instance dans commande
                 * ensuite passercommande ensuite ajouter le plats
                 *
                 * prixcommande est une vue qui calcule le prix final
                 */

                /*
                 * View nbrplacerestante fonctionne pas encore mais peut ajouter du pseudo code
                 * pour faire fonctionner
                 */
                depthZero();

            } else {
                System.out.println("Erreur du système, vérifier votre connexion à internet");
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("Echec pendant le fonctionnement de l'application");
            e.printStackTrace();
            // System.exit(1);
        }

    }
}
