package grenobleeat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import java.sql.*;


/**
 * La seule classe qui fait des requêtes à la base de données */
public class JavaConnectorDB {

    private static Connection connectionTotheDatabase;

    /*
     * Initialisation de la connexion pour une session
     *
     * @return 0 si tout s'est bien passé -1 si non
     */
    public static void initConnection(String[] args) {
        /*
         * Creation de tous les objets qui vont nous permettre d'obtenir la connectionTotheDatabase
         * url : l'url complete de connectionTotheDatabase - uname : nom d'utilisateur utilise pour
         * acceder a la base de donnees password : mot de passe de l'utilisateur query :
         * Syntaxe(ou instruction) a envoyer a la base de donnees
         *
         */

        // TODO Uncomment this part to do the connection to the ensimag database
        // Essai de connectionTotheDatabase
        // String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
        // String uname = "vanieb";
        // String password = "20082001";

        // try {
        //     DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); // récupération du pilote de oracle
        //     connectionTotheDatabase = DriverManager.getConnection(url, uname, password);
        //     connectionTotheDatabase.setAutoCommit(false);
        // } catch (SQLException e) {
        //     System.out.println("Erreur de connexion à la base de données");
        //     System.exit(1)
        // }

        String url = String.format("jdbc:mysql://%s:%s/%s", args[4], args[1], args[0]);
        String uname = args[2];
        String password = args[3];


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionTotheDatabase = DriverManager.getConnection(url, uname, password);
            connectionTotheDatabase.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erreur de connexion à la base de données");
            System.exit(1);
        }

        System.out.println("Connexion réussie");

