INSERT INTO stations (city)
VALUES ('Bristol'),
       ('Swindon'),
       ('Reading'),
       ('London'),
       ('Northampton'),
       ('Coventry'),
       ('Birmingham');
INSERT INTO routes (start_city_id, end_city_id, length)
VALUES ((SELECT id FROM stations WHERE city = 'Bristol'), (SELECT id FROM stations WHERE city = 'Birmingham'), 3),
       ((SELECT id FROM stations WHERE city = 'Bristol'), (SELECT id FROM stations WHERE city = 'Swindon'), 2),
       ((SELECT id FROM stations WHERE city = 'Swindon'), (SELECT id FROM stations WHERE city = 'Bristol'), 2),
       ((SELECT id FROM stations WHERE city = 'Swindon'), (SELECT id FROM stations WHERE city = 'Reading'), 4),
       ((SELECT id FROM stations WHERE city = 'Swindon'), (SELECT id FROM stations WHERE city = 'Birmingham'), 4),
       ((SELECT id FROM stations WHERE city = 'Reading'), (SELECT id FROM stations WHERE city = 'Swindon'), 4),
       ((SELECT id FROM stations WHERE city = 'Reading'), (SELECT id FROM stations WHERE city = 'London'), 1),
       ((SELECT id FROM stations WHERE city = 'London'), (SELECT id FROM stations WHERE city = 'Reading'), 1),
       ((SELECT id FROM stations WHERE city = 'London'), (SELECT id FROM stations WHERE city = 'Northampton'), 2),
       ((SELECT id FROM stations WHERE city = 'Northampton'), (SELECT id FROM stations WHERE city = 'London'), 2),
       ((SELECT id FROM stations WHERE city = 'Northampton'), (SELECT id FROM stations WHERE city = 'Coventry'), 2),
       ((SELECT id FROM stations WHERE city = 'Coventry'), (SELECT id FROM stations WHERE city = 'Northampton'), 2),
       ((SELECT id FROM stations WHERE city = 'Coventry'), (SELECT id FROM stations WHERE city = 'Birmingham'), 1),
       ((SELECT id FROM stations WHERE city = 'Birmingham'), (SELECT id FROM stations WHERE city = 'Coventry'), 1),
       ((SELECT id FROM stations WHERE city = 'Birmingham'), (SELECT id FROM stations WHERE city = 'Bristol'), 3),
       ((SELECT id FROM stations WHERE city = 'Birmingham'), (SELECT id FROM stations WHERE city = 'Swindon'), 4);
INSERT INTO travellers (first_name, last_name, login, password, role)
VALUES ('John', 'Doe', 'johndoe1', 'doejohn', 'user')