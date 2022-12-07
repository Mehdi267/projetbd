package grenobleeat.database;

/**
* Récupérer la liste des catégories disponibles chez grenobleeat sans recommandations */
public class Categorie extends Table {


    private static String tableName = "Categorie";
    private static String[] fields = {"categorie"};

    private static String fieldToPrintAsName = "categorie"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public Categorie(){
       super(tableName, fields);
    }

    /** Afficher la liste des plats pour que l'utilisateur fasse son choix */
    public void getCategoryList() {
        printTableValues(fieldToPrintAsName);
    }


    /**
     * Demander à l'utilisateur de faire un choix parmis nos plats */
    public void selectCategory() {
        getUserChoice("\nVeuillez choisir la catégorie que vous souhaitez\n");
    }
}
