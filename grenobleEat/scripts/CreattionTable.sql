CREATE TABLE Statut(
       statut VARCHAR(100) PRIMARY KEY CHECK(statut IN ('attente de confirmation', 'validee', 'disponible',
       'en livraison', 'annulee par le client', 'annulee par le restaurant')));

CREATE TABLE Jour(
       jour VARCHAR(30) PRIMARY KEY CHECK(jour IN ('lundi', 'mardi', 'mercredi',
       'jeudi', 'vendredi', 'samedi', 'dimanche')));

CREATE TABLE Allergene(nomAllergene VARCHAR(20) PRIMARY KEY);

CREATE TABLE Categorie(categorie VARCHAR(40) PRIMARY KEY);

CREATE TABLE Horaire(horaire VARCHAR(15) PRIMARY KEY CHECK(horaire in ('midi', 'soir', 'midi et soir')));

CREATE TABLE typeCommande(type VARCHAR(15) PRIMARY KEY CHECK(type in ('livraison', 'surPlace', 'emporte')));

CREATE TABLE Restaurant(idRest INT NOT NULL PRIMARY KEY,
        emailRest VARCHAR(30) NOT NULL UNIQUE, 
        nomRest VARCHAR(30) NOT NULL, 
        addrRest VARCHAR(100) NOT NULL, 
        nbPlaceRest INT CHECK(nbPlaceRest>=0) NOT NULL, 
        textPresentaionRest VARCHAR(100) NOT NULL, 
        horaireOuvertureRest VARCHAR(15), 
        numTel VARCHAR(30) NOT NULL, 
        FOREIGN KEY (horaireOuvertureRest) REFERENCES Horaire(horaire));

CREATE TABLE Client(idClient INT PRIMARY KEY, 
                    emailClient VARCHAR(30) UNIQUE, 
                    motDePasse VARCHAR(30), 
                    NomClient VARCHAR(30), 
                    prenomClient VARCHAR(30), 
                    addrClient VARCHAR(100));


CREATE TABLE Plat(idPlat INT, 
                idRest INT, 
                nomPlat VARCHAR(30) NOT NULL,
                descPlat VARCHAR(100) NOT NULL,
                prixPlat int CHECK(prixPlat>0) NOT NULL,
                PRIMARY KEY (idPlat, idRest),  
                FOREIGN KEY (idRest) REFERENCES Restaurant(idRest));
   
CREATE TABLE Commande(idCommande INT  PRIMARY KEY,
           dateCommande DATE NOT NULL,
           heureCommande DATE NOT NULL,
           prixCommande INT CHECK (prixCommande>0) NOT NULL,
           statutCommande VARCHAR(30),
           typeCommande VARCHAR(30),
           FOREIGN KEY (statutCommande)  REFERENCES Statut(statut),
           FOREIGN KEY (typeCommande)  REFERENCES typeCommande(type));


CREATE TABLE ComLivraison(idComLivraison INT PRIMARY KEY,
                adresseLivraison VARCHAR(100) NOT NULL,
                textLivreur VARCHAR(100) NOT NULL,
                heureLivraison DATE NOT NULL,
                FOREIGN KEY (idComLivraison) REFERENCES Commande(idCommande));


CREATE TABLE ComSurPlace(idComSurPlace INT PRIMARY KEY,
                       nbrPersonne INT NOT NULL,
                       heureArriveSurPlace VARCHAR(15),
                       FOREIGN KEY (heureArriveSurPlace) REFERENCES Horaire(horaire),
                       FOREIGN KEY (idComSurPlace) REFERENCES Commande(idCommande));


CREATE TABLE Evaluation(idCommandeEval int PRIMARY KEY,
                       idRest INT,
                       dateEval DATE NOT NULL,
                       heureEval DATE NOT NULL,
                       avisEval VARCHAR(100) NOT NULL,
                       noteEval INT CHECK(noteEval BETWEEN 0 and 5) NOT NULL,
                       FOREIGN KEY (idRest) REFERENCES Restaurant(idRest),
                       FOREIGN KEY(idCommandeEval) REFERENCES Commande(idCommande));


