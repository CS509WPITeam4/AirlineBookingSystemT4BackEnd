DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS users;

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
