
INSERT INTO Horaire VALUES ( 'midi'); 
INSERT INTO Horaire VALUES ( 'soir');
INSERT INTO Horaire VALUES ( 'midi et soir'); 

INSERT INTO HoraireCommande VALUES ( 'midi'); 
INSERT INTO HoraireCommande VALUES ( 'soir');

INSERT INTO typeCommande VALUES ( 'livraison'); 
INSERT INTO typeCommande VALUES ( 'surPlace');
INSERT INTO typeCommande VALUES ( 'emporte'); 


INSERT INTO Statut VALUES ( 'attente de confirmation'); 
INSERT INTO Statut VALUES ( 'validee');
INSERT INTO Statut VALUES ( 'disponible'); 
INSERT INTO Statut VALUES (  'en livraison'); 
INSERT INTO Statut VALUES ( 'annulee par le client');
INSERT INTO Statut VALUES ( 'annulee par le restaurant');


INSERT INTO Client VALUES (1, 'mehdi@gmail.com', '123456789', 'Kamoun', 'Mehdi', '2 rue de la liberté, Grenoble');
INSERT INTO Client VALUES (2, 'iheb@gmail.com', 'iheb45688', 'Karoui', 'Iheb', '3 rue de la liberté, Grenoble');
INSERT INTO Client VALUES (3, 'sami@gmail.com', 'sami123456789', 'Trabelsi', 'Sami', '1 rue de la liberté, Grenoble');
INSERT INTO Client VALUES (4, 'samuel@gmail.com', '123456789', 'Vanie', 'Samuel', '4 rue Stalingrad, Grenoble');

INSERT INTO Restaurant VALUES (1, 'bestkebab@gmail.com', 'OriginalKebab', '2 rue de  Victor Hugo, Grenoble', 20, 'For the love of delicious kebab.', 'midi et soir') ;
INSERT INTO Restaurant VALUES (2, 'FASTFOOD@gmail.com', 'FastFood', '5 rue de Louis Pasteur, Grenoble', 50, 'Low cost. High quality.', 'midi et soir') ;
INSERT INTO Restaurant VALUES (3, 'Pizza@gmail.com', 'World of Pizza', '6 rue de Général Leclerc, Grenoble', 10, 'We are always here to serve you the best pizza in the world.', 'midi') ;
INSERT INTO Restaurant VALUES (4, 'Blue_Diamond@gmail.com', 'Blue Diamond', '2 rue boulevard, Grenoble', 15, 'High class restaurant for high class people', 'soir') ;
INSERT INTO Restaurant VALUES (5, 'Dragonfly_China@gmail.com', 'Dragonfly China', '17 rue de Stalingrad, Grenoble', 30, 'Every bowl of noodles is a surprise.', 'soir') ;
INSERT INTO Restaurant VALUES (6, 'Tunisian_magic@gmail.com', 'Tunisian magic', '17 rue de Victoire, Grenoble', 50, 'Best food that you will ever eat.', 'soir') ;


INSERT INTO CATEGOIREREST VALUES (1, 'cuisine turque');
INSERT INTO CATEGOIREREST VALUES (1, 'Fast food');

INSERT INTO CATEGOIREREST VALUES (2, 'Fast food');

INSERT INTO CATEGOIREREST VALUES (3, 'cuisine italienne');
INSERT INTO CATEGOIREREST VALUES (3, 'cuisine italienne');

INSERT INTO CATEGOIREREST VALUES (4, 'cuisine régionale');
INSERT INTO CATEGOIREREST VALUES (4, 'cuisines royales');
INSERT INTO CATEGOIREREST VALUES (4, 'cuisine savoyarde');
INSERT INTO CATEGOIREREST VALUES (4, 'cuisine à la bière');


INSERT INTO CATEGOIREREST VALUES (5, 'cuisine chinoise');



INSERT INTO TYPECOMMANDEREST VALUES (1, 'livraison');
INSERT INTO TYPECOMMANDEREST VALUES (1, 'surPlace');
INSERT INTO TYPECOMMANDEREST VALUES (1, 'emporte');

