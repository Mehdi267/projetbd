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
        sb.append(" from Commande");
        JavaConnectorDB.executeUpdate(sb.toString(), this, Integer.toString(comSurPlace.getNbPersonnes()), comSurPlace.getHeureArriveSurPlace());
    }

    private void createPlatDeCommande(Restaurant restaurant, Plat p){
        StringBuilder sb = new StringBuilder("INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)");
        sb.append(" SELECT max(idCommande), ? ,? ,?");
        sb.append("FROM Commande");
        for(Map.Entry<String, Integer> meal : p.getSelectedMeals().entrySet()){
            JavaConnectorDB.executeUpdate(sb.toString(), this, restaurant.getCurrentSelectedTable().get("idRest"), p.getMealFields(meal.getKey()).get("idPlat"), Integer.toString(meal.getValue()));
        }
        JavaConnectorDB.commitSavePoint(this);
    }

    private void updateCommande(){
        StringBuilder sb = new StringBuilder("UPDATE Commande SET ");
        sb.append("prixCommande = (select prixcommande from PrixCommade ");
        sb.append("WHERE idCommande = ( select * from (select max(idCommande) from Commande ) as t) ),");
        sb.append("statutCommande=");
        sb.append(" WHERE idCommande = ( select * from (select max(idCommande) from Commande ) as t );");

        JavaConnectorDB.executeUpdate(sb.toString(), this);
    }

    public void passerCommandeSurPlace(TypeCommandeRest typeCommande, Restaurant restaurant, ComSurPlace commande, Plat plat){
        createCommande(typeCommande);
        createPasserCommande(restaurant);
        createComSurPlace(commande);
        createPlatDeCommande(restaurant, plat);
        updateCommande();
    }


    public void evaluateCommande(Restaurant restaurant, String note, String comments){
        StringBuilder sb = new StringBuilder("INSERT INTO Evaluation(idCommandeEval, idRest, dateEval, heureEval, avisEval, noteEval) ");
        sb.append("SELECT max(idCommande), ?, CURDATE(), CURRENT_TIME(), ?, ?");
        sb.append(" FROM Commande;");
        JavaConnectorDB.executeUpdate(sb.toString(), this, restaurant.getCurrentSelectedTable().get("restId"), comments, note);
        JavaConnectorDB.commitSavePoint(this);
    }


}
