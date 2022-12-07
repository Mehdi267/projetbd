/******************
Cette transaction permet de filtrer une liste de restaurants suivant 
leur temps d'ouverture et le jour d'ouverture.
Dans ce cas on montre les restaurant qui sont ouvert le midi le vendredi ou samedi
*****************/

Begin;
SELECT Restaurant.nomRest, Plat.nomPlat, AllergenePlat.allergene
	FROM Restaurant join Plat on Restaurant.idRest = Plat.idRest
    join AllergenePlat on
     Plat.idRest = AllergenePlat.idRest AND Plat.idPlat = AllergenePlat.idPlat
	WHERE Restaurant.idRest = 2 ;
COMMIT;   

