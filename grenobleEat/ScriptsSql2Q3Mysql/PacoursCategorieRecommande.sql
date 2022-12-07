
/******************
Cette transaction retourne les restaurants des catégorie recommandées i.e les catégories
dont le client avait déjà recommandées avant. 
(Dans cette exemple cas on affiche les recommandations de l'utilisateur 1)
Les restaurants sont ordonnée suivant leur évaluation et
si il on les même évolution on les ordonne suivant l’ordre alphabétique de leur nom
*****************/
Begin;
SELECT Restaurant.idRest, Restaurant.nomRest, noteRest
FROM CategorieRest 
join Restaurant on CategorieRest.idRest = Restaurant.idRest 
join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest	
WHERE categorie in (
    SELECT categorie
    FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest
    WHERE idClient = 2
)
ORDER BY noteRest DESC, nomRest ASC;
COMMIT;