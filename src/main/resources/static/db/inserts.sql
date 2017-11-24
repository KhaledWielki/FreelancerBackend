#####!!!!!!!!!! DODAJ 2 USERÓW PRZED PONIŻSZYMI INSERTAMI !!!!!!!!!!!!!!!!!!!!!!!######################

# ===============================
# = ROLE
# ===============================

INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'USER');

# ===============================
# = PROFESJE
# ===============================

INSERT INTO `profession` VALUES (1,'Stolarz');
INSERT INTO `profession` VALUES (2,'Hydraulik');
INSERT INTO `profession` VALUES (3,'Informatyk');
INSERT INTO `profession` VALUES (4,'Kierowca');
INSERT INTO `profession` VALUES (5,'Lekarz');
INSERT INTO `profession` VALUES (6,'Opiekunka');


# ===============================
# = ADRESY
# ===============================

INSERT INTO `address` VALUES (1,'15/17', 'Lodz', '10', '91-706', 'Bracka');
INSERT INTO `address` VALUES (2,'85A', 'Lodz', '15', '90-245', 'Sporna');

# ===============================
# = REQUESTY
# ===============================
#
INSERT INTO `request` VALUES (1, 1, '2017-11-10', 'short description', 300, 200, 2, 'Hydraulik na juz!!!', 1, 2, 1);
INSERT INTO `request` VALUES (2, 1, '2017-11-10', 'another desc...', 700, 100, 2, 'Potrzebny kierowca do Warszawy', 2, 4, 1);
INSERT INTO `request` VALUES (3, 1, '2017-11-09', 'test description', 300, 200, 1, 'NEED HYDRAULIK', 1, 2, 2);
INSERT INTO `request` VALUES (4, 0, '2017-11-04', 'bla bla bla', 100, 50, 2,'Opiekuna do dziecka od zaraz', 2, 6, 2);
INSERT INTO `request` VALUES (5, 0, '2017-11-12', 'opis', 250, 200, 0,'Opiekunka do starszej osoby', 2, 6, 2);
INSERT INTO `request` VALUES (6, 1, '2017-11-11', 'iksde', 500, 400, 0,'need opiekunka na pelny etat', 2, 6, 2);


# ===============================
# = DATA SOURCE
# ===============================

# ===============================
# = DATA SOURCE
# ===============================