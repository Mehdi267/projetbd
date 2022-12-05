/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package grenobleeat;

import java.util.Map;
import java.util.Scanner;

import grenobleeat.database.Categorie;
import grenobleeat.database.ComSurPlace;
import grenobleeat.database.JavaConnectorDB;
import grenobleeat.database.Plat;
import grenobleeat.database.Restaurant;
import grenobleeat.database.TypeCommandeRest;
import grenobleeat.session.Connexion;

public class App {

    private static int[] choices = new int[5];

    private static void depthZero() {

        StringBuilder sb = new StringBuilder().append("1. Afficher nos catégories de restaurants\n")
                .append("2. Afficher nos recommandations de catégories pour vous\n")
                .append("3. Un restaurant en tête ? Parcourir la liste de nos restaurants\n")
                .append("4. Supprimer votre compte");

        System.out.println(sb.toString());
        Scanner sc = new Scanner(System.in);

        String choix = sc.next();
        sc.close();

        switch (choix) {
        case "1":
            choices[0] = 1;
            Categorie categorie = new Categorie();
            categorie.getCategoryList();
            categorie.selectCategory();
            depthOne();
            break;

        case "2":
            break;

        case "3":
            break;
        }


        System.out.println("Mauvais choix");
    }


    /**
     * Les fonctions qui sont exécutées en fonction du choix effectué au premier menu */
    private static void depthOne(){
      if(choices[0] == 1){
        Restaurant ourRestaurants = new Restaurant();
        ourRestaurants.getRestaurantList();
        ourRestaurants.selectRestaurant();

      }else if(choices[0] == 2){

      }else if(choices[0] == 3){

        }else if(choices[0] == 4){

      }
    }


    private static void depthTwo(){
        if(choices[0] == 1){

        }else if(choices[0] == 2){

        }else if(choices[0] == 3){
            choices[1] = 3;
            TypeCommandeRest typeRest = new TypeCommandeRest();
            typeRest.setRelatedTable(Restaurant.getCurrentSelectedTable());
            typeRest.selectTypeOfCommand();
        }else if(choices[0] == 4){

        }
    }


    private static void depthThree(){
        if(choices[1] == 1){

        }else if(choices[1] == 2){

        }else if(choices[1] == 3){
            Map<String, String> selectedType = TypeCommandeRest.getCurrentSelectedTable();
            if(selectedType.get("type").equals("SurPlace")){
                ComSurPlace.getNbPeopleFromUser();
                ComSurPlace.getHeureArriveeFromUser();
                int nombreDePlaceRest = Restaurant.getPlacesLeft(ComSurPlace.getHeureArriveSurPlace());
                if(nombreDePlaceRest > ComSurPlace.getNbPersonnes()){
                    depthThree();
                }
            }else if(selectedType.get("type").equals("Livraison")){

            }else if(selectedType.get("type").equals("Emporte")){

            }
        }else if(choices[1] == 4){

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

                String choice = mainMenu();
                if(choice.equals("1")){

                }else if(choice.equals("2")){

                }else if(choice.equals("3")){

                }else if(choice.equals("4")){

                }

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