CREATE TABLE CategorieMere(categorie VARCHAR(40) PRIMARY KEY,
                           categorieMere VARCHAR(40),
                           FOREIGN KEY (categorie) REFERENCES Categorie(categorie),
                           FOREIGN KEY (categorieMere) REFERENCES Categorie(categorie));

CREATE TABLE TypeCommandeRest(idRest INT,
                            type VARCHAR(15),
                            PRIMARY KEY (idRest, type),
                            FOREIGN KEY (idRest) REFERENCES Restaurant(idRest),
                            FOREIGN KEY (type) REFERENCES typeCommande(type));

CREATE TABLE CategorieRest(idRest INT,
                           categorie VARCHAR(40),
                           PRIMARY KEY(idRest, categorie),
                           FOREIGN KEY (categorie) REFERENCES Categorie(categorie),
                           FOREIGN KEY (idRest) REFERENCES Restaurant(idRest));

CREATE TABLE JourResto(   idRest INT,
                        jour VARCHAR(30),
                        PRIMARY KEY(idRest, jour),
                        FOREIGN KEY (jour) REFERENCES Jour(jour),
                        FOREIGN KEY (idRest) REFERENCES Restaurant(idRest));

CREATE TABLE AllergenePlat(idPlat INT,
                           idRest INT,
                           allergene VARCHAR(20),
                           PRIMARY KEY(idPlat, idRest, allergene),
                           FOREIGN KEY(idPlat, idRest) REFERENCES Plat(idPlat, idRest),
                           FOREIGN KEY(allergene) REFERENCES Allergene(nomAllergene));

CREATE TABLE PasserCommande(idClient INT NOT NULL,
                            idCommande INT NOT NULL, 
                            idRest INT NOT NULL, 
                            FOREIGN KEY(idClient) REFERENCES Client(idClient), 
                            FOREIGN KEY(idCommande) REFERENCES Commande(idCommande), 
                            FOREIGN KEY(idRest) REFERENCES Restaurant(idRest), 
                            PRIMARY KEY(idCommande));

CREATE TABLE PlatsDeCommande(idCommande INT, 
                                idRest INT NOT NULL,
                                idPlat INT NOT NULL ,
                                Quantite INT  CHECK(Quantite>0) NOT NULL,
                                PRIMARY KEY(idCommande, idRest ,idPlat), FOREIGN KEY(idCommande) REFERENCES Commande(idCommande),
                                FOREIGN KEY(idPlat, idRest) REFERENCES Plat(idPlat, idRest) 
                                );


CREATE VIEW NoteMoyenneDesRest AS (
                SELECT idRest, AVG(noteEval) as noteRest
                FROM Evaluation
                GROUP BY idRest);

CREATE VIEW PrixCommade AS (
		SELECT SUM(prixPlat*Quantite), idCommande
		FROM Plat join PlatsDeCommande on Plat.idPlat = PlatsDeCommande.idPlat
                and Plat.idRest = PlatsDeCommande.idRest
		GROUP BY idCommande);

CREATE VIEW NbrPlaceRestante AS (
        SELECT Restaurant.idRest, Restaurant.nomRest, Commande.dateCommande, ComSurPlace.heureArriveSurPlace, nbPlaceRest-sum(ComSurPlace.nbrPersonne) as placeRestante
        FROM PasserCommande 
        JOIN ComSurPlace ON   ComSurPlace.idComSurPlace = PasserCommande.idCommande
        JOIN Restaurant ON   Restaurant.idRest = PasserCommande.idRest
        JOIN Commande ON   Commande.idCommande = PasserCommande.idCommande
        WHERE Commande.statutCommande = 'validee'
        group by Restaurant.idRest , dateCommande , heureArriveSurPlace , Restaurant.nomRest   );


SET sql_mode = '';