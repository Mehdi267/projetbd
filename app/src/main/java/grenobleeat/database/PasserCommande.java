package grenobleeat.database;

import java.sql.Savepoint;
import java.util.Map;

import grenobleeat.session.Connexion;

public class PasserCommande {

    private Savepoint savepoint;

    public Savepoint getSavepoint(){
        return this.savepoint;
    }

    public void putSavePoint(Savepoint sp){
        this.savepoint = sp;
    }

    private void createCommande(TypeCommandeRest typeCommande){
        StringBuilder sb = new StringBuilder("INSERT INTO Commande(idCommande, dateCommande,heureCommande,prixCommande, statutCommande, typeCommande )");
        sb.append(" SELECT max(idCommande)+1, CURDATE(), CURRENT_TIME(), 1, 'attente de confirmation', ? from Commande;");
        JavaConnectorDB.executeUpdate(sb.toString(), this, typeCommande.getCurrentSelectedTable().get("type"));
    }

    private void createPasserCommande(Restaurant restaurant){
        StringBuilder sb = new StringBuilder("INSERT INTO PasserCommande(idCommande, idClient, idRest) ");
        sb.append("SELECT max(idCommande),?,? FROM Commande;");
        JavaConnectorDB.executeUpdate(sb.toString(), this, Integer.toString(Connexion.getCurrentUserId()), restaurant.getCurrentSelectedTable().get("idRest"));
    }

    private void createComSurPlace(ComSurPlace comSurPlace){
        StringBuilder sb = new StringBuilder("INSERT INTO ComSurPlace(idComSurPlace, nbrPersonne, heureArriveSurPlace)");
        sb.append(" SELECT  max(idCommande), ?, ?");
        sb.append(" from Commande;");
        JavaConnectorDB.executeUpdate(sb.toString(), this, Integer.toString(comSurPlace.getNbPersonnes()), comSurPlace.getHeureArriveSurPlace());
    }

    private void createComLivraison(ComLivraison comLivraison){
        StringBuilder sb = new StringBuilder("        INSERT INTO ComLivraison(idComLivraison, adresseLivraison, textLivreur, heureLivraison ) ");
        sb.append(" SELECT  max(idCommande), ?,'i have ran into some traffic i will be there 5 min late',CURRENT_TIME() ");
        sb.append(" from Commande;");
        JavaConnectorDB.executeUpdate(sb.toString(), this, comLivraison.getAdresseUtilisateur());
    }

    private void createPlatDeCommande(Restaurant restaurant, Plat p){
        StringBuilder sb = new StringBuilder("INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)");
        sb.append(" SELECT max(idCommande), ? ,? ,?");
        sb.append(" FROM Commande");
        for(Map.Entry<String, Integer> meal : p.getSelectedMeals().entrySet()){
            JavaConnectorDB.executeUpdate(sb.toString(), this, restaurant.getCurrentSelectedTable().get("idRest"), p.getMealFields(meal.getKey()).get("idPlat"), Integer.toString(meal.getValue()));
        }
        JavaConnectorDB.commitSavePoint(this);
    }

    private void updateCommande(String statusfinal){
        StringBuilder sb = new StringBuilder("UPDATE Commande SET ");
        sb.append("prixCommande = (select prixcommande from PrixCommade ");
        sb.append(" WHERE idCommande = ( select * from (select max(idCommande) from Commande ) as t) ), ");
        sb.append(" statutCommande = ? ");
        sb.append(" WHERE idCommande = ( select * from (select max(idCommande) from Commande ) as t );");
        JavaConnectorDB.executeUpdate(sb.toString(), this, statusfinal);
        // JavaConnectorDB.executeUpdate(sb.toString(), this);
        JavaConnectorDB.commitSavePoint(this);
    }

    public void passerCommandeSurPlace(TypeCommandeRest typeCommande, Restaurant restaurant, ComSurPlace commande, Plat plat){
        createCommande(typeCommande);
        createPasserCommande(restaurant);
        createComSurPlace(commande);
        createPlatDeCommande(restaurant, plat);
        updateCommande("validee");
    }

    public void passerCommandeLivraison(TypeCommandeRest typeCommande, Restaurant restaurant, ComLivraison commande, Plat plat){
        createCommande(typeCommande);
        createPasserCommande(restaurant);
        createComLivraison(commande);
        createPlatDeCommande(restaurant, plat);
        updateCommande("en livraison");
    }

    public void passerCommandeEmporter(TypeCommandeRest typeCommande, Restaurant restaurant, Plat plat){
        createCommande(typeCommande);
        createPasserCommande(restaurant);
        createPlatDeCommande(restaurant, plat);
        updateCommande("disponible");
    }


    public void evaluateCommande(Restaurant restaurant, String note, String comments){
        StringBuilder sb = new StringBuilder("INSERT INTO Evaluation(idCommandeEval, idRest, dateEval, heureEval, avisEval, noteEval) ");
        sb.append("SELECT max(idCommande), ?, CURDATE(), CURRENT_TIME(), ?, ?");
        sb.append(" FROM Commande;");
        JavaConnectorDB.executeUpdate(sb.toString(), this, restaurant.getCurrentSelectedTable().get("idRest"), comments, note);
        JavaConnectorDB.commitSavePoint(this);
    }


}
