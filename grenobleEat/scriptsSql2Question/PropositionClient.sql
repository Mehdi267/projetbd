/******************
Cette transaction donne le nombre de place restante dans un restaurant pour un temps de service donnée
ensuite on compare la valeur qu'on avec le nombre de personne mis par l'utilisateur
si le nombre donné par l'utilisateur est inférieur à cette valeur.
On annule la commande
*****************/

Begin;

SELECT * FROM CategorieRest join Restaurant on CategorieRest.idRest = Restaurant.idRest join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest
WHERE categorie in (
SELECT categorie
FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest
			WHERE idClient = client)
and idRest in (
	SELECT idRest
		FROM NbrPlaceRestante
		WHERE NbrPlaceRestante > (SELECT nbrPersonne FROM ComSurPlace WHERE idCommande=’commandeEnCours’) )

ORDER BY noteRest DESC, nomRest ASC;


SELECT placeRestante
    FROM NbrPlaceRestante 
    WHERE idRest = 1 
    and DateCommande=CURDATE() 
    and heureArriveSurPlace='midi';
COMMIT;   



