SET sql_mode = '';


CREATE USER 'etudiant'@'%' IDENTIFIED BY 'mypass123';
CREATE USER 'etudiant'@'localhost' IDENTIFIED BY 'mypass123';

GRANT ALL PRIVILEGES ON *.* TO 'etudiant'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'etudiant'@'localhost';

mysqldump -u root -p projetbd  > projet.sql

mysqldump -u root -p  baseGrenobleEats > baseGrenobleEats.sql