package grenobleeat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


/**
 * La seule classe qui fait des requêtes à la base de données */
public class JavaConnectorDB {

    private static Connection connectionTotheDatabase;

    /*
     * Initialisation de la connexion pour une session
     *
     * @return 0 si tout s'est bien passé -1 si non
     */
    public static void initConnection() {
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
        //     System.exit(1);
        // }

        String url = "jdbc:mysql://localhost:3306/grenobleeat";
        String uname = "samuel";
        String password = "20082001";

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

    public static boolean checkIfUserExist(String email, String password) {
        try {
            PreparedStatement ps = connectionTotheDatabase
                    .prepareStatement("SELECT * FROM Client WHERE emailClient = ? AND motDePasse = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                System.out.println("Connexion réussie");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Impossible de vérifier dans la base de données");
        }
        System.out.println("Impossible de vous connectez, vous n'etes pas enregistré");
        return false;
    }

    public static boolean deleteAccount(int userId) {
        try {
            // TODO prepare the right query for account deletion and maj of the
            // other table are required
            PreparedStatement ps = connectionTotheDatabase.prepareStatement("");

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                System.out.println("\nCompte supprimé avoir avec succès");
                System.out.println("Nous espérons vous revoir bientôt\n");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Impossible d'accéder à la base de données");
        }
        return false;

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
            e.printStackTrace();
            System.out.println("Une erreur est survenue lors de la récupération des données dans la BD");
        }

        return null;
    }


    /**
     * Execute a query and build the result map */
    private static Map<Integer, Map<String, String>> executeQueryAndBuildResult(String query, String[] fields){
        try{
            Statement st = connectionTotheDatabase.createStatement();
            ResultSet rs = st.executeQuery(query);
            return buildResultMap(rs, fields);
        }catch(SQLException e){
            System.out.println("Une erreur est survenue lors de la récupération des données dans la BD");
            System.exit(1);
        }
        return null;
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
     * @return - map avec le numéro de ligne comme clé et la paire <champ_de_la_base_de_donnee, valeur> comme valeur */
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
            System.out.println("Erreur d'exécution de la requête vérifier votre connexion internet");
            System.exit(1);
        }
        return null;
    }


}
