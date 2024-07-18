CREATE TABLE stations (
                         id SERIAL PRIMARY KEY,
                         city VARCHAR(255) UNIQUE NOT NULL
);

-- Создание таблицы Route
CREATE TABLE routes (
                       id SERIAL PRIMARY KEY,
                       start_city_id INT NOT NULL,
                       end_city_id INT NOT NULL,
                       length INT NOT NULL,
                       CONSTRAINT fk_start_city
                           FOREIGN KEY(start_city_id)
                               REFERENCES stations(id),
                       CONSTRAINT fk_end_city
                           FOREIGN KEY(end_city_id)
                               REFERENCES stations(id)
);
INSERT INTO stations (city) VALUES
                               ('Bristol'),
                               ('Swindon'),
                               ('Reading'),
                               ('London'),
                               ('Northampton'),
                               ('Coventry'),
                               ('Birmingham');

-- Вставка данных в таблицу Route с использованием подзапросов для получения id
INSERT INTO routes (start_city_id, end_city_id, length) VALUES
                                                           ((SELECT id FROM stations WHERE city = 'Bristol'), (SELECT id FROM stations WHERE city = 'Birmingham'), 3),
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