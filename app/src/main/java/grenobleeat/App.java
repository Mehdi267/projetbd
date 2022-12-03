/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package grenobleeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import grenobleeat.database.JavaConnectorDB;
import grenobleeat.database.Plat;
import grenobleeat.database.Restaurant;
import grenobleeat.session.Connexion;

public class App {

    public static void main(String[] args) {
        try {
            System.out.println("Connexion à la base de données en cours...");
            JavaConnectorDB.initConnection();

            // TODO Draw something cool

            int codeRetournConnexion = Connexion.connexion();

            if (codeRetournConnexion == 0) {
                StringBuilder sb = new StringBuilder()
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
                        .append("1. Afficher nos catégories de restaurants\n")
                        .append("2. Afficher nos recommandations de catégories pour vous\n")
                        .append("3. Un restaurant en tête ? Parcourir la liste de nos restaurants")
                        .append("4. Supprimer votre compte");

                System.out.println(sb.toString());

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st = new StringTokenizer(br.readLine());

                String choix = st.nextToken();

                switch (choix) {
                case "1":

                    System.out.println(selectedRestId); // TODO use the restaurant selected
                    break;
                case "2":
                    // TODO Print recommended restaurant
                    Plat.printMealsList();
                    break;

                case "3":
                    String selectedRestId = null;
                    List<Map<String, String>> restaurants = Restaurant.getRestaurantList();

                    selectedRestId = Restaurant.selectRestaurant(restaurants);
                    while(restaurants == null || selectedRestId == null){

                        if(restaurants == null){
                            restaurants = Restaurant.getRestaurantList();
                        }

                        if (selectedRestId == null) {
                            selectedRestId = Restaurant.selectRestaurant(restaurants);
                        }

                    }
                }
            } else {
                System.out.println("Erreur du système, vérifier votre connexion à internet");
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec pendant le fonctionnement de l'application");
            System.exit(1);
        }

    }

}