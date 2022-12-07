/******************
Cette transaction permet de faire une commande sur place et mentionne les differents contraintes contextuelles qui 
-- faut prendre ne compte.
*****************/

Begin;
/*
La premiére étape qu'on veut faire une commande est de verifier que cette commande est possible
Ainsi il faut vérifier que le resto est ouvert dans ce jour et horaire 
et il faut chosir une type de commande que le restaurant servit.
Ces contraintes ont été traitées en java.
*/

/*Le prixCommande est égal à 1 mais eventuelement on changera cette valeur suivant les plat choisi*/
INSERT INTO Commande(idCommande, dateCommande,heureCommande,prixCommande, statutCommande, typeCommande )
SELECT  max(idCommande)+1, CURDATE(), CURRENT_TIME(), 1, 'attente de confirmation', 'surPlace' 
from Commande;



/*Dans cette partie on relis la commande à la personne qui l'a passé 
et le restaurant dans le quelle la commande a été faite*/

INSERT INTO PasserCommande(idCommande, idClient, idRest)
SELECT  max(idCommande),3,2
from Commande;

/*
On vérifie que le nombre de place est suffisant, si oui on crée un instance de ComSurPlace
sinon on annule la commande
*/

/*Dans une commande surplace, on doit ajouter un instance 
danc ComSurPlace qui contient l'id de la commande, le nombre de personne
et l'horaire du repas*/

INSERT INTO ComSurPlace(idComSurPlace, nbrPersonne, heureArriveSurPlace)
SELECT  max(idCommande), 5, 'midi'
from Commande;

/*On doit ajouter les plats de la commande
--On doit aussi d'assurer que le restaurant correspand au restaurent de la commande */

INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 2 ,1 ,3
from Commande;                   


INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 2 ,2 ,5
from Commande;                   

INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 2 ,3 ,5
from Commande; 



/*Ensuite si l'utilsateur et le resto valide la commande, on fait l'update de du status de la commande
et on fait une évalution si on veut. ;*/

update Commande set
prixCommande = (select prixcommande from PrixCommade 
                where idCommande = ( select * from (select max(idCommande) from Commande ) as t) ),
statutCommande = 'validee'
where idCommande = ( 
    select * from (select max(idCommande) from Commande ) as t
);  

/* Si la commande n'a pas été annulée. On peut faire une evalution*/
INSERT INTO Evaluation(idCommandeEval, idRest, dateEval, heureEval, avisEval, noteEval)
SELECT  max(idCommande), 2, CURDATE(), CURRENT_TIME(), "good food", 5 
from Commande;



COMMIT;   
