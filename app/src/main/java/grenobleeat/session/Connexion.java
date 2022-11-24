package grenobleeat.session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import grenobleeat.database.JavaConnectorDB;

public class Connexion {
    public static int connexion() {
        System.out.println("\nConnexion en cours...\n");
        while (true) {
            System.out.println("Entrez votre nom d'utilisateur");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st = new StringTokenizer(br.readLine());
                String userName = st.nextToken();
                String password = st.nextToken();
                if (userName.length() <= 0 || password.length() <= 0) {
                    System.out.println("Nom d'utilisateur ou mot de passe non défini");
                    continue;
                }


                boolean isConnected = JavaConnectorDB.checkIfUserExist(userName, password);
                if(isConnected){
                    StringBuilder sb = new StringBuilder().append("\nBienvenue chez Grenoble Eat ");
                    sb.append(userName);
                    System.out.println(sb.toString());
                    return 0;
                }else{
                    System.out.println("Mauvais mot de passe");
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }
    }

}
