package grenobleeat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.ClassNotFoundException;

/* Cette classe va me permettre de lancer directement mes requetes SQL dans mon code */

public class JavaConnectorDB {

    private static Connection connection;

    /*
     * Initialisation de la connexion pour une session
     *
     * @return 0 si tout s'est bien passé -1 si non
     */
    public static void initConnection() {
        /*
         * Creation de tous les objets qui vont nous permettre d'obtenir la connection
         * url : l'url complete de connection - uname : nom d'utilisateur utilise pour
         * acceder a la base de donnees password : mot de passe de l'utilisateur query :
         * Syntaxe(ou instruction) a envoyer a la base de donnees
         *
         */

        // Essai de connection
        String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
        String uname = "vanieb";
        String password = "20082001";

        Statement stmt = null; // Permet d'envoyer la syntaxe a la base de donnees en fonction de son type
                               // grace a des methodes

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); // récupération du pilote de oracle
            connection = DriverManager.getConnection(url, uname, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
            System.exit(1);
        }

        System.out.println("Connexion réussie");

        System.out.println("\nBienvenue chez GrenobleEat\n");

    }

    // fermeture de la connection
    public static void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("Fermeture de la connexion impossible");
            System.exit(1);
        }
    }

    public static boolean checkIfUserExist(String email, String password) {
        try {
            PreparedStatement ps = connection
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
            PreparedStatement ps = connection.prepareStatement("");

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
            PreparedStatement ps = connection.prepareStatement("");

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
     */
    public static List<Map<String, String>> printTableElementList(String table, String[] fields) {
        try {
            String query = String.format("SELECT * FROM %s", table);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Map<String, String> lineValues = new HashMap<>();
            List<Map<String, String>> results = new ArrayList<>();
            while (rs.next()) {
                for (String field : fields) {
                    try {
                        lineValues.put(field, rs.getString(field));
                    } catch (Exception e) {
                        lineValues.put(field, Integer.toString(rs.getInt(field)));
                    }
                }
                results.add(lineValues);
                lineValues = new HashMap<>();
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue lors de la récupération des données dans la BD");
        }

        return null;
    }

}
