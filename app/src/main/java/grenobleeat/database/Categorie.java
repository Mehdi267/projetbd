package grenobleeat.database;

import java.util.List;
import java.util.Map;

public class Categorie {

    /**
     * Récupérer la liste des catégories disponibles chez grenobleeat sans recommandations */
    public static List<Map<String, String>> getCategoryList(){
        System.out.println("\nNos catégories\n");
        String categoryFields[] = {"idcategorie", "categorie"};

        String fieldToPrintAsAchoice = "idcategorie";

        List<Map<String, String>> categories = JavaConnectorDB.printTableElementList("Categorie", categoryFields);

        int i = 1;
        for (Map<String, String> line : categories) {
            System.out.println(String.format("%d. %s", i, line.get(fieldToPrintAsAchoice)));
            ++i;
        }

        return categories;
    }

}