INSERT INTO TYPECOMMANDEREST VALUES (2, 'livraison');
INSERT INTO TYPECOMMANDEREST VALUES (2, 'surPlace');
INSERT INTO TYPECOMMANDEREST VALUES (2, 'emporte');

INSERT INTO TYPECOMMANDEREST VALUES (3, 'surPlace');
INSERT INTO TYPECOMMANDEREST VALUES (3, 'emporte');

INSERT INTO TYPECOMMANDEREST VALUES (4, 'surPlace');

INSERT INTO TYPECOMMANDEREST VALUES (5, 'surPlace');

INSERT INTO TYPECOMMANDEREST VALUES (6, 'surPlace');


INSERT INTO CATEGORIE VALUES ('cuisine savoyarde');
INSERT INTO CATEGORIE VALUES ('cuisine dauphinoise');
INSERT INTO CATEGORIE VALUES ('cuisine des alpes');
INSERT INTO CATEGORIE VALUES ('cuisine régionale');


INSERT INTO CATEGORIEMERE VALUES ('cuisine savoyarde', 'cuisine des alpes');
INSERT INTO CATEGORIEMERE VALUES ('cuisine dauphinoise', 'cuisine des alpes');
INSERT INTO CATEGORIEMERE VALUES ('cuisine des alpes', 'cuisine régionale');


INSERT INTO CATEGORIE VALUES ('cuisine française');
INSERT INTO CATEGORIE VALUES ('cuisines royales');
INSERT INTO CATEGORIE VALUES ('cuisine basque');
INSERT INTO CATEGORIE VALUES ('cuisine méditerranéenne');

INSERT INTO CATEGORIEMERE VALUES ('cuisines royales','cuisine parisienne');
INSERT INTO CATEGORIEMERE VALUES ('cuisine basque', 'cuisine du nord');
INSERT INTO CATEGORIEMERE VALUES ('cuisine méditerranéenne', 'cuisine française');


INSERT INTO CATEGORIE VALUES ('Fast food');


INSERT INTO CATEGORIE VALUES ('cuisine du nord');
INSERT INTO CATEGORIE VALUES ('cuisine au beurre');
INSERT INTO CATEGORIE VALUES ('cuisine à la bière');
INSERT INTO CATEGORIE VALUES ('cuisine de la mer');
INSERT INTO CATEGORIE VALUES ('cuisine parisienne');

INSERT INTO CATEGORIEMERE VALUES ('cuisine parisienne', 'cuisine du nord');
INSERT INTO CATEGORIEMERE VALUES ('cuisine à la bière', 'cuisine du nord');
INSERT INTO CATEGORIEMERE VALUES ('cuisine de la mer', 'cuisine du nord');
INSERT INTO CATEGORIEMERE VALUES ('cuisine au beurre', 'cuisine du nord');

INSERT INTO CATEGORIE VALUES ('cuisine française');

INSERT INTO CATEGORIEMERE VALUES ('cuisine du nord', 'cuisine française');
INSERT INTO CATEGORIEMERE VALUES ('cuisine régionale', 'cuisine française');

INSERT INTO CATEGORIE VALUES ('cuisine chinoise');
INSERT INTO CATEGORIE VALUES ('cuisine turque');
INSERT INTO CATEGORIE VALUES ('cuisine italienne');
INSERT INTO CATEGORIE VALUES ('cuisine arabe');

INSERT INTO CATEGORIEREST VALUES (1, 'cuisine française');





INSERT INTO PLAT VALUES (1, 1, 'Menu naan kebab', 'Naan fromage, kebab, crudités et 2 sauces au choix. Servi avec frites et 1 boisson au choix.', 10) ;
INSERT INTO PLAT VALUES (2, 1, 'Maxi cheese naan kebab', 'Double viande, crudités et 2 sauces au choix.', 9) ;
INSERT INTO PLAT VALUES (3, 1, 'classique kebab', 'Kebab, pain, crudités et 2 sauces au choix.', 8) ;
INSERT INTO PLAT VALUES (4, 1, 'Mozzarella stick', 'apéritifs ou hors-dœuvre faits de fromage pané', 15) ;


