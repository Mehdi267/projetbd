package grenobleeat.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

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

        int i = 0;
        for (Map<String, String> line : restList) {
            System.out.println(String.format("{%d}. {%s}", i, line.get(fieldToPrintAsAchoice)));
        }

        return restList;
    }

    public static int selectRestaurant(List<Map<String, String>> restaurantList) {
        System.out.println("\nChoisir un restaurant\n");
        final String fieldToPrint = "idrest";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}
