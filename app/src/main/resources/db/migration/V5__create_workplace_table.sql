CREATE TABLE workplace(
  id IDENTITY PRIMARY KEY,
  person_id BIGINT,
  place VARCHAR(300),
    FOREIGN KEY (person_id)
      REFERENCES person(id)
      ON DELETE CASCADE
);