package grenobleeat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.lang.ClassNotFoundException;

/* Cette classe va me permettre de lancer directement mes requetes SQL dans mon code */

public class JavaConnectorDB {

    private static String rows[];
    private static String Client[] = { "idClient", "emailClient", "motDePasse", "NomClient", "PrenomClient",
            "addrClient" };

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

    /*--------- Mode d'emploi de cette fonction -----------

    choix 0 : correspond a une requete de mise a jour de la base de donnees(Ajout d'une nouvelle information)
    choix 1 : correspond a une requete de consultation de la base de donnees

    */
    public static ResultSet JavaConnectorCommunicate(int choice, String table) throws SQLException {
        Statement stmt = null;
        try {
            if (connection != null) {
                stmt = connection.createStatement(); // Prevenir la base de donnes de l'envoi d'une syntaxe
                if (choice == 0) {
                    stmt.executeUpdate(query); // executeUpdate methode pour une requete de mise a jour de la BD; va
                                               // apporter une modification
                    System.out.println("Requete execute");

                    return null;
                } else if (choice == 1) {
                    changeRows(table);
                    ResultSet rs = null;
                    rs = stmt.executeQuery(query); // executeQuery methode pour une requete de consultation de la BD
                    return rs;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close(); // Dire a la base de donnes qu'elle ne recevra plus d'instructions
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void changeRows(String nomTable) {
        if (nomTable.equals("Client")) {
            rows = Client;
        }
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
