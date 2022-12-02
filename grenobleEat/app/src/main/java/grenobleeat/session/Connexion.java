package grenobleeat.session;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import grenobleeat.database.JavaConnectorDB;

public class Connexion {
    public static int connexion() {
        System.out.println("\nConnexion en cours...\n");
        while (true) {
            try {
                System.out.println("Entrez votre mail");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String userName = br.readLine();
                userName = userName.strip();
                System.out.println("Entrez votre mot de passe");
                String password = br.readLine();
                password = password.strip();
                if (userName.length() <= 0 || password.length() <= 0) {
                    System.out.println("Email ou mot de passe non défini");
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
