/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package grenobleeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import grenobleeat.database.JavaConnectorDB;
import grenobleeat.session.Connexion;

public class App {

    public static void main(String[] args) {
        System.out.println("Connexion à la base de données en cours...");
        JavaConnectorDB.initConnection();

        // TODO Draw something cool

        int codeRetournConnexion = Connexion.connexion();
    }

}