INSERT INTO PLAT VALUES (1, 2, '2 MENUS + 2 EXTRAS', '2 menus aux choix parmi une sélection de menus phares BURGER KING', 23) ;
INSERT INTO PLAT VALUES (2, 2, '3 MENUS + 3 EXTRAS', '3 menus aux choix parmi une sélection de menus phares BURGER KING', 30) ;
INSERT INTO PLAT VALUES (3, 2, 'Tendercrisp Cheese & Bacon', 'LIrrésistible Tendercrisp et son poulet pané croustillant dans sa version cheese & bacon', 11);
INSERT INTO PLAT VALUES (4, 2, 'Double Cheese Bacon XXL', 'Deux viandes de bœuf grillées à la flamme, des tranches de cheddar fondu et du bacon', 12) ;

INSERT INTO PLAT VALUES (1, 3, '5 Fromages', 'Base sauce tomate, mozzarella, chèvre, bleu, raclette et emmental.', 15);
INSERT INTO PLAT VALUES (2, 3, 'Norvégienne', 'Base crème fraîche, mozzarella, saumon fumé, tomates cerises et citron.', 16) ;
INSERT INTO PLAT VALUES (3, 3, 'Tartiflette', 'Base crème fraîche, mozzarella, pommes de terre, lardons, oignons et raclette.', 14) ;
INSERT INTO PLAT VALUES (4, 3, '3 Fondus', 'Base crème fraîche, mozzarella, lardons grillés', 16) ;

INSERT INTO PLAT VALUES (1, 4, 'Poke Sucré Saumon Wasabi', 'BRiz vinaigré Protéine : saumon mariné avec une sauce soja sucrée Légume : carotte cranberries ,', 60);
INSERT INTO PLAT VALUES (2, 4, 'Poke végétarien(vegan)', 'BDuo de céréales au curcuma "quinoa et riz curcuma" Protéine tofu grillé', 70);
INSERT INTO PLAT VALUES (3, 4, 'Pastels boeuf x2', 'Beignets frits fourrés au boeuf. 2 pièces', 55);
INSERT INTO PLAT VALUES (4, 4, 'Alokos extra', 'Bananes plantains frites, légèrement croquantes à lextérieur', 66);

INSERT INTO PLAT VALUES (1, 5, 'Nouilles Sautées aux légumes', 'Nouilles sautées aux légumes', 20);
INSERT INTO PLAT VALUES (2, 5, 'Raviolis aux crevettes Ha Kao (4 pièces)', 'Raviolis aux crevettes Ha Kao (4 pièces)',  5);
INSERT INTO PLAT VALUES (3, 5, 'M1', '1 plat et 1 accompagnement au choix.', 15);
INSERT INTO PLAT VALUES (4, 5, 'BO2 Bobun Au Bœuf', 'Grande salade, vermicelle, pousse de soja, concombre, carotte, bœuf, cacahuète', 18);

INSERT INTO PLAT VALUES (1, 6, 'Nougat glacé', 'Glace nougat façon Baba Louni', 10);
INSERT INTO PLAT VALUES (2, 6, 'Sandwich Babayon', 'Bouscoutou + glace Zabayon', 11);
INSERT INTO PLAT VALUES (3, 6, 'Bambaloni', 'Beignet Tunisien façon Sidi Bou Said', 7);
INSERT INTO PLAT VALUES (4, 6, 'Boule au miel', 'La fameuse boule au miel tunisienne', 6);


