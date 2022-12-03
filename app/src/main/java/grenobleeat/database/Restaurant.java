package grenobleeat.database;

import java.util.Scanner;

/**
 * Table restaurant de la base de données
 */
public class Restaurant extends Table {

    private static String tableName = "Restaurant";
    private static String[] fields = { "idRest", "emailRest", "nomRest", "addrRest", "nbplaceRest",
            "textPresentaionRest", "horaireOuvertureRest" };

    private static String fieldToPrintAsName = "nomRest"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public Restaurant(){
       super(tableName, fields);
    }

    /**
     * Afficher dans le menu le choix d'un restaurant à l'utilisateur en affichant les noms des
     * restaurants comme choix dans le menu */
    public void getRestaurantList() {
        printTableValues(fieldToPrintAsName);
    }

    /**
     * Demander à l'utilisateur de faire un choix parmis nos restaurants */
    public void selectRestaurant() {
        while(true){
            System.out.println("\nVeuillez choisir un restaurant\n");
            Scanner sc = new Scanner(System.in);
            int userChoice = sc.nextInt();
            sc.close();
            int isChoiceSuccessfullySet = this.selectAvalue(userChoice);

            if(isChoiceSuccessfullySet == 0){
                break;
            }
            System.out.println("\nChoix incorrect\n");
        }
    }

}
