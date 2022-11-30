package grenobleeat.database;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Restaurant {
    /**
     * Affiche la liste des restaurants contenu dans la base de données sans tenir
     * compte des recommandations pour l'utilisateur.
     *
     * @return List représentant la liste des restaurants avec les valeurs des
     *         différents champs
     */
    public static List<Map<String, String>> getRestaurantList() {
        System.out.println("\nNos restaurants partenaires\n");
        String restaurantFields[] = { "idrest", "emailrest", "nomrest", "addrrest", "nbplacerest" };
        String fieldToPrintAsAchoice = "nomrest";
        List<Map<String, String>> restList = JavaConnectorDB.printTableElementList("restaurant", restaurantFields);

        int i = 1;
        for (Map<String, String> line : restList) {
            System.out.println(String.format("%d. %s", i, line.get(fieldToPrintAsAchoice)));
            ++i;
        }

        return restList;
    }

    public static String selectRestaurant(List<Map<String, String>> restaurantList) {

        System.out.println("\nChoisir un restaurant\n");
        final String idField = "idrest";


        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        sc.close();

        try{
            String restChosenId = restaurantList.get(choice).get(idField);
            return restChosenId;
        }catch(IndexOutOfBoundsException e){
            System.out.println("\nChoix incorrect\n");
            return null;
        }

    }
}
