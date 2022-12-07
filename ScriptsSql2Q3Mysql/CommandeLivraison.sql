/******************
Cette transaction permet de faire une commande livraison et mentionne les differents contraintes contextuelles qui 
faut prendre ne compte.
*****************/

Begin;

/*
La première étape qu'on veut faire une commande est de vérifier que cette commande est possible
Ainsi il faut vérifier que le resto est ouvert dans ce jour  et il traite des commandes de livraison
Ces contraintes ont été traitées en java.
*/

/*Le prixCommande est égal à 1 mais eventuelement on changera cette valeur suivant les plat choisi*/
INSERT INTO Commande(idCommande, dateCommande,heureCommande,prixCommande, statutCommande, typeCommande )
SELECT  max(idCommande)+1, CURDATE(), CURRENT_TIME(), 1, 'attente de confirmation', 'livraison' 
from Commande;

/*Dans cette partie on relis la commande à la personne qui l'a passé et le restaurant dans le quelle 
La commande a été faite;*/

INSERT INTO PasserCommande(idCommande, idClient, idRest)
SELECT  max(idCommande),3,2
from Commande;

/*Dans une commande de livraison, on doit ajouter un instance 
danc ComLivraison qui contient l'id de la commande, adresseLivraison, textLivreur
et l'horaire de livraison;*/


INSERT INTO ComLivraison(idComLivraison, adresseLivraison, textLivreur, heureLivraison )
SELECT  max(idCommande),"adresse de livraison donnée par l'utilisateur" ,'i have ran into some traffic i will be there 5 min late',CURRENT_TIME()
from Commande;


/*On doit ajouter les plats de la commande
--On soit aussi d'assurer que le restaurant correspand au restaurent de passer commande;*/

INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 1 ,1 ,3
from Commande;                   


INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 1 ,2 ,5
from Commande;                   

INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 1 ,3 ,5
from Commande; 

/*--Ensuite si l'utilsateur et le resto valide la commande, 
on fait l'update de la commande du status de la commande*/

update Commande set
prixCommande = (select prixcommande from PrixCommade 
                where idCommande = ( select * from (select max(idCommande) from Commande ) as t) ),
statutCommande = 'en livraison'
where idCommande = ( 
    select * from (select max(idCommande) from Commande ) as t
);  

/* Si la commande n'a pas été annulée. On peut faire une evalution*/
INSERT INTO Evaluation(idCommandeEval, idRest, dateEval, heureEval, avisEval, noteEval)
SELECT  max(idCommande), 2, CURDATE(), CURRENT_TIME(), "good food", 5 
from Commande;


COMMIT;   
