CREATE TABLE passenger(
    id IDENTITY PRIMARY KEY,
    passport VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE ticket(
    id IDENTITY PRIMARY KEY,
    passenger_id BIGINT,
    from_planet VARCHAR(100),
    to_planet VARCHAR(100),
    FOREIGN KEY (passenger_id)
        REFERENCES passenger(id)
);
