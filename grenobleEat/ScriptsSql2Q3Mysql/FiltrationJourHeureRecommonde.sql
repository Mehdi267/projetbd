/******************
Cette transaction permet de filtrer une liste de restaurants qui correspond au recommendation
d'un client spécifique suivant leur temps d'ouverture et le jour d'ouverture.
Dans ce cas on montre les restaurant qui sont ouvert le midi le vendredi ou samedi
*****************/


Begin;
    SELECT Restaurant.idRest , Restaurant.nomRest
	FROM Restaurant join JourResto on Restaurant.idRest = JourResto.idRest
	WHERE  Restaurant.nomRest IN  (
                    SELECT  Restaurant.nomRest
                    FROM CategorieRest 
                    join Restaurant on CategorieRest.idRest = Restaurant.idRest 
                    join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest	
                    WHERE categorie in (
                        SELECT categorie
                        FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest
                        WHERE idClient = 2
                    )
                    ORDER BY noteRest DESC, nomRest ASC)
    AND (horaireOuvertureRest = 'midi' OR horaireOuvertureRest = 'midi et soir')
    AND (JourResto.jour = 'vendredi' OR JourResto.jour = 'samedi')
    GROUP by Restaurant.idRest;
COMMIT;   


