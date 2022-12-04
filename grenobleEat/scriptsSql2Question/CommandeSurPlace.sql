/******************
Cette transaction permet de faire une commande sur place et mentionne les differents contraintes contextuelles qui 
-- faut prendre ne compte.
*****************/

Begin;
/*
La premiére étape qu'on veut faire une commande est de verifier que cette commande est possible
Ainsi il faut vérifier que le resto est ouvert dans ce jour et horaire 
et il faut chosir une type de commande que le restaurant servit.
*/

INSERT INTO Commande(idCommande)
SELECT  max(idCommande)+1   
from Commande;

/*Le prixCommande est égal à 1 mais eventuelement on changera cette valeur suivant les palt recommandées*/

update Commande set
dateCommande = CURDATE(),
heureCommande = CURRENT_TIME(),
prixCommande = 1,
statutCommande = 'attente de confirmation',
typeCommande = 'surPlace' 
where idCommande = ( 
    select * from (select max(idCommande) from Commande ) as t
);

/*Dans cette partie on relis la commande à la personne qui l'a passé et le restaurant dans le quelle 
--La commande a été faite*/

INSERT INTO PasserCommande(idCommande, idClient, idRest)
SELECT  max(idCommande),3,1
from Commande;

/*
On vérifie que le nombre de place est suffisant, si oui on crée un instance de ComSurPlace
sinon on annule la commande
*/
/*Dans une commande surplace, on doit ajouter un instance 
--danc ComSurPlace qui contient l'id de la commande, le nombre de personne
--et l'horaire du repas*/

INSERT INTO ComSurPlace(idComSurPlace)
SELECT  max(idCommande)
from Commande;

update ComSurPlace set
nbrPersonne = 5 ,
heureArriveSurPlace = 'midi' 
where idComSurPlace = ( 
    select * from (select max(idCommande) from Commande ) as t
);

/*On doit ajouter les plats de la commande
--On soit aussi d'assurer que le restaurant correspand au restaurent de passer commande */

INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 1 ,1 ,3
from Commande;                   


INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 1 ,2 ,5
from Commande;                   

INSERT INTO PlatsDeCommande(idCommande, idRest, idPlat, Quantite)
SELECT  max(idCommande), 1 ,3 ,5
from Commande; 



/*Ensuite si l'utilsateur et le resto valide la commande, on fait l'update de la commande du status de la commande
-- et on fait une évalution si on veut. ;*/

update Commande set
prixCommande = (select prixcommande from PrixCommade 
                where idCommande = ( select * from (select max(idCommande) from Commande ) as t) ),
statutCommande = 'validee'
where idCommande = ( 
    select * from (select max(idCommande) from Commande ) as t
);  

/*--Quand la commande est valide, on peut faire une evalution*/

INSERT INTO Evaluation(idCommandeEval)
SELECT  max(idCommande)
from Commande;

update Evaluation set
idRest = 2,
dateEval = CURDATE(),
heureEval = CURRENT_TIME(),
avisEval = "good food",
noteEval = 4
where idCommandeEval = ( 
    select * from (select max(idCommande) from Commande ) as t
);  


COMMIT;   
