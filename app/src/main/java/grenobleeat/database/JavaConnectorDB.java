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

        // Essai de connectionTotheDatabase
        String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
        String uname = "vanieb";
        String password = "20082001";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); // récupération du pilote de oracle
            connectionTotheDatabase = DriverManager.getConnection(url, uname, password);
            connectionTotheDatabase.setAutoCommit(false);
        } catch (SQLException e) {
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

    public static int getNombreOfPlacesLeft(String restName) {
        // TODO prepare the right query to get the number of places left in this
        // restaurant
        try {
            PreparedStatement ps = connectionTotheDatabase.prepareStatement("");

            ResultSet rs = ps.executeQuery();
            return rs.getInt("NbrPlace");

        } catch (Exception e) {
            System.out.print("Impossible de récupérer le nombre de la places restant dans le restaurant ");
            System.out.println(restName);
            System.exit(1);
        }

        return -1;
    }

    /**
     * Récupérer le contenu d'une table
     *
     * Parameters:
     *
     * @param fields - liste des champs dont on veut récupérer la valeur dans la
     *               table
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
        try {
            String query = String.format("SELECT * FROM %s", table);
            Statement st = connectionTotheDatabase.createStatement();
            ResultSet rs = st.executeQuery(query);
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

}
