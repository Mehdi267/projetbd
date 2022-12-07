/******************
Cette transaction permet à un utilisateur de supprimer ces données personnelles.
Ainsi ce code efface le nom et prenon et l'adresse et l'email du client et remplace 
l'identifiant du client pour qu'on puisse stocké les évalution et les commandes réalisées.
Dans l'exemple suivant on supprimera les données du client avec l'identifiant égal à1  
*****************/

Begin;

INSERT INTO Client(idClient)
SELECT  max(idClient)+1   
from Client;

update PasserCommande 
SET idClient = (SELECT MAX(idClient) FROM Client)
where idClient = 1;

DELETE FROM Client WHERE idClient = 1;

COMMIT;