CREATE TABLE passengers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE flights (
    id SERIAL PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    origin VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    departure_time VARCHAR(10) NOT NULL,
    seats_available INT NOT NULL
);

CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    passenger_id INT NOT NULL,
    flight_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (passenger_id) REFERENCES passengers(id) ON DELETE CASCADE,
    FOREIGN KEY (flight_id) REFERENCES flights(id) ON DELETE CASCADE
);

INSERT INTO passengers (name, age) VALUES 
('Zharkyn', 18),
('Aidar', 25),
('Kamila', 30);

INSERT INTO flights (flight_number, origin, destination, departure_time, seats_available) VALUES 
('KC101', 'Almaty', 'Astana', '10:00', 52),
('KC102', 'Astana', 'Shymkent', '12:00', 67),
('KC103', 'Almaty', 'Shymkent', '14:30', 45);

INSERT INTO bookings (passenger_id, flight_id) VALUES 
(1, 1),
(2, 2),
(1, 3);

