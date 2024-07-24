CREATE TABLE stations (
    id BIGSERIAL PRIMARY KEY,
    city VARCHAR(255) UNIQUE NOT NULL
);

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

CREATE TABLE travellers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);
