
/******************
Cette transaction retourne les restaurants d'une catégorie spécifique,
Par exemple ici la catégorie pris est 'cuisine chinoise'.
Les restaurants sont ordonnée suivant leur évaluation
et si ils ont les mêmes évolutions on les ordonne suivant l’ordre alphabétique de leur nom.
*****************/

Begin;
SELECT Restaurant.idRest, Restaurant.nomRest 
FROM CategorieRest 
join Restaurant on CategorieRest.idRest = Restaurant.idRest 
join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest
WHERE categorie = 'cuisine chinoise'
ORDER BY noteRest DESC, nomRest ASC;
COMMIT;    