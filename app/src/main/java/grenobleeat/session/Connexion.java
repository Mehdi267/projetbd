package grenobleeat.session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import grenobleeat.database.JavaConnectorDB;

public class Connexion {
    public static int connexion() {
        System.out.println("\nConnexion en cours...\n");
        System.out.println("Entrez votre nom d'utilisateur");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            String userName = st.nextToken();
            if (userName.length() <= 0) {
                System.out.println("Vous devez entrer un nom d'utilisateur");
            }
            ResultSet rs = JavaConnectorDB.JavaConnectorCommunicate(0, "client",
                    "CREATE TABLE Client(id INT, nom VARCHAR(10))");
            if (rs != null) {
                return 0; // Connexion réussie
            } else {
                return -1; // Echec de la connexion
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
