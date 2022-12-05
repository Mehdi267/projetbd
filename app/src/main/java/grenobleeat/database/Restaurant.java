package grenobleeat.database;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grenobleeat.session.Connexion;

/**
 * Table restaurant de la base de données
 */
public class Restaurant extends Table {

    private static String tableName = "Restaurant";
    private static String[] fields = { "idRest", "nomRest" };

    private static String fieldToPrintAsName = "nomRest"; // le champ a afficher dans le menu comme choix pour l'utilisateur

    public static Map<Integer,String> daysHashMap = new HashMap<Integer, String>();

    public Restaurant(){
        super(tableName, fields);
        setupdays();
    }

    void setupdays(){
        daysHashMap.put(1,"lundi");  
        daysHashMap.put(2,"mardi");    
        daysHashMap.put(3,"mercredi");   
        daysHashMap.put(4,"jeudi");   
        daysHashMap.put(5,"vendredi");   
        daysHashMap.put(6,"samedi");  
        daysHashMap.put(7,"dimanche");   
    }

    /**
     * Afficher dans le menu le choix d'un restaurant à l'utilisateur en affichant les noms des
     * restaurants comme choix dans le menu */
    public void getRestaurantList() {

        String currentCategory = Categorie.getCurrentSelectedTable().get("categorie");

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest, Restaurant.nomRest FROM CategorieRest");
        sb.append(" JOIN Restaurant on CategorieRest.idRest = Restaurant.idRest");
        sb.append(" JOIN NoteMoyenneDesRest ON Restaurant.idRest = NoteMoyenneDesRest.idRest");
        sb.append(" WHERE categorie = ?");
        sb.append("ORDER BY noteRest DESC, nomRest ASC");

        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, currentCategory));
        printTableValues(fieldToPrintAsName);
    }

         
    /**
     * Demander à l'utilisateur de faire un choix parmis nos restaurants */
    public void selectRestaurant() {
        getUserChoice("\nVeuillez choisir un restaurant\n");
    }
    
    public void getRecommendedRestaurents(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest,Restaurant.nomRest");
        sb.append(",Restaurant.textPresentaionRest,Restaurant.horaireOuvertureRest ");
        sb.append("FROM CategorieRest ");
        sb.append("join Restaurant on CategorieRest.idRest = Restaurant.idRest ");
        sb.append("join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest ");
        sb.append("WHERE categorie in (");
        sb.append("SELECT categorie ");
        sb.append("FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest ");
        sb.append("WHERE idClient = ?");
        sb.append("))");
        sb.append("ORDER BY noteRest DESC, nomRest ASC;");
        JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, Integer.toString(Connexion.getCurrentUserId()));
    }


    public void getRecommendedRestaurentsFilterRecommended(){    
        Arraylist<String> horaire = gethoraire();
        Arraylist<String> jour = getDays();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest, Restaurant.nomRest");
        sb.append(",Restaurant.textPresentaionRest,Restaurant.horaireOuvertureRest");
        sb.append("WHERE Restaurant.nomRest IN FROM Restaurant join JourResto on Restaurant.idRest = JourResto.idRest;");
        sb.append("SELECT Restaurant.nomRest ");
        sb.append("join Restaurant on CategorieRest.idRest = Restaurant.idRest ");
        sb.append("join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest ");
        sb.append("WHERE categorie in (");
        sb.append("SELECT categorie ");
        sb.append("FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest ");
        sb.append("WHERE idClient = ? ");
        sb.append("))");
        sb.append("ORDER BY noteRest DESC, nomRest ASC;)");
        sb.append("AND ( );");

        if (horaire != null){
            for (String iterhoraire : horaire){
                sb.append("horaireOuvertureRest ='"+ iterhoraire+"' OR");
            }
        }
        sb.append("horaireOuvertureRest = 'midi et soir')");
        if (jour != null){
            sb.append(" AND ( ");
            int iter = 0;
            for (String iterjour : jour){
                iter ++;
                if (iter == jour.size()){
                    sb.append("JourResto.jour = '"+ iterjour+"' OR");}
                else{
                   sb.append("JourResto.jour = '"+ iterjour+"'");}
            }
            sb.append(" ) ");
        }
        sb.append(" GROUP by Restaurant.idRest;");
        JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, Integer.toString(Connexion.getCurrentUserId()));
    }


    public void getRecommendedRestaurentsFilterRecommended(String categorie){
        Arraylist<String> horaire = gethoraire();
        Arraylist<String> jour = getDays();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest,Restaurant.emailRest,Restaurant.nomRest,Restaurant.addrRest,Restaurant.nbplaceRest");
        sb.append("),Restaurant.textPresentaionRest,Restaurant.horaireOuvertureRest");
        sb.append("FROM Restaurant join JourResto on Restaurant.idRest = JourResto.idRest");
        sb.append("WHERE  Restaurant.nomRest IN  (");
        sb.append("SELECT Restaurant.nomRest ");
        sb.append("FROM CategorieRest ");
        sb.append("join Restaurant on CategorieRest.idRest = Restaurant.idRest ");
        sb.append("join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest");
        sb.append("WHERE categorie = 'Fast food'");
        sb.append("ORDER BY noteRest DESC, nomRest ASC)");
        sb.append("ORDER BY noteRest DESC, nomRest ASC;)");
        sb.append("AND ( ");

        if (horaire != null){
            for (String iterhoraire : horaire){
                sb.append("horaireOuvertureRest ='"+ iterhoraire+"' OR");
            }
        }
        sb.append("horaireOuvertureRest = 'midi et soir')");
        if (jour != null){
            sb.append(" AND ( ");
            int iter = 0;
            for (String iterjour : jour){
                iter ++;
                if (iter == jour.size()){
                    sb.append("JourResto.jour = '"+ iterjour+"' OR");}
                else{
                   sb.append("JourResto.jour = '"+ iterjour+"'");}
            }
            sb.append(" ) ");
        }
        sb.append(" GROUP by Restaurant.idRest;");
        JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, Integer.toString(Connexion.getCurrentUserId()));
    }





    /**
     * Récupérer le nombre de places restant dans le restaurant qui a été sélectionné
     *
     * Parameters:
     * @param heureArrivee - heure d'arrivée sur place du client
     *
     * Return:
     * @return - le nombre de places
     * @return - -1 si impossible de récupérer le nombre de place */
    public static int getPlacesLeft(String heureArrivee){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT placeRestante ");
        sb.append("FROM NbrPlaceRestante ");
        sb.append("WHERE idRest=? ");
        sb.append("AND DateCommande=CURDATE() ");
        sb.append("AND heureArriveSurPlace=?;");

        int nombreDePlaces = -1;
        String restId = Restaurant.getCurrentSelectedTable().get(fields[0]); // en supposant que le premier élément est la clé primaire
        ResultSet rs = JavaConnectorDB.executeCustomQuery(sb.toString(), restId, heureArrivee);
        try{
            System.out.println(nombreDePlaces);
            nombreDePlaces = rs.getInt("placeRestante");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Impossible de récupérer le nombre de places restantes dans ce restaurant");
            // TODO maybe exit the system ? or go back
        }
        return nombreDePlaces;
    }

    public Arraylist<String> getDays(){   
        sb.append("choisissez les jours\n"); 
        for(Map.Entry<Integer, String> entry : listDays ){
            sb.append( entry.getKey() + "->" + entry.getValue() +"\n" );
        }
        sb.append("8. appuyez sur 8 qaund vous avez termine\n");
        System.out.println(sb.toString());
        HashSet<String> listDaysSet = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        boolean isSuccessful = false;
        while(!isSuccessful){
            try{
                int entryuser = sc.nextInt(); 
                while ( entryuser != 8){  
                    if (entryuser <= 7 && entryuser >= 1 ){
                        listDaysSet.add(listDays.get(entryuser));
                    }
                    entryuser = sc.nextInt();
                }
                isSuccessful = true;
            }
            catch(Exception e){
                System.out.println("La entre doit être entre 1 et 7");
            }
        }
        return new ArrayList<>(listDays);
        
    }

    public Arraylist<String> gethoraire(){   
        sb.append("choisissez l'horaire\n");  
        sb.append("1 midi\n");
        sb.append("2. soir \n");
        sb.append("3. n'importe\n");
        System.out.println(sb.toString());
        Arraylist<String> horaire = Arraylist<String>();
        Scanner sc = new Scanner(System.in);
        boolean isSuccessful = false;
        while(!isSuccessful){
            try{
                int entryuser = sc.nextInt(); 
                if (entryuser == 1){horaire.add("midi"); return horaire;}
                if (entryuser == 2){horaire.add("soir"); return horaire;}
                if (entryuser == 3){horaire.add("soir"); horaire.add("midi"); return horaire;}
                System.out.println("La entre doit être entre 1 et 3");
            }
            catch(Exception e){
                System.out.println("La entre doit être entre 1 et 3");
            }
        }
        return null;
        
    }

}
