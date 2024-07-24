-- Create 'stations' table
CREATE TABLE stations (
                        id BIGSERIAL PRIMARY KEY,
                        city VARCHAR(255) UNIQUE NOT NULL
);

-- Create 'routes' table
CREATE TABLE routes (
                        id BIGSERIAL PRIMARY KEY,
                        start_city_id BIGINT NOT NULL,
                        end_city_id BIGINT NOT NULL,
                        length INTEGER NOT NULL,
                        CONSTRAINT fk_start_city
                            FOREIGN KEY (start_city_id)
                                REFERENCES stations (id)
                                ON DELETE CASCADE,
                        CONSTRAINT fk_end_city
                            FOREIGN KEY (end_city_id)
                                REFERENCES stations (id)
                                ON DELETE CASCADE
);

-- Create 'tickets' table
CREATE TABLE tickets (
                        id BIGSERIAL PRIMARY KEY,
                        departure_id BIGINT NOT NULL,
                        arrival_id BIGINT NOT NULL,
                        segments INTEGER NOT NULL,
                        price NUMERIC(10, 2) NOT NULL,
                        currency VARCHAR(10) NOT NULL,
                        traveller VARCHAR(255) NOT NULL,
                        CONSTRAINT fk_departure
                            FOREIGN KEY (departure_id)
                                REFERENCES stations (id)
                                ON DELETE CASCADE,
                        CONSTRAINT fk_arrival
                            FOREIGN KEY (arrival_id)
                                REFERENCES stations (id)
                                ON DELETE CASCADE
);

-- Create 'travellers' table
CREATE TABLE travellers (
                        id BIGSERIAL PRIMARY KEY,
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        login VARCHAR(255),
                        password VARCHAR(255),
                        role VARCHAR(255)
);
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