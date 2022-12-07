package grenobleeat.database;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import grenobleeat.App;
import grenobleeat.session.Connexion;

/**
 * Table restaurant de la base de données
 */
public class Restaurant extends Table {

    private static String tableName = "Restaurant";
    private static String[] fields = { "idRest", "nomRest", "nbPlaceRest" };

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
    public void getRestaurantList(Categorie c) {

        String currentCategory = c.getCurrentSelectedTable().get("categorie");

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest, Restaurant.nomRest, Restaurant.nbPlaceRest FROM CategorieRest");
        sb.append(" JOIN Restaurant on CategorieRest.idRest = Restaurant.idRest");
        sb.append(" JOIN NoteMoyenneDesRest ON Restaurant.idRest = NoteMoyenneDesRest.idRest");
        sb.append(" WHERE categorie = ?");
        sb.append(" ORDER BY noteRest DESC, nomRest ASC");
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, currentCategory));
        printTableValues(fieldToPrintAsName);
    }

         
    /**
     * Demander à l'utilisateur de faire un choix parmis nos restaurants */
    public void selectRestaurant() {
        getUserChoice("\nVeuillez choisir un restaurant\n");
    }
    
    /**
    * Cette fonction affiche les restaurants recommandées
    * pour le client actuellement connecté dans la base de données
    * les restaurants recommandées correspond aux restaurant des catégories
    * préférées de l'utilisateur ordonnées par leur évaluations
     */
    public void getRecommendedRestaurents(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest,Restaurant.nomRest, Restaurant.nbPlaceRest ");
        sb.append("FROM CategorieRest ");
        sb.append("join Restaurant on CategorieRest.idRest = Restaurant.idRest ");
        sb.append("join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest ");
        sb.append("WHERE categorie in (");
        sb.append("SELECT categorie ");
        sb.append("FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest ");
        sb.append("WHERE idClient = ?");
        sb.append(")");
        sb.append(" GROUP BY Restaurant.idRest ");
        sb.append("ORDER BY noteRest DESC, nomRest ASC;");
        //System.out.println(sb.toString());
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, Integer.toString(Connexion.getCurrentUserId())));
        printTableValues(fieldToPrintAsName);
    }

    /**
    * Cette fonction affiche les restaurants recommandées filtrée
    * selon l'horaire et les jours choisis par l'utilisateur
    * pour le client actuellement connecté dans la base de données
     */
    public void getRecommendedRestaurentsFilter(){    
        ArrayList<String> horaire = gethoraire();
        ArrayList<String> jour = getDays();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest, Restaurant.nomRest, Restaurant.nbPlaceRest ");
        sb.append("FROM Restaurant join JourResto on Restaurant.idRest = JourResto.idRest ");
        sb.append("WHERE  Restaurant.nomRest IN ( ");
        sb.append("SELECT Restaurant.nomRest ");
        sb.append(" FROM CategorieRest ");
        sb.append("join Restaurant on CategorieRest.idRest = Restaurant.idRest ");
        sb.append("join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest ");
        sb.append("WHERE categorie in (");
        sb.append("SELECT categorie ");
        sb.append("FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest ");
        sb.append("WHERE idClient = ? ");
        sb.append(")");
        sb.append(" ORDER BY noteRest DESC, nomRest ASC)");
        sb.append("AND ( ");
        if (horaire != null){
            for (String iterhoraire : horaire){
                sb.append(" horaireOuvertureRest ='"+ iterhoraire+"' OR ");
            }
        }
        sb.append(" horaireOuvertureRest = 'midi et soir')");
        if (jour.size() != 0){
            sb.append(" AND ( ");
            int iter = 0;
            for (String iterjour : jour){
                iter ++;
                if (iter == jour.size()){
                    sb.append(" JourResto.jour = '"+ iterjour+"' ");}
                else{
                   sb.append(" JourResto.jour = '"+ iterjour+"' OR ");}
            }
            sb.append(" ) ");
        }
        sb.append(" GROUP by Restaurant.idRest;");
        //System.out.println(sb.toString());
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields, Integer.toString(Connexion.getCurrentUserId())));
        printTableValues(fieldToPrintAsName);
    }


    /**
     * Cette fonction affiche les restaurants d'une catégorie spécifique recommandées filtrée
     * selon l'horaire et les jours choisis par l'utilisateur
     * pour le client actuellement connecté dans la base de données
     * @param categorie la catégorie des restaurants qu'on va filtrer
     */
    public void getCategorieAuChoixRestaurentsFilter(String categorie){
        ArrayList<String> horaire = gethoraire();
        ArrayList<String> jour = getDays();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Restaurant.idRest, Restaurant.nomRest, Restaurant.nbPlaceRest ");
        sb.append("FROM Restaurant join JourResto on Restaurant.idRest = JourResto.idRest ");
        sb.append("WHERE  Restaurant.nomRest IN  ( ");
        sb.append("SELECT Restaurant.nomRest ");
        sb.append("FROM CategorieRest ");
        sb.append("join Restaurant on CategorieRest.idRest = Restaurant.idRest ");
        sb.append("join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest ");
        sb.append("WHERE categorie = '"+categorie + "' ");
        sb.append("ORDER BY noteRest DESC, nomRest ASC) ");
        sb.append("AND ( ");
        if (horaire != null){
            for (String iterhoraire : horaire){
                sb.append("horaireOuvertureRest =' "+ iterhoraire+"' OR ");
            }
        }
        sb.append("horaireOuvertureRest = 'midi et soir') ");
        if (jour.size() != 0 ){
            sb.append(" AND ( ");
            int iter = 0;
            for (String iterjour : jour){
                iter ++;
                if (iter == jour.size()){
                    sb.append("JourResto.jour = '"+ iterjour+"' ");}
                else{
                   sb.append("JourResto.jour = '"+ iterjour+"' OR ");}
            }
            sb.append(" ) ");
        }
        sb.append(" GROUP by Restaurant.idRest;");
        //System.out.println(sb.toString());
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields));
        printTableValues(fieldToPrintAsName);
    }

    /**
     * Cette fonction affiche les restaurants proposé à un client
     * quand il essaye de faire une commande sur place mais le nombre de place dans 
     * la commande est supérieur au nombre de place restante du restaurant au quel on essaye de faire une commande
     * @param nbPlace nombre de place dans la reservation
     */
    public void getPropositionRestaurant(int nbPlace){
        StringBuilder sb = new StringBuilder();
        sb.append("        SELECT Restaurant.idRest, Restaurant.nomRest, Restaurant.nbPlaceRest FROM ");
        sb.append("        CategorieRest ");
        sb.append("        join Restaurant on CategorieRest.idRest = Restaurant.idRest");
        sb.append("        join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest");
        sb.append("        WHERE categorie in (");
        sb.append("                SELECT DISTINCT categorie");
        sb.append("                FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest");
        sb.append("                WHERE idClient = "+Connexion.getCurrentUserId()+")");
        sb.append("                and (Restaurant.idRest in (");
        sb.append("                    SELECT idRest");
        sb.append("                    FROM NbrPlaceRestante ");
        sb.append("                    WHERE placeRestante >= "+nbPlace+" and dateCommande = CURDATE() ");
        sb.append("                    and (heureArriveSurPlace = 'midi' OR heureArriveSurPlace = 'soir' ))");
        sb.append("                Or Restaurant.idRest IN (");
        sb.append("                        select Restaurant.idRest");
        sb.append("                        from Restaurant");
        sb.append("                        join TypeCommandeRest on TypeCommandeRest.idRest = Restaurant.idRest");
        sb.append("                        where Restaurant.idRest NOT IN (");
        sb.append("                                    SELECT");
        sb.append("                                    idRest from NbrPlaceRestante");
        sb.append("                                    where  dateCommande = CURDATE() ");
        sb.append("                                    and (heureArriveSurPlace = 'midi' ");
        sb.append("                                        OR heureArriveSurPlace = 'soir'       ");
        sb.append("                                    )");
        sb.append("                        )");
        sb.append("                        AND Restaurant.nbPlaceRest >= "+nbPlace);
        sb.append("                        AND  TypeCommandeRest.type = 'surPlace'                          ");
        sb.append("                    ))");
        sb.append("    GROUP by Restaurant.idRest ORDER BY noteRest DESC, nomRest ASC;");
        //System.out.println(sb.toString());
        setBdContents(JavaConnectorDB.executeQueryAndBuildResult(sb.toString(), fields));
        printTableValues(fieldToPrintAsName);
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
    public int getPlacesLeft(String heureArrivee){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT placeRestante ");
        sb.append("FROM NbrPlaceRestante ");
        sb.append("WHERE idRest=? ");
        sb.append("AND DateCommande=CURDATE() ");
        sb.append("AND heureArriveSurPlace=?;");

        int nombreDePlaces = -1;
        String restId = this.getCurrentSelectedTable().get(fields[0]); // en supposant que le premier élément est la clé primaire
        ResultSet rs = JavaConnectorDB.executeCustomQuery(sb.toString(), restId, heureArrivee);

        try{
            if(rs.next()){
                nombreDePlaces = rs.getInt("placeRestante");
            }else{
                nombreDePlaces = Integer.parseInt(this.getCurrentSelectedTable().get("nbPlaceRest"));
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Impossible de récupérer le nombre de places restantes dans ce restaurant");
        }
        return nombreDePlaces;
    }

    /**
     * Cette fonction permet à l'utilisateur de choisir les jours dont les quelles
     * les restaurents sont ouverts 
     * @return la liste des jours que l'utilisateur a chosi
     */
    public ArrayList<String> getDays(){   
        StringBuilder sb = new StringBuilder();
        sb.append("choisissez les jours\n"); 
        for(Map.Entry<Integer, String> entry : daysHashMap.entrySet() ){
            sb.append( entry.getKey() + "->" + entry.getValue() +"\n" );
        }
        sb.append("8. appuyez sur 8 quand vous avez termine\n");
        System.out.println(sb.toString());
        HashSet<String> listDaysSet = new HashSet<>();
        boolean isSuccessful = false;
        App.sc = new Scanner(System.in);
        while(!isSuccessful){
            try{
                int entryuser = App.sc.nextInt();
                while ( entryuser != 8){  
                    if (entryuser <= 7 && entryuser >= 1 ){
                        listDaysSet.add(daysHashMap.get(entryuser));
                    }
                    System.out.println("Donner un jour ou appuyez sur 8 pour quitter "); 
                    App.sc = new Scanner(System.in);
                    entryuser = App.sc.nextInt();
                }
                isSuccessful = true;
            }
            catch(Exception e){
                System.out.println("La entre doit être entre 1 et 7");
            }
        }
        return new ArrayList<>(listDaysSet);
        
    }
    
    /**
     * Cette fonction permet à l'utilisateur de choisir l'horaire dont le quelle
     * les restaurents ouverts
     * @return la liste des horaires
     */
    public ArrayList<String> gethoraire(){   
        StringBuilder sb = new StringBuilder();
        sb.append("choisissez l'horaire\n");  
        sb.append("1 midi\n");
        sb.append("2. soir \n");
        sb.append("3. n'importe\n");
        System.out.println(sb.toString());
        ArrayList<String> horaire = new ArrayList<String>();
        boolean isSuccessful = false;
        while(!isSuccessful){
            try{
                App.sc = new Scanner(System.in);
                int entryuser = App.sc.nextInt(); 
                if (entryuser == 1){horaire.add("midi"); return horaire;}
                if (entryuser == 2){horaire.add("soir"); return horaire;}
                if (entryuser == 3){horaire.add("soir"); horaire.add("midi"); return horaire;}
            }
            catch(Exception e){
                //System.out.println("La entre doit être entre 1 et 3");
            }
        }
        return null;
        
    }

}
