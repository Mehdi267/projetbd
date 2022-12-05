 
/******************
Cette transaction fait des recommandations des restaurant à un client
quand sa commande sur place a été annulée
due à un nombre de place non suffisant
Dans cette exemple on fait des recommedations au client 
avec l'identifiant 3 et un nombre de place égal à 7
*****************/


SELECT Restaurant.idRest, Restaurant.nomRest, Commande.dateCommande, ComSurPlace.heureArriveSurPlace, nbPlaceRest-sum(ComSurPlace.nbrPersonne) as placeRestante


Begin;
    SELECT Restaurant.idRest, Restaurant.nomRest, noteRest FROM 
    CategorieRest 
    join Restaurant on CategorieRest.idRest = Restaurant.idRest
    join NoteMoyenneDesRest on Restaurant.idRest = NoteMoyenneDesRest.idRest
    WHERE categorie in (
            SELECT DISTINCT categorie
            FROM PasserCommande join CategorieRest on PasserCommande.idRest = CategorieRest.idRest
            WHERE idClient = 3)
            and (Restaurant.idRest in (
                SELECT idRest
                FROM NbrPlaceRestante 
                WHERE placeRestante >= 7 and dateCommande = CURDATE() 
                and (heureArriveSurPlace = 'midi' OR heureArriveSurPlace = 'soir' ))
            Or Restaurant.idRest IN (
                    select Restaurant.idRest
                    from Restaurant
                    join TypeCommandeRest on TypeCommandeRest.idRest = Restaurant.idRest
                    where Restaurant.idRest NOT IN (
                                SELECT
                                idRest from NbrPlaceRestante
                                where  dateCommande = CURDATE() 
                                and (heureArriveSurPlace = 'midi' 
                                    OR heureArriveSurPlace = 'soir'       
                                )
                    )
                    AND  TypeCommandeRest.type = 'surPlace'                                                
                ))
GROUP by Restaurant.idRest ORDER BY noteRest DESC, nomRest ASC;

COMMIT;   



