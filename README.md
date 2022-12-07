# Compilation

Pour compiler le projet, il faut lancer la commande `make build` dans le répertoire racine
du projet.


# Lancement de l'application

NB : *Le projet utilise une base de données MySQL*

Si vous ne disposez pas d'une base de données MySQL, vous pouvez effectuer le lancement
de l'application sur notre base de données à distance en tapant la commande :
`make run mehdi`
 
Si vous disposez d'une instance de MySQL mais aussi de la base de données de GrenobleEat en 
local où partout ailleurs, vous pouvez lancer le programme à l'aide de la commande :
`make run local_database_name port username password address`

Remplacer par les arguments correspondants
Exemple:
make baseGrenobleEats 3306 etudiant mypass123 79.88.105.10

# Documentation

Tous les fichiers de documentation du code se trouve dans le répertoire *doc*.
Dans le cas où ce fichier est inexistant, vous pouvez le générer en lançant la commande :
`make doc`

# Fonctionnement

Après la connexion vous pourrez tester le projet.
Pour cela il faudra être l'un des clients, quelques exemples de clients de la bd sont :
  
email: mehdi@gmail.com
mot de passe : 123456789
 
email: samuel@gmail.com
mot de passe : 123456789