INSERT INTO Allergene VALUES ('Lait de vache');
INSERT INTO Allergene VALUES ('Soja');
INSERT INTO Allergene VALUES ('protéines');  
INSERT INTO Allergene VALUES ('Œufs');
INSERT INTO Allergene VALUES ('Poissons');    
INSERT INTO Allergene VALUES ('Arachides');
INSERT INTO Allergene VALUES ('Fruits à coques');
INSERT INTO Allergene VALUES ('Céleri');
INSERT INTO Allergene VALUES ('Moutarde');
INSERT INTO Allergene VALUES ('Lupin');
INSERT INTO Allergene VALUES ('Mollusques');
INSERT INTO Allergene VALUES ('Noix');
INSERT INTO Allergene VALUES ('Carotte');
INSERT INTO Allergene VALUES ('Gingembre');
INSERT INTO Allergene VALUES ('Banane');
INSERT INTO Allergene VALUES ('Gingembre');
INSERT INTO Allergene VALUES ('Avoine');
INSERT INTO Allergene VALUES ('Chocolat');
INSERT INTO Allergene VALUES ('Epinard');
INSERT INTO Allergene VALUES ('Oignon');
INSERT INTO Allergene VALUES ('Thon');





INSERT INTO AllergenePlat VALUES (1,1, 'Lait de vache');
INSERT INTO AllergenePlat VALUES (1,1, 'Soja');
INSERT INTO AllergenePlat VALUES (1,2, 'Poissons');
INSERT INTO AllergenePlat VALUES (1,4, 'Fruits à coques');

INSERT INTO AllergenePlat VALUES (2,1, 'Thon');
INSERT INTO AllergenePlat VALUES (2,2, 'Oignon');
INSERT INTO AllergenePlat VALUES (2,2, 'Epinard');
INSERT INTO AllergenePlat VALUES (2,3, 'Chocolat');
INSERT INTO AllergenePlat VALUES (2,4, 'Gingembre');

INSERT INTO AllergenePlat VALUES (3,1, 'Banane');
INSERT INTO AllergenePlat VALUES (3,2, 'Gingembre');
INSERT INTO AllergenePlat VALUES (3,2, 'Carotte');
INSERT INTO AllergenePlat VALUES (3,3, 'Noix');
INSERT INTO AllergenePlat VALUES (3,3, 'Mollusques');

INSERT INTO AllergenePlat VALUES (4,1, 'Lupin');
INSERT INTO AllergenePlat VALUES (4,2, 'Moutarde');
INSERT INTO AllergenePlat VALUES (4,2, 'Céleri');   
INSERT INTO AllergenePlat VALUES (4,2, 'Fruits à coques');
INSERT INTO AllergenePlat VALUES (4,3, 'Arachides');
INSERT INTO AllergenePlat VALUES (4,3, 'protéines');
INSERT INTO AllergenePlat VALUES (4,4, 'Soja');


-- oracle
INSERT INTO Commande VALUES (1, DATE '2022-11-13',  TO_DATE('2022/11/13 8:30:25', 'YYYY/MM/DD HH:MI:SS'), 30, 'attente de confirmation', 'surPlace');
INSERT INTO Commande VALUES (2, DATE '2022-11-14',  TO_DATE('2022/11/14 8:33:25', 'YYYY/MM/DD HH:MI:SS'), 30, 'disponible', 'livraison');
INSERT INTO Commande VALUES (3, DATE '2022-11-14',  TO_DATE('2022/11/14 8:35:25', 'YYYY/MM/DD HH:MI:SS'), 30, 'disponible', 'livraison');
INSERT INTO Commande VALUES (4, DATE '2022-11-16',  TO_DATE('2022/11/16 8:36:25', 'YYYY/MM/DD HH:MI:SS'), 30, 'annulee par le client', 'emporte');
INSERT INTO Commande VALUES (5, DATE '2022-11-17',  TO_DATE('2022/11/17 8:37:25', 'YYYY/MM/DD HH:MI:SS'), 30, 'attente de confirmation', 'emporte');

