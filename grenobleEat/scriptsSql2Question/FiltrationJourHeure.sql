/******************
Cette transaction permet de filtrer une liste de restaurants suivant 
leur temps d'ouverture et le jour d'ouverture.
Dans ce cas on montre les restaurant qui sont ouvert le midi le vendredi ou samedi
*****************/

Begin;
SELECT Restaurant.idRest, Restaurant.nomRest
	FROM Restaurant join JourResto on Restaurant.idRest = JourResto.idRest
	WHERE horaireOuvertureRest = 'midi' OR horaireOuvertureRest = 'midi et soir' 
    AND JourResto.jour = 'vendredi' OR JourResto.jour = 'samedi'
    GROUP by Restaurant.idRest;
COMMIT;   

