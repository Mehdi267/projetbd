/******************
Cette transaction permet de connecter un utilisateur.
Elle vérifie que le email et mot de passe entrée sont dans la base de données
*****************/

Begin;
SELECT * FROM Client WHERE emailClient = 'mehdi@gmail.com' AND motDePasse = '123456789';
COMMIT;   

