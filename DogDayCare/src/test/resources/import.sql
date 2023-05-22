INSERT INTO CLIENT (document, address, date_created, name )VALUES (1234, 'calle 123', CURRENT_DATE, 'Juan');
INSERT INTO CLIENT (document, address, date_created, name )VALUES (123, 'calle 12', CURRENT_DATE, 'JuanFe');
INSERT INTO CLIENT (document, address, date_created, name )VALUES (444, 'calle 12', CURRENT_DATE, 'JuanFe');
INSERT INTO PET (date_created, name, client) VALUES (CURRENT_DATE, 'Roberto', 1234);
INSERT INTO PET (date_created, name, client) VALUES (CURRENT_DATE, 'Julia', 123);
INSERT INTO PET (date_created, name, client) VALUES (CURRENT_DATE, 'Jacinto', 444);
INSERT INTO BOOKING (date, client_id, pet_id) VALUES (CURRENT_DATE, 1234,1);
INSERT INTO BOOKING (date, client_id, pet_id) VALUES (CURRENT_DATE, 444,3);
