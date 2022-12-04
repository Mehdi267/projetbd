/******************
Cette transaction donne le nombre de place restante dans un restaurant pour un temps de service donnée
ensuite on compare la valeur qu'on avec le nombre de personne mis par l'utilisateur
si le nombre donné par l'utilisateur est inférieur à cette valeur.
On annule la commande
*****************/

Begin;
SELECT placeRestante
    FROM NbrPlaceRestante 
    WHERE idRest = 1 
    and DateCommande=CURDATE() 
    and heureArriveSurPlace='midi';
COMMIT;   



