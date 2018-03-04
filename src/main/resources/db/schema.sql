DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS hotels;
DROP TABLE IF EXISTS reservations;
DROP SEQUENCE IF EXISTS global_seq;


CREATE SEQUENCE global_seq START WITH 1000;

CREATE TABLE contacts
(
  id                BIGINT                   DEFAULT global_seq.nextval PRIMARY KEY,
  phone             VARCHAR                  NOT NULL,
  email             VARCHAR                  NOT NULL,
  address           VARCHAR                  NOT NULL,
  city              VARCHAR                  NOT NULL,
  country           VARCHAR                  NOT NULL
);

CREATE UNIQUE INDEX unique_email_idx ON contacts (email);

CREATE TABLE clients
(
  id               BIGINT                   DEFAULT global_seq.nextval PRIMARY KEY,
  contact_id       BIGINT                   NOT NULL,
  first_name       VARCHAR                  NOT NULL,
  last_name        VARCHAR                  NOT NULL,
  FOREIGN KEY (contact_id) REFERENCES contacts (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE hotels
(
  id               BIGINT                     DEFAULT global_seq.nextval PRIMARY KEY,
  contact_id       BIGINT                   NOT NULL,
  name            VARCHAR                    NOT NULL,
  FOREIGN KEY (contact_id) REFERENCES contacts (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE rooms (
  id                 BIGINT                   DEFAULT global_seq.nextval PRIMARY KEY,
  hotel_id           BIGINT                   NOT NULL,
  number             INT                      NOT NULL,
  type               VARCHAR                  NOT NULL,
  breakfast          BIGINT                   NOT NULL,
  cleaning           BIGINT                   NOT NULL,
  price_per_night    DECIMAL                  NOT NULL,
  FOREIGN KEY (hotel_id) REFERENCES hotels (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE reservations
(
  id                 BIGINT DEFAULT global_seq.nextval PRIMARY KEY,
  room_id            BIGINT                   NOT NULL,
  client_id          BIGINT                   NOT NULL,
  in_date            DATE                     NOT NULL,
  out_date           DATE                     NOT NULL,
  breakfast          BOOLEAN DEFAULT FALSE    NOT NULL,
  cleaning           BOOLEAN DEFAULT FALSE    NOT NULL,
  FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE
);






