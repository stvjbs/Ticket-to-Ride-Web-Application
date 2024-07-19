CREATE TABLE IF NOT EXISTS stations (
                         id SERIAL PRIMARY KEY,
                         city VARCHAR(255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS routes (
id SERIAL PRIMARY KEY,
start_city_id INT NOT NULL,
end_city_id INT NOT NULL,
length INT NOT NULL,
CONSTRAINT fk_start_city FOREIGN KEY(start_city_id) REFERENCES stations(id),
CONSTRAINT fk_end_city FOREIGN KEY(end_city_id) REFERENCES stations(id));

CREATE TABLE IF NOT EXISTS travellers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);
DROP TABLE tickets;
CREATE TABLE IF NOT EXISTS tickets (
    id SERIAL PRIMARY KEY,
    traveller_id BIGINT NOT NULL,
    departure_id BIGINT NOT NULL,
    arrival_id BIGINT NOT NULL,
    segments INTEGER,
    result VARCHAR(255),
    CONSTRAINT fk_traveller FOREIGN KEY (traveller_id) REFERENCES travellers(id),
    CONSTRAINT fk_departure FOREIGN KEY (departure_id) REFERENCES stations(id),
    CONSTRAINT fk_arrival FOREIGN KEY (arrival_id) REFERENCES stations(id)
);

