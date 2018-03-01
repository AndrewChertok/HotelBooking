DELETE FROM room_type;
DELETE FROM contacts;
DELETE FROM clients;
DELETE FROM rooms;
DELETE FROM hotels;
DELETE FROM reservations;
ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO contacts (phone, email, address,city , country)
VALUES ('+44 713 335-55-12', 'river@gmail.com', 'Hermit st. 3 app.77', 'London', 'England'),
  ('+44 731 356-35-22', 'smart@gmail.com', 'Friend st. 17 app.25', 'London', 'England'),
  ('+44 723 638-53-33', 'bruin@gmail.com', 'Moreland st. 33 app.18', 'London', 'England'),
  ('+44 742 342-15-44', 'good@gmail.com', 'Persival st. 221 app.7', 'London', 'England'),
  ('+44 207 836-43-43', 'savoy@gmail.com', 'Strand, London WC2R 0EZ', 'London', 'England'),
  ('+44 742 893-12-78', 'hyatt@gmail.com', '30 Portman Square', 'London', 'England');

INSERT INTO hotels (name, contact_id)
VALUES ('The Savoy', 1004), ('Hyatt', 1005);

INSERT INTO rooms (hotel_id, price_per_night, number, breakfast, cleaning)
VALUES ( 1006, 100, 11, 30, 10),
  (1006, 200, 20, 60, 20),
  (1006, 300, 3, 90, 30),
  (1006, 500, 7, 100, 40),
  (1006, 1500, 320, 100, 50),
  (1007, 100, 5, 30, 10),
  (1007, 200, 33, 60, 20),
  (1007, 300, 48, 90, 30),
  (1007, 500, 78, 100, 40),
  (1007, 1500, 300, 100, 50);


INSERT INTO room_type (type, room_id) VALUES
  ('Single', 1008 ),
  ('Double', 1009),
  ('Triple', 1010),
  ('Family', 1011),
  ('Royal', 1012),
  ('Single', 1013),
  ('Double', 1014),
  ('Triple', 1015),
  ('Family', 1016),
  ('Royal', 1017);


INSERT INTO clients (first_name, last_name, contact_id)
VALUES ('John', 'River', 1000),
  ('Peter', 'Smart', 1001),
  ('Lenny', 'Bruin', 1002),
  ('Samantha', 'Good', 1003);



INSERT INTO reservations (in_date, out_date, room_id, client_id, breakfast, cleaning) VALUES
  ('2018-05-30', '2018-06-05', 1008, 1018, FALSE, FALSE),
  ('2018-07-15', '2018-07-20', 1011, 1019, TRUE , FALSE),
  ('2018-06-10', '2018-06-30', 1015, 1020, FALSE, FALSE),
  ('2018-08-01', '2018-08-26', 1017, 1021, TRUE, TRUE);