        System.out.println("\nBienvenue chez GrenobleEat\n");

    }

    // fermeture de la connectionTotheDatabase
    public static void closeConnection() {
        try {
            if (connectionTotheDatabase != null)
                connectionTotheDatabase.close();
        } catch (SQLException e) {
            System.out.println("Fermeture de la connexion impossible");
            System.exit(1);
        }
    }

    public static int checkIfUserExist(String email, String password) {

        try {
            PreparedStatement ps = connectionTotheDatabase.prepareStatement("SELECT * FROM Client WHERE emailClient = ? AND motDePasse = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                System.out.println("Connexion réussie");
                rs.next();
                int idClient = rs.getInt("idClient");
                ps.close();
                rs.close();
                return idClient;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Impossible de vous connectez, vous n'etes pas enregistré");
        return -1;

    }

    /**
     * Transform the results took from database to a map with key values for each line */
    private static Map<Integer, Map<String, String>> buildResultMap(ResultSet rs, String[] fields){
        try {
            Map<String, String> lineValues = new HashMap<>();
            Map<Integer, Map<String, String>> results = new HashMap<>();

            int currentLineNumber = 1;
            while (rs.next()) {
                for (String field : fields) {
                    try {
                        lineValues.put(field, rs.getString(field));
                    } catch (Exception e) {
                        lineValues.put(field, Integer.toString(rs.getInt(field)));
                    }
                }
                results.put(currentLineNumber, lineValues);
                lineValues = new HashMap<>();

                ++currentLineNumber;
            }

            return results;
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la récupération des données dans la BD");
        }

        return null;
    }


    /**
     * Exécute une requete préparée (ces requêtes contiennent des "?")
     *
     * Paramaters:
     * @param query - la requête préparée que l'on veut exécuter
     * @param fields - Les champs de la table dont on veut garder les résultats pour pouvoir les utiliser plus tard
     *
     *
     * Return:
     * @return - map qui représente les colonnes de la table avec leur valeur.
     *
     * Example:
     * <pre>
     * String query = "SELECT * FROM Restaurant";
     * String[] fields = {"idRest", "nomRest", "emailRest"};
     * Map&lt;Integer, Map&lt;String, String&gt;&gt; bdContents = executeQueryAndBuildResult(query, fields);
     * </pre>
     *  */
    public static Map<Integer, Map<String, String>> executeQueryAndBuildResult(String query, String[] fields){
        ResultSet rs = executeCustomQuery(query);
        return buildResultMap(rs, fields);
    }

    /**
     * Exécute une requete préparée (ces requêtes contiennent des "?")
     *
     * Paramaters:
     * @param query - la requête préparée que l'on veut exécuter
     * @param fields - Les champs de la table dont on veut garder les résultats pour pouvoir les utiliser plus tard
     * @param values - les valeurs que l'on veut pour chaque "?" dans l'ordre de leur appirition
     *
     *
     * Return:
     * @return - map qui représente les colonnes de la table avec leur valeur.
     *
     * Example:
     * <pre>
     * String query = "SELECT * FROM Restaurant WHERE idRest = ?";
     * String[] fields = {"idRest", "nomRest", "emailRest"};
     * Map&lt;Integer, Map&lt;String, String&gt;&gt; bdContents = executeQueryAndBuildResult(query, fields, 2);
     * </pre>
     *  */
    public static Map<Integer, Map<String, String>> executeQueryAndBuildResult(String query, String[] fields, String ...values){
        ResultSet rs = executeCustomQuery(query, values);
        return buildResultMap(rs, fields);
    }

    /**
     * Récupérer le contenu d'une table
     *
     * Parameters:
     *
     * @param fields - liste des champs dont on veut récupérer la valeur dans la
     *               table
     * @param table - table name to fetch from
     *
     *
     * Return:
     *
     * @return List de Map contenant chaque ligne du tableau sous forme de clé -
     *         valeur
     *         Chaque ligne de la list est une ligne de la BD, et les paires clés valeurs sont les noms
     *         des champs dans la table et leur valeurs
     */
    public static Map<Integer, Map<String, String>> fetchDataFromDB(String table, String[] fields) {
        String query = String.format("SELECT * FROM %s", table);
        return executeQueryAndBuildResult(query, fields);
    }


    /**
     * Récupère les données dans une table de la base de données selon une condition
     * de valeur sur un champ.
     *
     * Parameters:
     * @param table - table dans laquelle on récupère les données
     * @param fieldsToPrint - valeurs devant apparaître dans les résultats
     * @param fieldTofilter - champ utilisé dans la condition
     * @param valueTofilterWith - valeur que le champ filtré doit avoir dans les résultats
     *
     * Return:
     * @return - map avec le numéro de ligne comme clé et la paire &lt;champ_de_la_base_de_donnee, valeur&gt; comme valeur */
    public static Map<Integer, Map<String, String>> fetchDataFromDB(String table, String[] fieldsToPrint, String fieldTofilter, String valueTofilterWith){
        String query = String.format("SELECT * FROM %s WHERE %s = %s", table, fieldTofilter, valueTofilterWith);
        return executeQueryAndBuildResult(query, fieldsToPrint);
    }


    /**
     * Exécuter une requête personnalisé, une requête différente de toutes les autres
     *
     * Parameters:
     * @param query - La requête à exécuter
     *
     * Return:
     * @return - le résultat de la requête dans un objet de type {@link ResultSet} */
    public static ResultSet executeCustomQuery(String query){
        try{
            Statement st = connectionTotheDatabase.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }catch(SQLException e){
            System.out.println("Erreur d'exécution de la requête vérifier votre connexion internet");
            System.exit(1);
        }
        return null;
    }

    /**
     * Exécuter une requête préparée ( {@link PreparedStatement} ) personnalisé, une requête différente de toutes les autres
     *
     * Parameters:
     * @param query - La requête à exécuter
     * @param values - les valeurs que les différents paramètres de la requêtre préparée
     *
     * Return:
     * @return - le résultat de la requête dans un objet de type {@link ResultSet} */
    public static ResultSet executeCustomQuery(String query, String ...values){
        try{
            PreparedStatement ps = connectionTotheDatabase.prepareStatement(query);
            int value;
            for(int i=0; i<values.length; i++){
                try{
                    value = Integer.parseInt(values[i]);
                    ps.setInt(i+1, value);
                }catch(Exception e){
                    ps.setString(i+1, values[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            return rs;
        }catch(SQLException e){
            System.out.println("Erreur d'exécution de la requête vérifier votre connexion à la base de données");
            System.exit(1);
        }
        return null;
    }


    /**
     *  */
    public static void executeUpdate(String query, PasserCommande target){

        try{
            if(target.getSavepoint() == null){
                target.putSavePoint(connectionTotheDatabase.setSavepoint());
            }

            Statement st = connectionTotheDatabase.createStatement();
            int numberOfRows = st.executeUpdate(query);
            System.out.println(st);
            if(numberOfRows <= 0){
                connectionTotheDatabase.rollback(target.getSavepoint());
            }
        }catch (SQLException e){
            try{
                connectionTotheDatabase.rollback(target.getSavepoint());
            }catch(Exception r){}
        }

    }

    /**
     * Exécuter une requête préparée de mise à jour sur la base de données
     * Une requête de mise à jour est une requête d'insertion ou update, elle affecte les données de la base
     * de données.
     *
     * @param query - requête préparée (avec les points d'interrogation)
     * @param target - l'instance de commande à créer
     * @param values - les valeurs que l'on veut pour les variables dans l'ordre d'apparition des points d'interrogation */
    public static void executeUpdate(String query, PasserCommande target, String ...values){
        try{
            if(target.getSavepoint() == null){
                target.putSavePoint(connectionTotheDatabase.setSavepoint());
            }
            PreparedStatement ps = connectionTotheDatabase.prepareStatement(query);
            int value;
            for(int i=0; i<values.length; i++){
                try{
                    value = Integer.parseInt(values[i]);
                    ps.setInt(i+1, value);
                }catch(Exception e){
                    ps.setString(i+1, values[i]);
                }
            }
            // System.out.println(ps);
            int numberOfrows = ps.executeUpdate();
            if(numberOfrows <= 0){
                connectionTotheDatabase.rollback(target.getSavepoint());
            }
        }catch(SQLException e){
            // TODO change message to vérifier la bd instead of the connection
            System.out.println("Erreur d'exécution de la requête vérifier votre connexion internet");
            try{
                connectionTotheDatabase.rollback(target.getSavepoint());
            }catch(Exception r){}
            System.exit(1);
        }
    }

    public static void commitSavePoint(PasserCommande target){
        try{
            if(target.getSavepoint() != null){
                connectionTotheDatabase.commit();
            }
        }catch(SQLException e){
            System.out.println("Aucun savepoint n'a été enregistré");
            System.exit(1);
        }
    }


    public static boolean deleteAccount(int userId) {
        Savepoint savePoint = null;
        PreparedStatement statementToAddNewInstance = null;
        PreparedStatement statementToDeleteAllMentions = null;
        PreparedStatement statementToDeleteClient = null;
        try {
            savePoint = connectionTotheDatabase.setSavepoint();
            StringBuilder queryAddMax = new StringBuilder();
            queryAddMax.append(" INSERT INTO Client(idClient) ");
            queryAddMax.append(" SELECT  max(idClient)+1   ");
            queryAddMax.append(" from Client ; ");
            statementToAddNewInstance = connectionTotheDatabase.prepareStatement(queryAddMax.toString());
            int output = statementToAddNewInstance.executeUpdate();

            StringBuilder queryDeleteAll = new StringBuilder();
            queryDeleteAll.append(" update PasserCommande ");
            queryDeleteAll.append(" SET idClient = (SELECT MAX(idClient) FROM Client)  ");
            queryDeleteAll.append(" where idClient = ?; ");
            statementToDeleteAllMentions = connectionTotheDatabase.prepareStatement(queryDeleteAll.toString());
            statementToDeleteAllMentions.setInt(1, userId);
            int output2 =statementToDeleteAllMentions.executeUpdate();

            StringBuilder queryDeleteClient = new StringBuilder();
            queryDeleteClient.append(" DELETE FROM Client WHERE idClient = ?; ");
            statementToDeleteClient = connectionTotheDatabase.prepareStatement(queryDeleteClient.toString());
            statementToDeleteClient.setInt(1, userId);
            statementToDeleteClient.executeUpdate();
            connectionTotheDatabase.commit();
        }
        catch (SQLException e) {
            System.out.println("Impossible d'accéder à la base de données");
            if( connectionTotheDatabase != null ) {
                try { if (savePoint != null) {
                    System.out.println("Rolling back ....");
                    connectionTotheDatabase.rollback(savePoint);} } // rollback on error
                catch( SQLException exp ) { }
            }
            return false;
        }
        finally{
            if (statementToAddNewInstance != null) { try { statementToAddNewInstance.close(); } catch (SQLException e) {} statementToAddNewInstance = null; } 
            if (statementToDeleteAllMentions != null) { try { statementToDeleteAllMentions.close(); } catch (SQLException e) {} statementToDeleteAllMentions = null; } 
            if (statementToDeleteClient != null) { try { statementToDeleteClient.close(); } catch (SQLException e) {} statementToDeleteClient = null; } 
        }
        return true;

    }

    /**
     * Cette fonction parcourt le tableau categorieRest
     * ensuite elle prend chaque restaurant et la catégorie associée et cherche
     * si cette catégorie a des parents, si elle en a on insère dans le tableau 
     * categorieRest l’id de ce restaurant et la catégorie parent
     */
    public static boolean setUpCategorie() {
        Savepoint savePoint = null;
        PreparedStatement statementToAddRestoAndCategorie = null;
        ResultSet rs = null;
        try { 
            savePoint = connectionTotheDatabase.setSavepoint();
            StringBuilder querygetResto = new StringBuilder();
            querygetResto.append(" Select * from CategorieRest;");
            
            statementToAddRestoAndCategorie = connectionTotheDatabase.prepareStatement(querygetResto.toString());
            rs = statementToAddRestoAndCategorie.executeQuery();
            while (rs.next()){
                int idRest = rs.getInt("idRest");
                String nomCategorie = rs.getString("categorie");
                addCategorieRecursif(idRest, nomCategorie);
            }
            connectionTotheDatabase.commit();
        }
        catch (SQLException e) {
            System.out.println("Impossible d'accéder à la base de données");
            if( connectionTotheDatabase != null ) {
                try { if (savePoint != null) {
                    System.out.println("Rolling back ....");
                    connectionTotheDatabase.rollback(savePoint);} } // rollback on error
                catch( SQLException exp ) { }
            }
            return false;
        }
        finally{
            if (statementToAddRestoAndCategorie != null) { try { statementToAddRestoAndCategorie.close(); } catch (SQLException e) {} statementToAddRestoAndCategorie = null; } 
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null; } 
        }
        return true;

    }

    /**
     * Cette fonction insère la catégorie mère d’une catégorie à catégorieRest 
     * pour un restaurant spécifique  
     * @param idRest
     * @param nomCategorie
     */
    public static void addCategorieRecursif(int idRest, String nomCategorie) {
        PreparedStatement statementToGetCategorieMere = null;
        PreparedStatement statementToInsertCategorie = null;
        ResultSet rs = null;
        try {
            StringBuilder querygetResto = new StringBuilder();
            querygetResto.append(" Select categorieMere from CategorieMere where categorie = '"+nomCategorie+"';");
            statementToGetCategorieMere = connectionTotheDatabase.prepareStatement(querygetResto.toString());
            
            System.out.println("query = " + statementToGetCategorieMere);
            rs = statementToGetCategorieMere.executeQuery();
            while (rs.next()){
                String nomCategorieMere = rs.getString("categorieMere");
                
                StringBuilder queryAddMax = new StringBuilder();
                queryAddMax.append(" INSERT INTO CategorieRest VALUES ("+idRest+", '"+nomCategorieMere+"'); ");
                statementToInsertCategorie = connectionTotheDatabase.prepareStatement(queryAddMax.toString());
                
                System.out.println("query = " + statementToInsertCategorie);
                addCategorieRecursif(idRest, nomCategorieMere);
                int output = statementToInsertCategorie.executeUpdate();
            }
        }
        catch (SQLException e) {
            System.out.println("Categorie is already present");
        }
        finally{
            if (statementToGetCategorieMere != null) { try { statementToGetCategorieMere.close(); } catch (SQLException e) {} statementToGetCategorieMere = null; } 
            if (statementToInsertCategorie != null) { try { statementToInsertCategorie.close(); } catch (SQLException e) {} statementToInsertCategorie = null; } 
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null; } 
        }
    }


    /**
     * Permet de récupérer l'adresse du client
     * @param idUser - id de l'utilisateur */
    public static String getUserAdress(int idUser) {
        PreparedStatement statementToCLientAdress = null;
        ResultSet rs = null;
        String adresseClient = null;
        try {
            StringBuilder querygetResto = new StringBuilder();
            querygetResto.append(" select addrClient from Client where idCLient = "+idUser+";");
            statementToCLientAdress = connectionTotheDatabase.prepareStatement(querygetResto.toString());
            System.out.println(statementToCLientAdress);
            rs = statementToCLientAdress.executeQuery();
            while (rs.next()){
                adresseClient = rs.getString("addrClient");
            }
        }
        catch (SQLException e) {
            System.out.println("Problem when getting user id");
        }
        finally{
            if (statementToCLientAdress != null) { try { statementToCLientAdress.close(); } catch (SQLException e) {} statementToCLientAdress = null; } 
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null; } 
        }
        return adresseClient;
    }

    
}
