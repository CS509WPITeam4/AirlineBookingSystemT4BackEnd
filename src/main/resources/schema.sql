DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS bookingflight;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS `southwests`;
DROP TABLE IF EXISTS `deltas`;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS locations (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         iata_code VARCHAR(10) NOT NULL UNIQUE,
    city_name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    airport_name VARCHAR(200) NOT NULL,
    timezone VARCHAR(100) NOT NULL COMMENT 'IANA timezone format, e.g., America/New_York',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS reservations (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            user_id BIGINT NOT NULL,
                                            flight_number VARCHAR(50) NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    status ENUM('BOOKED', 'CANCELLED', 'CONFIRMED') NOT NULL DEFAULT 'BOOKED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS bookingflight (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     booking_id BIGINT NOT NULL,
     flight_id BIGINT NOT NULL,
     leg_type ENUM('DEPARTURE', 'RETURN') NOT NULL,
     sequence INT NOT NULL,
     FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE
);

CREATE TABLE `deltas` (
                          `Id` int NOT NULL AUTO_INCREMENT,
                          `DepartDateTime` datetime NOT NULL,
                          `ArriveDateTime` datetime NOT NULL,
                          `DepartAirport` varchar(255) NOT NULL,
                          `ArriveAirport` varchar(255) NOT NULL,
                          `FlightNumber` varchar(255) NOT NULL,
                          PRIMARY KEY (`Id`)
);

CREATE TABLE `southwests` (
                              `Id` int NOT NULL AUTO_INCREMENT,
                              `DepartDateTime` datetime NOT NULL,
                              `ArriveDateTime` datetime NOT NULL,
                              `DepartAirport` varchar(255) NOT NULL,
                              `ArriveAirport` varchar(255) NOT NULL,
                              `FlightNumber` varchar(255) NOT NULL,
                              PRIMARY KEY (`Id`)
);

CREATE OR REPLACE VIEW flights AS
SELECT * FROM deltas
UNION ALL
SELECT
CONVERT(Id + 20000, SIGNED) AS Id,
DepartDateTime,
ArriveDateTime,
DepartAirport,
ArriveAirport,
FlightNumber
FROM southwests;
