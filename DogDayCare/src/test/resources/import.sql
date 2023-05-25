INSERT INTO CLIENT (document, address, date_created, email, name )VALUES (1234, 'calle 123', TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') , 'asd@asd.asd', 'Juan');
INSERT INTO CLIENT (document, address, date_created, email,  name )VALUES (123, 'calle 12', TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 'asd@asd.asd', 'JuanFe');
INSERT INTO CLIENT (document, address, date_created, email,  name )VALUES (444, 'calle 12', TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 'asd@asd.asd', 'JuanFe');
INSERT INTO PET (date_created, name, client) VALUES (TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 'Roberto', 1234);
INSERT INTO PET (date_created, name, client) VALUES (TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 'Julia', 123);
INSERT INTO PET (date_created, name, client) VALUES (TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 'Jacinto', 444);
INSERT INTO BOOKING (date, client_id, pet_id) VALUES (TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 1234,1);
INSERT INTO BOOKING (date, client_id, pet_id) VALUES (TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'), 444,3);
