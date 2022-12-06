package grenobleeat.session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import grenobleeat.session.Connexion;
import grenobleeat.database.JavaConnectorDB;

public class DeleteAccount {
    /**
     * Account deletion dialogue with user
     *
     * @return 0 - if the account was successfully deleted
     * @return 1 - if the account deletion was aborted */
    public static int deleteAccount(){
        System.out.println("\nVoulez-vous vraiment continuer ?\n");
        System.out.println("1. Oui\n2. Non");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String choix = br.readLine();
            switch(choix){
                case "1":
                    if (JavaConnectorDB.deleteAccount(Connexion.getCurrentUserId())){
                        System.out.println("\nMerci d'avoir été fait parti de nos clients\n");
                    }
                    else{
                        System.out.println("\n il y avait un probléme lors du effacement de votre compte");
                        System.out.println(" essayez plus tard s'il vous plait ! Merci pour votre comprehension!");
                    }
                    return 0;   
                case "2":
                    System.out.println("\nL'aventure continue...\n");
                    return 1;
            }
        }catch(Exception e){
           System.out.println("\nImpossible de supprimer cet compte\n");
           System.exit(-1);
        }

        return 1;
    }
}
