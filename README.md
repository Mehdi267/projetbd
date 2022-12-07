# Compilation

Pour compiler le projet, il faut lancer la commande `make build` dans le répertoire racine
du projet.


# Lancement de l'application

NB : *Le projet utilise une base de données MySQL*

Si vous ne disposez pas d'une base de données MySQL, vous pouvez effectuer le lancement
de l'application sur notre base de données à distance en tapant la commande :
`make run_mehdi`

Si jamais cette commande ne marche pas, alors mon adresse ip a sûrement changée.
Une solution est vous m'envoyer un mail sur mahdi.bc1@gmail.com pour que je vous donne ma
nouvelle ip adresse
Une autre solution est de hostée le serveur sql sur votre machine et vous
Connectez-vous localement sur cette machine. La démarche pour faire cela est expliquée à la fin de ce readme. 

Si vous disposez d'une instance de MySQL mais aussi de la base de données de GrenobleEat en 
local où partout ailleurs, vous pouvez lancer le programme à l'aide de la commande :
`make run local_database_name port username password address`

Remplacer par les arguments correspondants
Exemple:
make run baseGrenobleEats 3306 etudiant mypass123 79.88.105.10
make run baseGrenobleEats 3306 etudiant mypass123 localhost


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

 
 
# Hostée son propre serveur mysql:
Les prochaines commandes mises explique comment mettre le serveur sql en place 
les commandes sont inspirée de ce lien qui comporte un tutoriel simple pour télécharger mysql et le mettre en place
https://docs.rackspace.com/support/how-to/install-mysql-server-on-the-ubuntu-operating-system/
 
voici les commandes qu'il faut faire:
1/
sudo apt-get install mysql-server
Cette commande est utilisée pour telecharge le serveur mysql
 
2/
sudo su
Cette commande est utilisée pour passer au mode administrateur
 
3/
Ensuite il faut faire cette commande pour lancer le serveur mysql
sudo systemctl start mysql
 
4/
cette commande pour lancer mysql:
/usr/bin/mysql -u root -p
 
5/
Maintenant vous devez être dans mysql
il faut créer un utilisateur qui peut accéder vos bases données
voici les commandes qu'il faut lancer
 
CREATE USER 'etudiant'@'%' IDENTIFIED BY 'mypass123';
CREATE USER 'etudiant'@'localhost' IDENTIFIED BY 'mypass123';
 
GRANT ALL PRIVILEGES ON *.* TO 'etudiant'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'etudiant'@'localhost';
 
6/
Maintenant il faut juste crée la base de données:
lancez la commande :
create database baseGrenobleEats;
 
7/
Entrée dans la base données avec la commande suivante
use baseGrenobleEats
 
8/
Vous êtes dans la base de données maintenant et il faut insérer des données dedans
il faut juste ajouté les tableaux et les données
passer dans le dossier scriptsSetUpMysql
 
Copier tout le fichier Creationtable dans le terminal mysql pour crée les tables
Copier tout le fichier ajoutElement dans le terminal mysql pour créer les tables
 
Une autre solution est d'importer les données de base de données directement
du fichier qui dans le drive avec la commande exécutée cette commande dans le mode administrateur
et si jamais il vous dit qu'il faut mettre un mot de passe appuyez sur entrée:
mysql -u root -p baseGrenobleEats < baseGrenobleEats.sql

9/
Maintenant, quand vous êtes dans sql une commande qui est importante pour pouvoir faire certain
group by est la suivant :
Set sql_mode = '';

10/
Enfin il faut juste faire un : 
make build 
make run baseGrenobleEats 3306 etudiant mypass123 localhost
