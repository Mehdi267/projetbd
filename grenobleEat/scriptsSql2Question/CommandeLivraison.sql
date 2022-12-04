/******************
Cette transaction permet de faire une commande livraison et mentionne les differents contraintes contextuelles qui 
-- faut prendre ne compte.
*****************/

Begin;

/*La premiére étape qu'on veut faire une commande est de verifier que cette commande est possible
--Ainsi il faut vérifier que le resto est ouvert dans ce jour  et il traite des commandes de livraison.;*/

INSERT INTO Commande(idCommande)
SELECT  max(idCommande)+1   
from Commande;

/*Le prixCommande est égal à 1 mais eventuelement on changera cette valeur suivant les palt recommandées;*/

update Commande set
dateCommande = CURDATE(),
heureCommande = CURRENT_TIME(),
prixCommande = 1,
statutCommande = 'attente de confirmation',
typeCommande = 'livraison' 
where idCommande = ( 
    select * from (select max(idCommande) from Commande ) as t
);

/*Dans cette partie on relis la commande à la personne qui l'a passé et le restaurant dans le quelle 
La commande a été faite;*/

INSERT INTO PasserCommande(idCommande, idClient, idRest)
SELECT  max(idCommande),3,2
from Commande;

/*Dans une commande de livraison, on doit ajouter un instance 
--danc ComLivraison qui contient l'id de la commande, adresseLivraison, textLivreur
--et l'horaire de livraison;*/


INSERT INTO ComLivraison(idComLivraison)
SELECT  max(idCommande)
from Commande;

/*L'adresse mise doit correspandre à l'adresse du client*/

update ComLivraison set
adresseLivraison = "adresse de livraison donnée par l'utilisateur" ,
textLivreur =  'i have ran into some traffic i will be there 5 min late',
heureLivraison = CURRENT_TIME()
where idComLivraison = ( 
    select * from (select max(idCommande) from Commande ) as t
);

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
/*Quand la commande est valide, on peut faire une evalution;*/


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
