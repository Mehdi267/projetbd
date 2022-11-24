package grenobleeat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
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

        Connection connection = null; // Va nous permettre d'obtenir la connection a la base de donnees grace a nos
                                      // objets
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


    public static boolean checkIfUserExist(String email, String password){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Client WHERE emailClient = ? AND motDePasse = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
                System.out.println("Connexion réussie");
                return true;
            }
        }catch(SQLException e){
            System.out.println("Impossible de vérifier dans la base de données");
        }
        System.out.println("Impossible de vous connectez, vous n'etes pas enregistré");
        return false;
    }



    public static void printResults(ResultSet rs) {
        try {
            while (rs.next()) {
                /* Affichage dans le format : <<nomChamp>> : <<valeur>> \t .... */
                for (String fieldName : rows) {
                    try {
                        System.out.print(fieldName + " : " + rs.getString(fieldName) + "\t");
                    } catch (Exception e) {
                        System.out.print(fieldName + " : " + rs.getInt(fieldName) + "\t");
                    }
                }
            }
            System.out.println("\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