--mysql
INSERT INTO Commande VALUES (1, DATE '2022-11-13',  STR_TO_DATE('20221113 103545', '%Y%m%d %h%i%s'), 30, 'attente de confirmation', 'surPlace');
INSERT INTO Commande VALUES (2, DATE '2022-11-14',  STR_TO_DATE('20221114 103545', '%Y%m%d %h%i%s'), 30, 'disponible', 'livraison');
INSERT INTO Commande VALUES (3, DATE '2022-11-14',  STR_TO_DATE('20221114 103545', '%Y%m%d %h%i%s'), 30, 'disponible', 'livraison');
INSERT INTO Commande VALUES (4, DATE '2022-11-16',  STR_TO_DATE('20221116 103545', '%Y%m%d %h%i%s'), 30, 'annulee par le client', 'emporte');
INSERT INTO Commande VALUES (5, DATE '2022-11-17',  STR_TO_DATE('20221117 103545', '%Y%m%d %h%i%s'), 30, 'attente de confirmation', 'emporte');

INSERT INTO Commande VALUES (7, DATE '2022-11-13',  STR_TO_DATE('20221113 103545', '%Y%m%d %h%i%s'), 30, 'validee', 'surPlace');
INSERT INTO Commande VALUES (8, DATE '2022-11-13',  STR_TO_DATE('20221113 103545', '%Y%m%d %h%i%s'), 30, 'validee', 'surPlace');
INSERT INTO Commande VALUES (9, DATE '2022-11-13',  STR_TO_DATE('20221113 103545', '%Y%m%d %h%i%s'), 30, 'validee', 'surPlace');
INSERT INTO Commande VALUES (10, DATE '2022-11-13',  STR_TO_DATE('20221113 103545', '%Y%m%d %h%i%s'), 30, 'validee', 'surPlace');

INSERT INTO ComSurPlace VALUES (1, 6, 'midi'); -- restaurant
INSERT INTO ComSurPlace VALUES (7, 6, 'midi');
INSERT INTO ComSurPlace VALUES (8, 6, 'midi');
INSERT INTO ComSurPlace VALUES (9, 6, 'soir');
INSERT INTO ComSurPlace VALUES (10, 6, 'soir');


                
INSERT INTO PasserCommande VALUES (1,1,1);
INSERT INTO PasserCommande VALUES (2,2,2);
INSERT INTO PasserCommande VALUES (3,3,3);
INSERT INTO PasserCommande VALUES (1,4,4);
INSERT INTO PasserCommande VALUES (4,5,5);
INSERT INTO PasserCommande VALUES (3,6,6);
INSERT INTO PasserCommande VALUES (2,7,1);
INSERT INTO PasserCommande VALUES (1,8,6);
INSERT INTO PasserCommande VALUES (1,9,3);


INSERT INTO PlatsDeCommande VALUES (1,1,1, 3);
INSERT INTO PlatsDeCommande VALUES (1,1,3, 3);

INSERT INTO PlatsDeCommande VALUES (2,2,1, 3);
INSERT INTO PlatsDeCommande VALUES (2,2,3, 3);

INSERT INTO PlatsDeCommande VALUES (3,3,1, 3);
INSERT INTO PlatsDeCommande VALUES (3,3,3, 3);

INSERT INTO PlatsDeCommande VALUES (4,4,2, 3);
INSERT INTO PlatsDeCommande VALUES (4,4,3, 3);

INSERT INTO PlatsDeCommande VALUES (5,5,4, 3);
INSERT INTO PlatsDeCommande VALUES (5,5,3, 3);

INSERT INTO PlatsDeCommande VALUES (6,6,5, 3);
INSERT INTO PlatsDeCommande VALUES (6,6,3, 3);

INSERT INTO PlatsDeCommande VALUES (7,1,1, 3);
INSERT INTO PlatsDeCommande VALUES (7,1,3, 3);

INSERT INTO PlatsDeCommande VALUES (8,6,2, 3);
INSERT INTO PlatsDeCommande VALUES (8,6,3, 3);

INSERT INTO PlatsDeCommande VALUES (9,3,3, 3);
INSERT INTO PlatsDeCommande VALUES (9,3,1, 3);





